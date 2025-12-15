package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.dto.ProjectBudgetResponseDto.ProjectDto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EtBudgetExpenseResponseDto {

    private Integer expenseId;
    private LocalDate expenseDate;
    private BigDecimal expenseAmount;
    private ProjectDto project;
    private BudgetDto budget;

    // Getters and Setters
    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public BudgetDto getBudget() {
        return budget;
    }

    public void setBudget(BudgetDto budget) {
        this.budget = budget;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
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
