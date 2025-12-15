package com.example.dto;

import java.time.LocalDate;

public class EquipmentMaintenanceResponseDto {

    private Integer maintenanceId;
    private String maintenanceType;
    private LocalDate maintenanceDate;
    private String maintenanceDescription;
    private String maintenanceStatus;
    private Integer equipmentId;
    
	public Integer getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

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
