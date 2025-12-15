package com.example.dto;

import java.time.LocalDate;
import java.util.List;

public class ProjectEquipmentDto {

	private Integer projectId;
	private String projectName;
	private String projectDescription;
	private String projectType;
	private String projectLocation;
	private LocalDate projectStartDate;
	private Integer projectEnergyCapacity;
	private String projectStatus;
	private List<EquipmentDto> equipment;
	
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

	public List<EquipmentDto> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<EquipmentDto> equipment) {
		this.equipment = equipment;
	}
}
