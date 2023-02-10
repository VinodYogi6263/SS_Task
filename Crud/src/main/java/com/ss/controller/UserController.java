package com.ss.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ss.request.PaginationRequest;
import com.ss.request.UserRequest;
import com.ss.response.GeneralResponse;
import com.ss.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/user")
	public ResponseEntity<GeneralResponse> save(@RequestBody @Valid UserRequest userRequest) {
		return userService.save(userRequest);
	}

	@GetMapping("/get")
	public ResponseEntity<GeneralResponse> findAll(@RequestBody @Valid PaginationRequest paginationRequest) {
		return userService.findAll(paginationRequest);

	}

	@GetMapping("/getby")
	public ResponseEntity<GeneralResponse> findById(@RequestParam("id") Integer id) {
		return userService.findById(id);
	}

	@GetMapping("/searching")
	public ResponseEntity<GeneralResponse> Searching(@RequestBody @Valid PaginationRequest paginationRequest) {
		return userService.Searching(paginationRequest);
	}

	@PutMapping("/userupdate")
	public ResponseEntity<GeneralResponse> update(@RequestBody @Valid UserRequest userRequest) {
		return userService.update(userRequest);
	}

	@DeleteMapping("/deleteuser")
	public ResponseEntity<GeneralResponse> deleteById(@RequestParam("id") Integer deleteid) {
		return userService.delete(deleteid);

	}
}
