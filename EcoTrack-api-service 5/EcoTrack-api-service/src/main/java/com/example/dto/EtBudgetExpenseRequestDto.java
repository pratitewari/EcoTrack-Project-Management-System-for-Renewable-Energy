package com.example.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EtBudgetExpenseRequestDto {

    @NotNull(message = "Project ID is required")
    private Integer projectId;

    @NotNull(message = "Budget ID is required")
    private Integer budgetId;

    @NotNull(message = "Expense date is required")
    private LocalDate expenseDate;

    @NotNull(message = "Expense amount is required")
    @Positive(message = "Expense amount should be greater than 0")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal expenseAmount;

    // Getters and Setters
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
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
}
