package com.antonbelykh.spring_boot_credit_calculator.dao;

import com.antonbelykh.spring_boot_credit_calculator.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Integer> {
}
