package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.EtBudgetExpense;

public interface EtBudgetExpenseRepo extends JpaRepository<EtBudgetExpense, Integer> {

}
