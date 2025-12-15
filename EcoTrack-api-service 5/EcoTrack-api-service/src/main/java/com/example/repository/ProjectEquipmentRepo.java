package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Project;
import com.example.entity.ProjectEquipment;

public interface ProjectEquipmentRepo extends JpaRepository<ProjectEquipment, Integer> {

	List<ProjectEquipment> findByProject(Project project);

}
