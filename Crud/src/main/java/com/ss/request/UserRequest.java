package com.ss.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequest {
	private int userId;
	@NotBlank(message = "username shouldn't be Blank")
	@NotNull(message = "username shouldn't be null")
	private String userName;
	@NotBlank(message = "email shouldn't be Blank")
	@NotNull(message = "email shouldn't be null")
	@Email(message = "invalid email address")
	private String userEmail;
	@NotBlank(message = "userPassword shouldn't be Blank")
	@NotNull(message = "userPassword shouldn't be null")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", message = "Minimum eight characters,special character must occur at least once, no whitespace allowed:")
	private String userPassword;
	@NotBlank(message = "userstatus shouldn't be Blank")
	@NotNull(message = "userstatus shouldn't be null")
	private String userStauts;

	@NotNull(message = "projectId shouldn't be null")
	@NotEmpty(message = "projectId Not Empty")
	private List<Integer> projectId;

	@NotNull(message = "bookId shouldn't be null")
	@NotEmpty(message = "bookId Not Empty")
	private List<Integer> bookId;
}
