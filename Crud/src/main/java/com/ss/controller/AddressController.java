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
import com.ss.request.AddressRequest;
import com.ss.response.GeneralResponse;
import com.ss.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(description = "Address resources that provides access to availabe address data", name = "Address Resource")
@RequestMapping("/address")
@RestController
public class AddressController {
	@Autowired
	AddressService addressService;

	@Operation(summary = "Save address", description = "Save the Book Object")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.save),
			@ApiResponse(responseCode = "400", description = Message.notSave, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@PostMapping("/saveaddress")
	public ResponseEntity<GeneralResponse> save(@RequestBody @Valid AddressRequest addressRequest) {

		return addressService.save(addressRequest);
	}

	@Operation(summary = "Get address", description = "Provides all available address list")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.found),
			@ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@GetMapping("/address")
	public ResponseEntity<GeneralResponse> findAll() {

		return addressService.find();
	}

}
