package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EQUIPMENT_MAINTENANCE")
public class EquipmentMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maintenanceId;

    @Column(nullable = false, length = 50)
    private String maintenanceType;

    @Column(nullable = false)
    private LocalDate maintenanceDate;

    @Column(length = 200)
    private String maintenanceDescription;

    @Column(nullable = false, length = 50)
    private String maintenanceStatus;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private ProjectEquipment equipment;

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

	public ProjectEquipment getEquipment() {
		return equipment;
	}

	public void setEquipment(ProjectEquipment equipment) {
		this.equipment = equipment;
	}
}
