package com.example.service;

import java.util.List;

import com.example.dto.ProjectRequestDto;
import com.example.dto.ProjectResponseDto;

import jakarta.validation.Valid;

public interface ProjectService {

	ProjectResponseDto saveProjectDetail(@Valid ProjectRequestDto projectRequestDto) throws Exception;

	ProjectResponseDto getProjectById(Integer id);

	List<ProjectResponseDto> getProjects();

}
