package com.example.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

public class ProjectRequestDto {

	@NotBlank(message = "Project name is required.")
	private String projectName;

	@Size(max = 200)
	private String projectDescription;

	@NotBlank(message = "Project type is required.")
	private String projectType;

	@NotBlank(message = "Project location is required.")
	private String projectLocation;

	@NotNull(message = "Project Start Date is required.")
	private LocalDate projectStartDate;

	private Integer projectEnergyCapacity;
	private String projectStatus;

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
}
