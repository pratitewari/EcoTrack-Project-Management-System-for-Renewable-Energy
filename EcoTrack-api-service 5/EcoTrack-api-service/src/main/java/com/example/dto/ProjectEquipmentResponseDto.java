package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProjectEquipmentResponseDto {

    private Integer equipmentId;
    private String equipmentNumber;
    private String equipmentType;
    private LocalDate equipmentInstallationDate;
    private LocalDate equipmentWarrantyDate;
    private BigDecimal equipmentQuantity;
    private Integer projectId;

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentNumber() {
		return equipmentNumber;
	}

	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public LocalDate getEquipmentInstallationDate() {
		return equipmentInstallationDate;
	}

	public void setEquipmentInstallationDate(LocalDate equipmentInstallationDate) {
		this.equipmentInstallationDate = equipmentInstallationDate;
	}

	public LocalDate getEquipmentWarrantyDate() {
		return equipmentWarrantyDate;
	}

	public void setEquipmentWarrantyDate(LocalDate equipmentWarrantyDate) {
		this.equipmentWarrantyDate = equipmentWarrantyDate;
	}

	public BigDecimal getEquipmentQuantity() {
		return equipmentQuantity;
	}

	public void setEquipmentQuantity(BigDecimal equipmentQuantity) {
		this.equipmentQuantity = equipmentQuantity;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
}
