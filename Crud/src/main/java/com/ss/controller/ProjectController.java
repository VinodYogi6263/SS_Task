package com.ss.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.constant.Message;
import com.ss.request.ProjectRequest;
import com.ss.response.GeneralResponse;
import com.ss.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/project")
@RestController
@Tag(description = "Project resources that provides access to availabe Project data", name = "Project Resource")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@Operation(summary = "Save Project", description = "Save project object")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.found),
			@ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@PostMapping("/saveproject")
	public ResponseEntity<GeneralResponse> save(@RequestBody @Valid ProjectRequest projectRequest) {
		return projectService.save(projectRequest);
	}

	@Operation(summary = "Get Project", description = "Provides all available Project list")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.found),
			@ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@GetMapping("/project")
	public ResponseEntity<GeneralResponse> findAll() {
		return projectService.findAll();
	}

}
