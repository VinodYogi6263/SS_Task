package com.ss.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ss.request.ProjectRequest;
import com.ss.response.GeneralResponse;
import com.ss.service.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@PostMapping("/saveproject")
	public ResponseEntity<GeneralResponse> save(@RequestBody @Valid ProjectRequest projectRequest) {
		return projectService.save(projectRequest);
	}

	@GetMapping("/project")
	public ResponseEntity<GeneralResponse> findAll() {
		return projectService.findAll();
	}

}
