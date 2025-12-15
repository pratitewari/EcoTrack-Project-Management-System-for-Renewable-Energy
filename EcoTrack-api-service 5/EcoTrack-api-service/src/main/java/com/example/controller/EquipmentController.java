package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProjectEquipmentDto;
import com.example.dto.ProjectEquipmentRequestDto;
import com.example.dto.ProjectEquipmentResponseDto;
import com.example.service.EquipmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/equipment")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;

	@PostMapping
	public ResponseEntity<?> saveEquipmentDetail(@Valid @RequestBody ProjectEquipmentRequestDto equipmentRequestDto) {
		ProjectEquipmentResponseDto dto = equipmentService.saveEquipmentDetail(equipmentRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<?> getEquipments() {
		List<ProjectEquipmentDto> dtos = equipmentService.getEquipments();
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEquipmentByProjectId(@PathVariable Integer id) {
		List<ProjectEquipmentDto> dtos = equipmentService.getEquipmentByProjectId(id);
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
}
