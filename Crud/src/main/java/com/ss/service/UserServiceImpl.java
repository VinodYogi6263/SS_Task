package com.ss.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

	private final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Override
	public ResponseEntity<GeneralResponse> save(UserRequest userRequest) {

		try {
			for (Integer bookId : userRequest.getBookId()) {
				if (!bookRepository.existsById(bookId))
					return new ResponseEntity<GeneralResponse>(
							new GeneralResponse(null, Message.bookIdNotFound + " " + bookId, 404),
							HttpStatus.NOT_FOUND);

			}
			for (Integer projectId : userRequest.getProjectId()) {
				if (!projectRepository.existsById(projectId))
					return new ResponseEntity<GeneralResponse>(
							new GeneralResponse(null, Message.projectIdNotFound + " " + projectId, 404),
							HttpStatus.NOT_FOUND);

			}

			List<Project> projectList = projectRepository.findAllById(userRequest.getProjectId());

			List<Book> bookList = bookRepository.findAllById(userRequest.getBookId());
			LocalDateTime current = LocalDateTime.now();
			User user = new User(userRequest.getUserName(), userRequest.getUserEmail(), userRequest.getUserPassword(),
					userRequest.getUserStauts(), current, bookList, projectList);
			user.setUserId(0);
			user = userRepository.save(user);
			log.info(Message.save);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(user, Message.save, 200), HttpStatus.OK);
		}

		catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notSave, 500),
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

		try {

			Optional<User> findById = userRepository.findById(userID);
			User user = findById.get();
			log.info(Message.found);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(user, Message.found, 200), HttpStatus.OK);

		} catch (Exception e) {

			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.idNotFound, 404),
					HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<GeneralResponse> update(UserRequest userRequest) {

		try {

			LocalDateTime current = LocalDateTime.now();
			User user = new User(userRequest.getUserId(), userRequest.getUserName(), userRequest.getUserEmail(),
					userRequest.getUserPassword(), userRequest.getUserStauts(), current);

			user = userRepository.save(user);
			log.info(Message.update);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(user, Message.update, 200), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notSave, 500),
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
			List<User> userList = pageUser.getContent();
			PaginationResponse paginationResponse = new PaginationResponse(pageUser.getNumber(), pageUser.getSize(),
					pageUser.getTotalPages(), pageUser.getTotalElements(), pageUser.isLast(), pageUser.isFirst());
			log.info(Message.found);
			return ResponseEntity
					.of(Optional.of(new GeneralResponse(userList, Message.found, 200, paginationResponse)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notfound, 404),
					HttpStatus.NOT_FOUND);

		}
	}
}