package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.EtBudgetExpenseRequestDto;
import com.example.dto.EtBudgetExpenseResponseDto;
import com.example.entity.EtBudgetExpense;
import com.example.entity.Project;
import com.example.entity.ProjectBudget;
import com.example.repository.EtBudgetExpenseRepo;
import com.example.repository.ProjectBudgetRepo;
import com.example.repository.ProjectRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class EtBudgetExpenseServiceImpl implements EtBudgetExpenseService {
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private ProjectBudgetRepo budgetRepo;
	
	@Autowired
	private EtBudgetExpenseRepo expenseRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public EtBudgetExpenseResponseDto saveExpenseDetail(@Valid EtBudgetExpenseRequestDto expenseRequestDto) {
		Project project = projectRepo.findById(expenseRequestDto.getProjectId())
		        .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + expenseRequestDto.getProjectId()));
		
		ProjectBudget budget = budgetRepo.findById(expenseRequestDto.getBudgetId())
        		.orElseThrow(() -> new EntityNotFoundException("Budget not found with ID: " + expenseRequestDto.getBudgetId()));


		EtBudgetExpense	expense = new EtBudgetExpense();
		expense.setProject(project);
		expense.setProjectBudget(budget);
		expense.setExpenseDate(expenseRequestDto.getExpenseDate());
		expense.setExpenseAmount(expenseRequestDto.getExpenseAmount());
	    
		EtBudgetExpense savedExpense = expenseRepo.save(expense);
		return modelMapper.map(savedExpense, EtBudgetExpenseResponseDto.class);
	}

	@Override
	public List<EtBudgetExpenseResponseDto> getExpenses() {
		List<EtBudgetExpense> expenses = expenseRepo.findAll();
		return expenses.stream()
					.map(e -> modelMapper.map(e, EtBudgetExpenseResponseDto.class))
					.collect(Collectors.toList());
	}

}
