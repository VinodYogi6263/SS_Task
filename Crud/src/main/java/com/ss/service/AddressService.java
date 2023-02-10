package com.ss.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ss.request.AddressRequest;
import com.ss.response.GeneralResponse;

@Service
public interface AddressService {
	public ResponseEntity<GeneralResponse> find();

	public ResponseEntity<GeneralResponse> save(AddressRequest addressRequest);

}
