package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Project;
import com.example.entity.ProjectBudget;

public interface ProjectBudgetRepo extends JpaRepository<ProjectBudget, Integer> {

	boolean existsByProjectAndBudgetType(Project project, String budgetType);

	List<ProjectBudget> findByProject(Project project);

}
