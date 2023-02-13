package com.ss.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ss.constant.Message;
import com.ss.request.BookRequest;
import com.ss.request.PaginationRequest;
import com.ss.response.GeneralResponse;
import com.ss.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/book")
@RestController
@Tag(description = "Book resources that provides access to availabe Book data", name = "Book Resource")
public class BookController {
	@Autowired
	BookService bookService;

	@Operation(summary = "Get Book", description = "Provides all available Book list")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.found),
			@ApiResponse(responseCode = "400", description = "", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@GetMapping("/")
	public ResponseEntity<GeneralResponse> findAll(@RequestBody @Valid PaginationRequest paginationRequest) {
		return bookService.findAll(paginationRequest);

	}

	@Operation(summary = "Save Book", description = "Save the Book Objest")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.save),
			@ApiResponse(responseCode = "400", description = Message.notSave, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@PostMapping("/save")
	public ResponseEntity<GeneralResponse> save(@RequestBody @Valid BookRequest bookRequest) {
		return bookService.save(bookRequest);

	}

	@Operation(summary = "Update Book", description = "Update the Book Objest")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.update),
			@ApiResponse(responseCode = "400", description = Message.notUpdate, content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@PutMapping("/update")
	public ResponseEntity<GeneralResponse> update(@RequestBody @Valid BookRequest bookRequest) {
		return bookService.update(bookRequest);

	}

	@Operation(summary = "Get Book", description = "Provides  Book")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.found),
			@ApiResponse(responseCode = "400", description = "", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@GetMapping("/book/{id}")
	public ResponseEntity<GeneralResponse> getById(@PathVariable("id") Integer id) {
		return bookService.findById(id);
	}

	@Operation(summary = "Delete Book", description = "Delete Book Id   ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.delete),
			@ApiResponse(responseCode = "400", description = "", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notDelete, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@DeleteMapping("/delete")
	public ResponseEntity<GeneralResponse> deleteById(@RequestParam("id") Integer deleteid) {
		return bookService.delete(deleteid);

	}

	@Operation(summary = "Get Book", description = "get data Book Id   ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.found),
			@ApiResponse(responseCode = "400", description = "", content = {
					@Content(examples = { @ExampleObject(value = "") }) }),
			@ApiResponse(responseCode = "404", description = Message.notfound, content = {
					@Content(examples = { @ExampleObject(value = "") }) }) })
	@GetMapping("/getbyBookIdAndBookName")
	public ResponseEntity<GeneralResponse> get(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		return bookService.getByBookIdAndBookname(id, name);

	}
}
