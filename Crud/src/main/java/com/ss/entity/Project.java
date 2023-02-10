package com.ss.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name = "Project_Details")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "projectId")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projectId;
	private String projectName;
	private String projectManager;
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,targetEntity = User.class)
	@JoinColumn(name = "UserId")
	private List<User> user;

	public Project(String projectName, String projectManager, List<User> user) {
		super();
		this.projectName = projectName;
		this.projectManager = projectManager;
		this.user = user;
	}

	public Project() {
		super();
	}

}
