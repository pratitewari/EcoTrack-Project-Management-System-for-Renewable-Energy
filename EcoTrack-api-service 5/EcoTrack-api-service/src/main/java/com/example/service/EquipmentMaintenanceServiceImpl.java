package com.example.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.EquipmentMaintenanceRequestDto;
import com.example.dto.EquipmentMaintenanceResponseDto;
import com.example.dto.EquipmentDto;
import com.example.dto.EquipmentDto.MaitenanceDto;
import com.example.entity.EquipmentMaintenance;
import com.example.entity.ProjectEquipment;
import com.example.repository.EquipmentMaintenanceRepo;
import com.example.repository.ProjectEquipmentRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class EquipmentMaintenanceServiceImpl implements EquipmentMaintenanceService {
	
	@Autowired
	private ProjectEquipmentRepo equipmentRepo;
	
	@Autowired
	private EquipmentMaintenanceRepo maintenanceRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public EquipmentMaintenanceResponseDto saveEquipmentMaintenanceDetail(@Valid EquipmentMaintenanceRequestDto equipmentRequestDto) {
		ProjectEquipment equipment = equipmentRepo.findById(equipmentRequestDto.getEquipmentId())
		        .orElseThrow(() -> new EntityNotFoundException("Equipment not found with ID: " + equipmentRequestDto.getEquipmentId()));

		EquipmentMaintenance maintenance = new EquipmentMaintenance();
		maintenance.setEquipment(equipment);
		maintenance.setMaintenanceType(equipmentRequestDto.getMaintenanceType());
		maintenance.setMaintenanceDescription(equipmentRequestDto.getMaintenanceDescription());
		maintenance.setMaintenanceDate(equipmentRequestDto.getMaintenanceDate());
		maintenance.setMaintenanceStatus(equipmentRequestDto.getMaintenanceStatus());
	    
		EquipmentMaintenance savedMaintenance = maintenanceRepo.save(maintenance);
		return modelMapper.map(savedMaintenance, EquipmentMaintenanceResponseDto.class);
	}

	@Override
	public List<EquipmentDto> getAllMaintenances() {
		List<EquipmentMaintenance> maintenances = maintenanceRepo.findAll();
		Map<ProjectEquipment, List<EquipmentMaintenance>> grouped = maintenances.stream()
	            .collect(Collectors.groupingBy(EquipmentMaintenance::getEquipment));

	    return grouped.entrySet().stream().map(entry -> {
	        ProjectEquipment equipment = entry.getKey();
	        List<EquipmentMaintenance> maintenanceList = entry.getValue();
	        EquipmentDto equipmentDto = modelMapper.map(equipment, EquipmentDto.class);
	        
	        List<MaitenanceDto> maintenanceDtos = maintenanceList.stream()
	        		.map(m -> modelMapper.map(m, MaitenanceDto.class))
	        		.collect(Collectors.toList());

	        equipmentDto.setMaintenances(maintenanceDtos);

	        return equipmentDto;
	    }).collect(Collectors.toList());
	}

}
