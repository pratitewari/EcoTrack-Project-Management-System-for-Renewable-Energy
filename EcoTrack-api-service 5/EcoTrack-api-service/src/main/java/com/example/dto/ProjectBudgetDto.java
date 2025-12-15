package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProjectBudgetDto {

	private Integer projectId;
	private String projectName;
	private String projectDescription;
	private String projectType;
	private String projectLocation;
	private LocalDate projectStartDate;
	private Integer projectEnergyCapacity;
	private String projectStatus;
	private List<BudgetDto> budgets;
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public LocalDate getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(LocalDate projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Integer getProjectEnergyCapacity() {
		return projectEnergyCapacity;
	}

	public void setProjectEnergyCapacity(Integer projectEnergyCapacity) {
		this.projectEnergyCapacity = projectEnergyCapacity;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	
	public List<BudgetDto> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<BudgetDto> budgets) {
		this.budgets = budgets;
	}

	public static class BudgetDto {
		
		private Integer budgetId;
	    private String budgetType;
	    private BigDecimal budgetAmount;
	    
	    public Integer getBudgetId() {
	        return budgetId;
	    }

	    public void setBudgetId(Integer budgetId) {
	        this.budgetId = budgetId;
	    }

	    public String getBudgetType() {
	        return budgetType;
	    }

	    public void setBudgetType(String budgetType) {
	        this.budgetType = budgetType;
	    }

	    public BigDecimal getBudgetAmount() {
	        return budgetAmount;
	    }

	    public void setBudgetAmount(BigDecimal budgetAmount) {
	        this.budgetAmount = budgetAmount;
	    }
	}
}
