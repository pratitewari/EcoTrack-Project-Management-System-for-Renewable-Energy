package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EquipmentDto {
	
	private Integer equipmentId;
    private String equipmentNumber;
    private String equipmentType;
    private LocalDate equipmentInstallationDate;
    private LocalDate equipmentWarrantyDate;
    private BigDecimal equipmentQuantity;
    private List<MaitenanceDto> maintenances;
    
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
	
	public List<MaitenanceDto> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(List<MaitenanceDto> maintenances) {
		this.maintenances = maintenances;
	}

	public static class MaitenanceDto {
		
		private Integer maintenanceId;
	    private String maintenanceType;
	    private LocalDate maintenanceDate;
	    private String maintenanceDescription;
	    private String maintenanceStatus;
	    
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
	}
}