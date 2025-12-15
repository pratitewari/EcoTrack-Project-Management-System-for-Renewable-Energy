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

import com.example.dto.ProjectRequestDto;
import com.example.dto.ProjectResponseDto;
import com.example.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;

	@PostMapping
	public ResponseEntity<?> saveProjectDetail(@Valid @RequestBody ProjectRequestDto  projectRequestDto) throws Exception {
		ProjectResponseDto dto = projectService.saveProjectDetail(projectRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProjectById(@PathVariable Integer id) {
		ProjectResponseDto dto = projectService.getProjectById(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<?> getProjects() {
		List<ProjectResponseDto> dtos = projectService.getProjects();
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
}
