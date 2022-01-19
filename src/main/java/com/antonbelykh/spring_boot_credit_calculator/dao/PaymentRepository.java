package com.antonbelykh.spring_boot_credit_calculator.dao;

import com.antonbelykh.spring_boot_credit_calculator.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    public void deletePaymentsByCredit_Id(int id);
}
