package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer> {

	boolean existsByProjectName(String projectName);

}
