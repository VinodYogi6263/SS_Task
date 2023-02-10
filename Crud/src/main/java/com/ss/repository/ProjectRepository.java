package com.ss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

	boolean existsById(List<Integer> projectId);

}
