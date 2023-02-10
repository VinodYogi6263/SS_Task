package com.ss.service;

import java.time.LocalDateTime;
import java.util.*;
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
import com.ss.entity.Project;
import com.ss.entity.User;
import com.ss.repository.BookRepository;
import com.ss.repository.ProjectRepository;
import com.ss.repository.UserRepository;
import com.ss.request.PaginationRequest;
import com.ss.request.UserRequest;
import com.ss.response.GeneralResponse;
import com.ss.response.PaginationResponse;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	ProjectRepository projectRepository;

	Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Override
	public ResponseEntity<GeneralResponse> save(UserRequest userRequest) {

		LocalDateTime current = LocalDateTime.now();

		User save = null;
		try {

			List<Project> project = projectRepository.findAllById(userRequest.getProjectId());
			List<Book> book = bookRepository.findAllById(userRequest.getBookId());
			User user = new User(userRequest.getUserName(), userRequest.getUserEmail(), userRequest.getUserPassword(),
					userRequest.getUserStauts(), current, book, project);
			save = userRepository.save(user);
			log.info(Message.save);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(save, Message.save, 200), HttpStatus.OK);
		}

		catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(save, Message.notSave, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<GeneralResponse> findAll(PaginationRequest paginationRequest) {
		try {
			Pageable p = PageRequest.of(Integer.parseInt(paginationRequest.getPageNumber()),
					Integer.parseInt(paginationRequest.getPageSize()), Sort.by(paginationRequest.getSortBy()));
			Page<User> pageUser = userRepository.findAll(p);
			List<User> listUser = pageUser.getContent();
			PaginationResponse paginationResponse = new PaginationResponse(pageUser.getNumber(), pageUser.getSize(),
					pageUser.getTotalPages(), pageUser.getTotalElements(), pageUser.isLast(), pageUser.isFirst());
			log.info(Message.found);
			return ResponseEntity
					.of(Optional.of(new GeneralResponse(listUser, Message.found, 200, paginationResponse)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notfound, 404),
					HttpStatus.NOT_FOUND);

		}
	}

	@Override
	public ResponseEntity<GeneralResponse> findById(Integer userID) {
		User user = null;
		try {

			Optional<User> findById = userRepository.findById(userID);
			user = findById.get();
			log.info(Message.found);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(user, Message.found, 200), HttpStatus.OK);

		} catch (Exception e) {

			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(user, Message.idNotFound, 404),
					HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<GeneralResponse> update(UserRequest userRequest) {
		User save = null;
		LocalDateTime current = LocalDateTime.now();
		try {

			Optional<User> findById = userRepository.findById(userRequest.getUserId());
			User user = new User(userRequest.getUserId(), userRequest.getUserName(), userRequest.getUserEmail(),
					userRequest.getUserPassword(), userRequest.getUserStauts(), findById.get().getUserCreated_at(),
					current);

			save = userRepository.save(user);
			log.info(Message.update);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(save, Message.update, 200), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(save, Message.notSave, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<GeneralResponse> delete(Integer userId) {

		if (userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			log.info(Message.delete);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.delete, 200), HttpStatus.OK);
		} else {

			log.info(Message.idNotFound);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.idNotFound, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<GeneralResponse> Searching(PaginationRequest paginationRequest) {
		try {
			Pageable p = PageRequest.of(Integer.parseInt(paginationRequest.getPageNumber()),
					Integer.parseInt(paginationRequest.getPageSize()), Sort.by(paginationRequest.getSortBy()));
			Page<User> pageUser = userRepository.findByuserNameOruserEmail(paginationRequest.getSearch(),
					paginationRequest.getSearch(), p);
			List<User> searching = pageUser.getContent();
			PaginationResponse paginationResponse = new PaginationResponse(pageUser.getNumber(), pageUser.getSize(),
					pageUser.getTotalPages(), pageUser.getTotalElements(), pageUser.isLast(), pageUser.isFirst());
			log.info(Message.found);
			return ResponseEntity
					.of(Optional.of(new GeneralResponse(searching, Message.found, 200, paginationResponse)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notfound, 404),
					HttpStatus.NOT_FOUND);

		}
	}
}