package com.ss.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ss.constant.Message;
import com.ss.entity.Project;
import com.ss.entity.User;
import com.ss.repository.ProjectRepository;
import com.ss.repository.UserRepository;
import com.ss.request.ProjectRequest;
import com.ss.response.GeneralResponse;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	UserRepository userRepository;

	private final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

	public ResponseEntity<GeneralResponse> save(ProjectRequest projectRequest) {

		try {
			List<User> userList = userRepository.findAllById(projectRequest.getUserId());
			Project project = new Project(projectRequest.getProjectName(), projectRequest.getProjectManager(),
					userList);
			project = projectRepository.save(project);
			log.info(Message.save);
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(project, Message.save, 200), HttpStatus.OK);
		}

		catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notSave, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<GeneralResponse> findAll() {

		try {
			List<Project> projectList = projectRepository.findAll();
			log.info(Message.found);
			return ResponseEntity.of(Optional.of(new GeneralResponse(projectList, Message.found, 200)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(null, Message.notfound, 404),
					HttpStatus.NOT_FOUND);
		}
	}

}
