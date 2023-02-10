package com.ss.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.transaction.Transactional;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

@Transactional
@Data
@Entity
@NoArgsConstructor
@Table(name = "User_Details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "userId")
public class User {
	@Id
	@Column(name = "UserId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Column(name = "UserName")
	private String userName;
	@Column(name = "UserEmail")
	private String userEmail;
	@Column(name = "UserPassword")
	private String userPassword;
	@Column(name = "UserStauts")
	private String userStauts;
	@Column(name = "UserCreated_at")
	private LocalDateTime userCreated_at;
	@Column(name = "UserUpdated_at")
	private LocalDateTime userUpdated_at;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Book> book = new ArrayList<>();
	@ManyToMany(cascade = CascadeType.ALL,targetEntity = Project.class,fetch = FetchType.EAGER)
	private List<Project> project;

	public User(String userName, String userEmail, String userPassword, String userStauts, LocalDateTime userCreated_at,
			List<Book> book, List<Project> project) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userStauts = userStauts;
		this.userCreated_at = userCreated_at;
		this.book = book;
		this.project = project;

	}

	public User(int userId, String userName, String userEmail, String userPassword, String userStauts,
			LocalDateTime userCreated_at, List<Book> book, LocalDateTime userUpdated_at) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userStauts = userStauts;
		this.userUpdated_at = userUpdated_at;
		this.userCreated_at = userCreated_at;
		this.book = book;
		this.userId = userId;
	}
	public User(int userId, String userName, String userEmail, String userPassword, String userStauts,
			LocalDateTime userCreated_at, LocalDateTime userUpdated_at) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userStauts = userStauts;
		this.userUpdated_at = userUpdated_at;
		this.userCreated_at = userCreated_at;
		this.book = book;
		this.userId = userId;
	}

}
