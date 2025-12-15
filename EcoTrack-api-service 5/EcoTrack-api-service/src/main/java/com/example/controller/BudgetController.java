package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProjectBudgetDto;
import com.example.dto.ProjectBudgetRequestDto;
import com.example.dto.ProjectBudgetResponseDto;
import com.example.service.BudgetService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/budgets")
public class BudgetController {

	@Autowired
	private BudgetService budgetService;

	@PostMapping
	public ResponseEntity<?> saveBudgetDetail(@Valid @RequestBody ProjectBudgetRequestDto budgetRequestDto) throws Exception {
		ProjectBudgetResponseDto dto = budgetService.saveBudgetDetail(budgetRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<?> getBudgets() {
		List<ProjectBudgetResponseDto> dtos = budgetService.getBudgets();
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBudgetByProjectId(@PathVariable Integer id) {
		ProjectBudgetDto dtos = budgetService.getBudgetByProjectId(id);
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
}
