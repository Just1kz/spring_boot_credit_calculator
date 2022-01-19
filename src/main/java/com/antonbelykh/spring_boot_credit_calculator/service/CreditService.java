package com.antonbelykh.spring_boot_credit_calculator.service;

import com.antonbelykh.spring_boot_credit_calculator.entity.Credit;

import java.util.List;

public interface CreditService {

    public List<Credit> getAll();

    public Credit getId(Integer id);

    public void saveOrUpdate(Credit credit);

    public void deleteById(int id);

    public void calculateSchedulePayments(int id);

    public void deleteScheduleAfterUpdateCreditParams(int id);
}
