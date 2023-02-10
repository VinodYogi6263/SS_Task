package com.ss.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ss.request.AddressRequest;
import com.ss.response.GeneralResponse;
import com.ss.service.AddressService;

@RestController
public class AddressController {
	@Autowired
	AddressService addressService;

	@PostMapping("/saveaddress")
	public ResponseEntity<GeneralResponse> save(@RequestBody @Valid AddressRequest addressRequest) {

		return addressService.save(addressRequest);
	}

	@GetMapping("/address")
	public ResponseEntity<GeneralResponse> findAll() {

		return addressService.find();
	}

}
