package com.example.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PROJECT_EQUIPMENT")
public class ProjectEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipmentId;

    @Column(nullable = false, length = 50)
    private String equipmentNumber;

    @Column(nullable = false, length = 50)
    private String equipmentType;

    @Column(nullable = false)
    private LocalDate equipmentInstallationDate;

    @Column(nullable = false)
    private LocalDate equipmentWarrantyDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal equipmentQuantity;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<EquipmentMaintenance> maintenances;

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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<EquipmentMaintenance> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(List<EquipmentMaintenance> maintenances) {
		this.maintenances = maintenances;
	}
}
