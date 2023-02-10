package com.ss.service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ss.request.PaginationRequest;
import com.ss.request.UserRequest;
import com.ss.response.GeneralResponse;
@Service
public interface UserService {

	public  ResponseEntity<GeneralResponse> save(UserRequest userRequest);

	public  ResponseEntity<GeneralResponse> findAll(PaginationRequest paginationRequest);

	public ResponseEntity<GeneralResponse> findById(Integer userID);

	public ResponseEntity<GeneralResponse> update(UserRequest userRequest);

	public ResponseEntity<GeneralResponse> delete(Integer userId);
	
	public ResponseEntity<GeneralResponse>Searching( PaginationRequest paginationRequest);


}
