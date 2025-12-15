package com.example.service;

import java.util.List;

import com.example.dto.ProjectBudgetDto;
import com.example.dto.ProjectBudgetRequestDto;
import com.example.dto.ProjectBudgetResponseDto;

import jakarta.validation.Valid;

public interface BudgetService {

	ProjectBudgetResponseDto saveBudgetDetail(@Valid ProjectBudgetRequestDto budgetRequestDto) throws Exception;

	List<ProjectBudgetResponseDto> getBudgets();

	ProjectBudgetDto getBudgetByProjectId(Integer id);

}
