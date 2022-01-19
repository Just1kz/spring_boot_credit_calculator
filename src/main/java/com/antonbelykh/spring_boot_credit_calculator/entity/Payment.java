package com.antonbelykh.spring_boot_credit_calculator.entity;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "month")
    private int  month;

    @Column(name = "debt_before_payment")
    private double  debtBeforePayment;

    @Column(name = "percent_payment")
    private double percentPayment;

    @Column(name = "debt_payment")
    private double debtPayment;

    @Column(name = "payment")
    private double payment;

    @Column(name = "debt_after_payment")
    private double debtAfterPayment;

    //Исключен метод удаления, чтобы при обновлении параметров кредита удалялся только сам график не цепляя за собой сам кредит
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "credit_id")
    private Credit credit;

    public Payment() {
    }

    public Payment(int month, double debtBeforePayment, double percentPayment, double debtPayment, double payment, double debtAfterPayment) {
        this.month = month;
        this.debtBeforePayment = debtBeforePayment;
        this.percentPayment = percentPayment;
        this.debtPayment = debtPayment;
        this.payment = payment;
        this.debtAfterPayment = debtAfterPayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getDebtBeforePayment() {
        return debtBeforePayment;
    }

    public void setDebtBeforePayment(double debtBeforePayment) {
        this.debtBeforePayment = debtBeforePayment;
    }

    public double getPercentPayment() {
        return percentPayment;
    }

    public void setPercentPayment(double percentPayment) {
        this.percentPayment = percentPayment;
    }

    public double getDebtPayment() {
        return debtPayment;
    }

    public void setDebtPayment(double debtPayment) {
        this.debtPayment = debtPayment;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getDebtAfterPayment() {
        return debtAfterPayment;
    }

    public void setDebtAfterPayment(double debtAfterPayment) {
        this.debtAfterPayment = debtAfterPayment;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "debtBeforePayment=" + debtBeforePayment +
                ", percentPayment=" + percentPayment +
                ", debtPayment=" + debtPayment +
                ", payment=" + payment +
                ", debtAfterPayment=" + debtAfterPayment +
                '}';
    }
}
