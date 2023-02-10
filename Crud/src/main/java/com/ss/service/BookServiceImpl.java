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

	private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Override
	public ResponseEntity<GeneralResponse> findById(Integer bookId) {

		try {
			Optional<Book> bookOptional = bookRepository.findById(bookId);
			Book book = bookOptional.get();
			log.info(Message.found);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(book, Message.found, 200), HttpStatus.OK);
		} catch (Exception e) {

			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.idNotFound, 404),
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<GeneralResponse> findAll(PaginationRequest paginationRequest) {
		try {
			Pageable p = PageRequest.of(Integer.parseInt(paginationRequest.getPageNumber()),
					Integer.parseInt(paginationRequest.getPageSize()), Sort.by(paginationRequest.getSortBy()));
			Page<Book> bookPage = bookRepository.findAll(p);
			List<Book> bookList = bookPage.getContent();
			PaginationResponse paginationResponse = new PaginationResponse(bookPage.getNumber(), bookPage.getSize(),
					bookPage.getTotalPages(), bookPage.getTotalElements(), bookPage.isLast(), bookPage.isFirst());
			log.info(Message.found);
			return ResponseEntity
					.of(Optional.of(new GeneralResponse(bookList, Message.found, 200, paginationResponse)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notfound, 404),
					HttpStatus.NOT_FOUND);

		}
	}

	@Override
	public ResponseEntity<GeneralResponse> update(BookRequest bookRequest) {

		try {
			if (!userRepository.existsById(Integer.parseInt(bookRequest.getUserId()))) {
				return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.idNotFound, 200),
						HttpStatus.NOT_FOUND);
			}
			User user = userRepository.findById(Integer.parseInt(bookRequest.getUserId())).get();
			Book book = new Book(bookRequest.getBookName(), Integer.parseInt(bookRequest.getPrice()), user,
					bookRequest.getBookId());
			book = bookRepository.save(book);
			log.info(Message.update);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(book, Message.update, 200), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notUpdate, 500),
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

		try {
			Optional<Book> bookOptional = bookRepository.getByBookIdAndBookname(bookid, bookname);
			Book book = bookOptional.get();
			log.info(Message.found);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(book, Message.found, 200), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notfound, 404),
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<GeneralResponse> save(BookRequest bookRequest) {
		try {
			User user = userRepository.findById(Integer.parseInt(bookRequest.getUserId())).get();

			Book book = new Book(bookRequest.getBookName(), Integer.parseInt(bookRequest.getPrice()), user);
			book = bookRepository.save(book);
			log.info(Message.save);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(book, Message.save, 200), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notSave, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
