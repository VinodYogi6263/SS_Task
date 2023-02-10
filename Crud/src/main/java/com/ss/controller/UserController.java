package com.ss.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ss.constant.Message;
import com.ss.request.PaginationRequest;
import com.ss.request.UserRequest;
import com.ss.response.GeneralResponse;
import com.ss.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/user")
@RestController
@Tag(description = "User resources that provides access to availabe User data", name = "User Resource")
public class UserController {

	@Autowired
	UserService userService;

	@Operation(summary = "Save User", description = "Save USer object")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.save),
			@ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notSave, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@PostMapping("/user")
	public ResponseEntity<GeneralResponse> save(@RequestBody @Valid UserRequest userRequest) {
		return userService.save(userRequest);
	}

	@Operation(summary = "Get User", description = "get User object")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.found),
			@ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@GetMapping("/get")
	public ResponseEntity<GeneralResponse> findAll(@RequestBody @Valid PaginationRequest paginationRequest) {
		return userService.findAll(paginationRequest);

	}

	@Operation(summary = "Get User", description = "get User object")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.found),
			@ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@GetMapping("/getby")
	public ResponseEntity<GeneralResponse> findById(@RequestParam("id") Integer id) {
		return userService.findById(id);
	}

	@Operation(summary = "Get User", description = "Search the User")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.found),
			@ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@GetMapping("/searching")
	public ResponseEntity<GeneralResponse> Searching(@RequestBody @Valid PaginationRequest paginationRequest) {
		return userService.Searching(paginationRequest);
	}

	@Operation(summary = "Update User", description = "Update User object")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.update),
			@ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notUpdate, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@PutMapping("/userupdate")
	public ResponseEntity<GeneralResponse> update(@RequestBody @Valid UserRequest userRequest) {
		return userService.update(userRequest);
	}

	@Operation(summary = "Delete User", description = "Delete User Id   ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.delete),
			@ApiResponse(responseCode = "400", description = "", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notDelete, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@DeleteMapping("/deleteuser")
	public ResponseEntity<GeneralResponse> deleteById(@RequestParam("id") Integer deleteid) {
		return userService.delete(deleteid);

	}
}
