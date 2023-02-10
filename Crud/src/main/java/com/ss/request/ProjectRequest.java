package com.ss.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
@Data
public class ProjectRequest {
	private int projectId;
	@NotBlank(message = "projectName shouldn't be Blank")
	@NotNull(message = "projectName shouldn't be null")
	private String projectName;
	@NotBlank(message = "projectManager shouldn't be Blank")
	@NotNull(message = "projectManager shouldn't be null")
	private String projectManager;
	private List<Integer> userId;
}
