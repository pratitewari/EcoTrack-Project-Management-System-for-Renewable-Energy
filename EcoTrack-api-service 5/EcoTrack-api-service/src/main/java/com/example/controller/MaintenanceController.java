package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EquipmentDto;
import com.example.dto.EquipmentMaintenanceRequestDto;
import com.example.dto.EquipmentMaintenanceResponseDto;
import com.example.service.EquipmentMaintenanceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/maintenance")
public class MaintenanceController {

	@Autowired
	private EquipmentMaintenanceService maintenanceService;

	@PostMapping
	public ResponseEntity<?> saveEquipmentMaintenanceDetail(@Valid @RequestBody EquipmentMaintenanceRequestDto equipmentRequestDto) {
		EquipmentMaintenanceResponseDto dto = maintenanceService.saveEquipmentMaintenanceDetail(equipmentRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllMaintenances() {
		List<EquipmentDto> dtos = maintenanceService.getAllMaintenances();
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
}
