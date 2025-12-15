package com.example.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EquipmentMaintenanceRequestDto {

    @NotBlank(message = "Maintenance type is required")
    private String maintenanceType;

    @NotNull(message = "Maintenance date is required")
    private LocalDate maintenanceDate;

    @Size(max = 200, message = "Description must not exceed 200 characters")
    private String maintenanceDescription;

    @NotBlank(message = "Maintenance status is required")
    private String maintenanceStatus;

    @NotNull(message = "Equipment ID is required")
    private Integer equipmentId;

	public String getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public LocalDate getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(LocalDate maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getMaintenanceDescription() {
		return maintenanceDescription;
	}

	public void setMaintenanceDescription(String maintenanceDescription) {
		this.maintenanceDescription = maintenanceDescription;
	}

	public String getMaintenanceStatus() {
		return maintenanceStatus;
	}

	public void setMaintenanceStatus(String maintenanceStatus) {
		this.maintenanceStatus = maintenanceStatus;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
}
