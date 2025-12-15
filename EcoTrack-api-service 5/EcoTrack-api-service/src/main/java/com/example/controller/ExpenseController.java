package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EtBudgetExpenseRequestDto;
import com.example.dto.EtBudgetExpenseResponseDto;
import com.example.service.EtBudgetExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

	@Autowired
	private EtBudgetExpenseService expenseService;

	@PostMapping
	public ResponseEntity<?> saveExpenseDetail(@Valid @RequestBody EtBudgetExpenseRequestDto expenseRequestDto) {
		EtBudgetExpenseResponseDto dto = expenseService.saveExpenseDetail(expenseRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<?> getExpenses() {
		List<EtBudgetExpenseResponseDto> dtos = expenseService.getExpenses();
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
}
