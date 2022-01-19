package com.antonbelykh.spring_boot_credit_calculator.service;

import com.antonbelykh.spring_boot_credit_calculator.entity.Credit;
import com.antonbelykh.spring_boot_credit_calculator.dao.CreditRepository;
import com.antonbelykh.spring_boot_credit_calculator.dao.PaymentRepository;
import com.antonbelykh.spring_boot_credit_calculator.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository, PaymentRepository paymentRepository) {
        this.creditRepository = creditRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Credit> getAll() {
        return creditRepository.findAll();
    }

    @Override
    public Credit getId(Integer id) {
        Credit credit = null;

        Optional<Credit> creditOptional = creditRepository.findById(id);
        if (creditOptional.isPresent()) {
            credit = creditOptional.get();
        }
        return credit;
    }

    @Override
    public void saveOrUpdate(Credit credit) {
        creditRepository.save(credit);
    }

    @Override
    public void deleteById(int id) {
        creditRepository.deleteById(id);
    }

    @Override
    public void calculateSchedulePayments(int id) {
        Credit credit = getId(id);
        List<Payment> schedulePayment = new ArrayList<>();
        Payment payment;
        double annuityPayment = calculateAnnuityPayment(credit);
        double everyMonthPercentRatio = credit.getCostPercentage()/100/12;

        for (int i = 0; i < credit.getPeriodCredit(); i++) {
            payment = new Payment();
            payment.setPayment(annuityPayment);

            if (i==0) {
                payment.setDebtBeforePayment(credit.getAmount());
            } else {
                int count = i -1;
                payment.setDebtBeforePayment(schedulePayment.get(count).getDebtAfterPayment());
            }
            payment.setMonth(i+1);
            payment.setPercentPayment(payment.getDebtBeforePayment() * everyMonthPercentRatio);
            payment.setDebtPayment(payment.getPayment() - payment.getPercentPayment());
            payment.setDebtAfterPayment(payment.getDebtBeforePayment() - payment.getDebtPayment());
            schedulePayment.add(payment);
        }

        for (Payment rsl:schedulePayment) {
            credit.addPaymentToCredit(rsl);
        }

        creditRepository.save(credit);
    }

    @Transactional
    @Override
    public void deleteScheduleAfterUpdateCreditParams(int id) {
        paymentRepository.deletePaymentsByCredit_Id(id);
    }

    private double calculateAnnuityPayment(Credit credit) {
        double amount = credit.getAmount();
        double period = credit.getPeriodCredit();
        double everyMonthPercentRatio = credit.getCostPercentage()/100/12;
        return amount * (everyMonthPercentRatio + everyMonthPercentRatio/(Math.pow((1+everyMonthPercentRatio), period) - 1));
    }
}
