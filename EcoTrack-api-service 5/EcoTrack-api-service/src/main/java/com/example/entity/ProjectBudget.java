package com.example.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "PROJECT_BUDGET")
public class ProjectBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer budgetId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(nullable = false, length = 50)
    private String budgetType;

    @Column(precision = 10, scale = 2)
    private BigDecimal budgetAmount;

    @OneToMany(mappedBy = "projectBudget", cascade = CascadeType.ALL)
    private List<EtBudgetExpense> expenses;

	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public List<EtBudgetExpense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<EtBudgetExpense> expenses) {
		this.expenses = expenses;
	}
}

