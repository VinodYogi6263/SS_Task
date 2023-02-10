package com.ss.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ss.request.ProjectRequest;
import com.ss.response.GeneralResponse;

@Service
public interface ProjectService {

	public ResponseEntity<GeneralResponse> save(ProjectRequest projectRequest);

	public ResponseEntity<GeneralResponse> findAll();

}
