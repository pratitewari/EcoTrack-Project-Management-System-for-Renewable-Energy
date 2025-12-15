package com.example.service;

import java.util.List;

import com.example.dto.EquipmentDto;
import com.example.dto.EquipmentMaintenanceRequestDto;
import com.example.dto.EquipmentMaintenanceResponseDto;

import jakarta.validation.Valid;

public interface EquipmentMaintenanceService {

	EquipmentMaintenanceResponseDto saveEquipmentMaintenanceDetail(@Valid EquipmentMaintenanceRequestDto equipmentRequestDto);

	List<EquipmentDto> getAllMaintenances();

}
