package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    @Column(nullable = false, length = 50)
    private String projectName;

    @Column(length = 200)
    private String projectDescription;

    @Column(nullable = false, length = 50)
    private String projectType;

    @Column(nullable = false, length = 50)
    private String projectLocation;

    @Column(nullable = false)
    private LocalDate projectStartDate;

    @Column
    private Integer projectEnergyCapacity;

    @Column(length = 50)
    private String projectStatus;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectBudget> projectBudgets;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<EtBudgetExpense> expenses;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectEquipment> equipments;

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

	public List<ProjectBudget> getProjectBudgets() {
		return projectBudgets;
	}

	public void setProjectBudgets(List<ProjectBudget> projectBudgets) {
		this.projectBudgets = projectBudgets;
	}

	public List<EtBudgetExpense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<EtBudgetExpense> expenses) {
		this.expenses = expenses;
	}

	public List<ProjectEquipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<ProjectEquipment> equipments) {
		this.equipments = equipments;
	}
}

