package com.example.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.ProjectEquipmentRequestDto;
import com.example.dto.ProjectEquipmentResponseDto;
import com.example.dto.EquipmentDto;
import com.example.dto.EquipmentDto.MaitenanceDto;
import com.example.dto.ProjectEquipmentDto;
import com.example.entity.Project;
import com.example.entity.ProjectEquipment;
import com.example.repository.ProjectEquipmentRepo;
import com.example.repository.ProjectRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class EquipmentServiceImpl implements EquipmentService {
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private ProjectEquipmentRepo equipmentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public ProjectEquipmentResponseDto saveEquipmentDetail(@Valid ProjectEquipmentRequestDto equipmentRequestDto) {
		Project project = projectRepo.findById(equipmentRequestDto.getProjectId())
		        .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + equipmentRequestDto.getProjectId()));

		ProjectEquipment equipment = new ProjectEquipment();
		equipment.setProject(project);
		equipment.setEquipmentNumber(equipmentRequestDto.getEquipmentNumber());
		equipment.setEquipmentType(equipmentRequestDto.getEquipmentType());
		equipment.setEquipmentQuantity(equipmentRequestDto.getEquipmentQuantity());
		equipment.setEquipmentWarrantyDate(equipmentRequestDto.getEquipmentWarrantyDate());
		equipment.setEquipmentInstallationDate(equipmentRequestDto.getEquipmentInstallationDate());
		
		ProjectEquipment savedEquipment = equipmentRepo.save(equipment);
		return modelMapper.map(savedEquipment, ProjectEquipmentResponseDto.class);
	}

	@Override
	public List<ProjectEquipmentDto> getEquipments() {
		List<ProjectEquipment> equipments = equipmentRepo.findAll();
		Map<Project, List<ProjectEquipment>> groupedByProject = equipments.stream()
				.collect(Collectors.groupingBy(ProjectEquipment::getProject));

		return groupedByProject.entrySet().stream().map(entry -> {
			Project project = entry.getKey();
			List<ProjectEquipment> projectEquipments = entry.getValue();
			ProjectEquipmentDto dto = modelMapper.map(project, ProjectEquipmentDto.class);
			List<EquipmentDto> equipmentDtos = projectEquipments.stream().map(equipment -> {
				EquipmentDto equipmentDto = modelMapper.map(equipment, EquipmentDto.class);

				if (equipment.getMaintenances() != null) {
					List<MaitenanceDto> maintenanceDtos = equipment.getMaintenances().stream().map(m -> {
						MaitenanceDto maintenanceDto = modelMapper.map(m, MaitenanceDto.class);
						return maintenanceDto;
					}).collect(Collectors.toList());

					equipmentDto.setMaintenances(maintenanceDtos);
				}

				return equipmentDto;
			}).collect(Collectors.toList());

			dto.setEquipment(equipmentDtos);
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<ProjectEquipmentDto> getEquipmentByProjectId(Integer id) {
		Project project = projectRepo.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));

		List<ProjectEquipment> equipments = equipmentRepo.findByProject(project);
		Map<Project, List<ProjectEquipment>> groupedByProject = equipments.stream()
				.collect(Collectors.groupingBy(ProjectEquipment::getProject));

		return groupedByProject.entrySet().stream().map(entry -> {
			Project project1 = entry.getKey();
			List<ProjectEquipment> projectEquipments = entry.getValue();
			ProjectEquipmentDto dto = modelMapper.map(project1, ProjectEquipmentDto.class);
			List<EquipmentDto> equipmentDtos = projectEquipments.stream().map(equipment -> {
				EquipmentDto equipmentDto = modelMapper.map(equipment, EquipmentDto.class);

				if (equipment.getMaintenances() != null) {
					List<MaitenanceDto> maintenanceDtos = equipment.getMaintenances().stream().map(m -> {
						MaitenanceDto maintenanceDto = modelMapper.map(m, MaitenanceDto.class);
						return maintenanceDto;
					}).collect(Collectors.toList());

					equipmentDto.setMaintenances(maintenanceDtos);
				}

				return equipmentDto;
			}).collect(Collectors.toList());

			dto.setEquipment(equipmentDtos);
			return dto;
		}).collect(Collectors.toList());
	}
}
