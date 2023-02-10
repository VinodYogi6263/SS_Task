package com.ss.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ss.constant.Message;
import com.ss.entity.Book;
import com.ss.entity.User;
import com.ss.repository.BookRepository;
import com.ss.repository.UserRepository;
import com.ss.request.BookRequest;
import com.ss.request.PaginationRequest;
import com.ss.response.GeneralResponse;

import com.ss.response.PaginationResponse;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	UserRepository userRepository;

	Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Override
	public ResponseEntity<GeneralResponse> findById(Integer bookId) {
		Book book = null;

		try {
			Optional<Book> findById = bookRepository.findById(bookId);
			book = findById.get();
			log.info(Message.found);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(book, Message.found, 200), HttpStatus.OK);
		} catch (Exception e) {

			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(book, Message.idNotFound, 404),
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<GeneralResponse> findAll(PaginationRequest paginationRequest) {
		try {
			Pageable p = PageRequest.of(Integer.parseInt(paginationRequest.getPageNumber()),
					Integer.parseInt(paginationRequest.getPageSize()), Sort.by(paginationRequest.getSortBy()));
			Page<Book> pageBook = bookRepository.findAll(p);
			List<Book> listBook = pageBook.getContent();
			PaginationResponse paginationResponse = new PaginationResponse(pageBook.getNumber(), pageBook.getSize(),
					pageBook.getTotalPages(), pageBook.getTotalElements(), pageBook.isLast(), pageBook.isFirst());
			log.info(Message.found);
			return ResponseEntity
					.of(Optional.of(new GeneralResponse(listBook, Message.found, 200, paginationResponse)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notfound, 404),
					HttpStatus.NOT_FOUND);

		}
	}

	@Override
	public ResponseEntity<GeneralResponse> update(BookRequest bookRequest) {

		User user = null;
		Book save = null;
		try {
			user = userRepository.findById(Integer.parseInt(bookRequest.getUserId())).get();
			Book book = new Book(bookRequest.getBookName(), Integer.parseInt(bookRequest.getPrice()), user,
					bookRequest.getBookId());
			save = bookRepository.save(book);
			log.info(Message.update);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(save, Message.update, 200), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(save, Message.notUpdate, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<GeneralResponse> delete(Integer bookId) {

		if (bookRepository.existsById(bookId)) {
			bookRepository.deleteById(bookId);
			log.info(Message.delete);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.delete, 200), HttpStatus.OK);
		} else {
			log.info(Message.idNotFound);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.idNotFound, 404),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<GeneralResponse> getByBookIdAndBookname(Integer bookid, String bookname) {
		Book book = null;
		try {
			Optional<Book> findById = bookRepository.getByBookIdAndBookname(bookid, bookname);
			book = findById.get();
			log.info(Message.found);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(book, Message.found, 200), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(book, Message.notfound, 404),
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<GeneralResponse> save(BookRequest bookRequest) {
		Book save = null;
		try {
			User user = userRepository.findById(Integer.parseInt(bookRequest.getUserId())).get();

			Book book = new Book(bookRequest.getBookName(), Integer.parseInt(bookRequest.getPrice()), user);
			save = bookRepository.save(book);
			log.info(Message.save);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(save, Message.save, 200), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(save, Message.notSave, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
