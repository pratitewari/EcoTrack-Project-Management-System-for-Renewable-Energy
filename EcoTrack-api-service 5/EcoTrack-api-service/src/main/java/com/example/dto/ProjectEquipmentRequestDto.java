package com.example.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProjectEquipmentRequestDto {

    @NotBlank(message = "Equipment number is required")
    private String equipmentNumber;

    @NotBlank(message = "Equipment type is required")
    private String equipmentType;

    @NotNull(message = "Installation date is required")
    private LocalDate equipmentInstallationDate;

    @NotNull(message = "Warranty date is required")
    private LocalDate equipmentWarrantyDate;

    @NotNull(message = "Equipment quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private BigDecimal equipmentQuantity;

    @NotNull(message = "Project ID is required")
    private Integer projectId;

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
