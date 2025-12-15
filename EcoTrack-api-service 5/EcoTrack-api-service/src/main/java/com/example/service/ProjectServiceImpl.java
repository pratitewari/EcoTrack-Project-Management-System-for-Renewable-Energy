package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.ProjectRequestDto;
import com.example.dto.ProjectResponseDto;
import com.example.entity.Project;
import com.example.repository.ProjectRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public ProjectResponseDto saveProjectDetail(@Valid ProjectRequestDto projectRequestDto) throws Exception {
		boolean existsByName = projectRepo.existsByProjectName(projectRequestDto.getProjectName());
		if(existsByName) {
			throw new BadRequestException("Project already exists by name " + projectRequestDto.getProjectName());
		}
		Project project = modelMapper.map(projectRequestDto, Project.class);
		Project savedProject = projectRepo.save(project);
		return modelMapper.map(savedProject, ProjectResponseDto.class);
	}

	@Override
	public ProjectResponseDto getProjectById(Integer id) {
		Project project = projectRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));
		return modelMapper.map(project, ProjectResponseDto.class);
	}
	
	@Override
	public List<ProjectResponseDto> getProjects() {
		List<Project> projects = projectRepo.findAll();
		return projects.stream()
					.map(p -> modelMapper.map(p, ProjectResponseDto.class))
					.collect(Collectors.toList());
	}
}
