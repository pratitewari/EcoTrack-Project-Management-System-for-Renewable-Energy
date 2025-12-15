package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.ProjectBudgetDto.BudgetDto;
import com.example.dto.ProjectBudgetDto;
import com.example.dto.ProjectBudgetRequestDto;
import com.example.dto.ProjectBudgetResponseDto;
import com.example.dto.ProjectBudgetResponseDto.ProjectDto;
import com.example.entity.Project;
import com.example.entity.ProjectBudget;
import com.example.repository.ProjectBudgetRepo;
import com.example.repository.ProjectRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class BudgetServiceImpl implements BudgetService {
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private ProjectBudgetRepo budgetRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public ProjectBudgetResponseDto saveBudgetDetail(@Valid ProjectBudgetRequestDto budgetRequestDto) throws Exception {
		Project project = projectRepo.findById(budgetRequestDto.getProjectId())
		        .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + budgetRequestDto.getProjectId()));

		boolean existsByProjectIdAndBudgetType = budgetRepo.existsByProjectAndBudgetType(project, budgetRequestDto.getBudgetType());
		if(existsByProjectIdAndBudgetType) {
			throw new BadRequestException("Budget already exists for project " + project.getProjectName() + " and budget type " + budgetRequestDto.getBudgetType());
		}
		
		ProjectBudget budget = new ProjectBudget();
	    budget.setProject(project);
	    budget.setBudgetType(budgetRequestDto.getBudgetType());
	    budget.setBudgetAmount(budgetRequestDto.getBudgetAmount());
	    
		ProjectBudget savedBudget = budgetRepo.save(budget);
		return modelMapper.map(savedBudget, ProjectBudgetResponseDto.class);
	}

	@Override
	public List<ProjectBudgetResponseDto> getBudgets() {
		List<ProjectBudget> budgets = budgetRepo.findAll();
		return budgets.stream()
					.map(b -> 
						{
							ProjectBudgetResponseDto dto = modelMapper.map(b, ProjectBudgetResponseDto.class);
							ProjectDto projectDto = modelMapper.map(b.getProject(), ProjectDto.class);
							dto.setProject(projectDto);

							return dto;
						})
					.collect(Collectors.toList());
	}

	@Override
	public ProjectBudgetDto getBudgetByProjectId(Integer id) {
		Project project = projectRepo.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));

		List<ProjectBudget> budgets = budgetRepo.findByProject(project);
		
		ProjectBudgetDto dto = modelMapper.map(project, ProjectBudgetDto.class);
		List<BudgetDto> budgetDto = budgets.stream()
				.map(b -> modelMapper.map(b, BudgetDto.class))
				.collect(Collectors.toList());
		dto.setBudgets(budgetDto);
		
		return dto;
	}

}
