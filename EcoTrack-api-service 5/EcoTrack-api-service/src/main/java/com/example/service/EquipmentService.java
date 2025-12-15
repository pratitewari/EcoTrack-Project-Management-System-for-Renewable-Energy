package com.example.service;

import java.util.List;

import com.example.dto.ProjectEquipmentDto;
import com.example.dto.ProjectEquipmentRequestDto;
import com.example.dto.ProjectEquipmentResponseDto;

import jakarta.validation.Valid;

public interface EquipmentService {

	ProjectEquipmentResponseDto saveEquipmentDetail(@Valid ProjectEquipmentRequestDto equipmentRequestDto);

	List<ProjectEquipmentDto> getEquipments();

	List<ProjectEquipmentDto> getEquipmentByProjectId(Integer id);

}
