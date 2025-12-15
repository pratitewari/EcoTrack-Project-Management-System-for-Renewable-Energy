package com.example.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProjectBudgetRequestDto {

    @NotNull(message = "Project ID is required")
    private Integer projectId;

    @NotBlank(message = "Budget type is required")
    @Size(max = 50)
    private String budgetType;

    @NotNull(message = "Budget amount is required")
    @Positive(message = "Budget amount should be greater than 0")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal budgetAmount;

    // Getters and Setters
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

