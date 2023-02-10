package com.ss.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ss.request.BookRequest;
import com.ss.request.PaginationRequest;
import com.ss.response.GeneralResponse;
import com.ss.service.BookService;

@RestController
public class BookController {
	@Autowired
	BookService bookService;

	@GetMapping("/")
	public ResponseEntity<GeneralResponse> findAll(@RequestBody @Valid PaginationRequest paginationRequest) {
		return bookService.findAll(paginationRequest);

	}

	@PostMapping("/save")
	public ResponseEntity<GeneralResponse> save(@RequestBody @Valid BookRequest bookRequest) {
		return bookService.save(bookRequest);

	}

	@PutMapping("/update")
	public ResponseEntity<GeneralResponse> update(@RequestBody @Valid BookRequest bookRequest) {
		return bookService.update(bookRequest);

	}

	@GetMapping("/book/{id}")
	public ResponseEntity<GeneralResponse> getById(@PathVariable("id") Integer id) {
		return bookService.findById(id);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<GeneralResponse> deleteById(@RequestParam("id") Integer deleteid) {
		return bookService.delete(deleteid);

	}

	@GetMapping("/getbyBookIdAndBookName")
	public ResponseEntity<GeneralResponse> get(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		return bookService.getByBookIdAndBookname(id, name);

	}
}
