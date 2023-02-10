package com.ss.service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ss.request.BookRequest;
import com.ss.request.PaginationRequest;
import com.ss.response.GeneralResponse;

@Service
public interface BookService {

	public ResponseEntity<GeneralResponse> findAll(PaginationRequest paginationRequest);

	public ResponseEntity<GeneralResponse> save(BookRequest request);

	public ResponseEntity<GeneralResponse> update(BookRequest request);

	public ResponseEntity<GeneralResponse> findById(Integer bookId);

	public ResponseEntity<GeneralResponse> delete(Integer bookId);

	public ResponseEntity<GeneralResponse> getByBookIdAndBookname(Integer bookid, String bookname);

}
