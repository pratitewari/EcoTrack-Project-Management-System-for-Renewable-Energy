package com.example.service;

import java.util.List;

import com.example.dto.EtBudgetExpenseRequestDto;
import com.example.dto.EtBudgetExpenseResponseDto;

import jakarta.validation.Valid;

public interface EtBudgetExpenseService {

	EtBudgetExpenseResponseDto saveExpenseDetail(@Valid EtBudgetExpenseRequestDto expenseRequestDto);

	List<EtBudgetExpenseResponseDto> getExpenses();

}
