package com.ss.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class BookRequest {

	private int bookId;
	@NotBlank(message = "bookName shouldn't be Blank")
	@NotNull(message = "bookName shouldn't be null")
	private String bookName;
	@NotBlank(message = "price shouldn't be Blank")
	@NotNull(message = "price shouldn't be null")
	@Pattern(regexp ="\\d+", message = "Please inter only digit")
	private String price;
	@NotBlank(message = "userId shouldn't be Blank")
	@NotNull(message = "userId shouldn't be null")
	@NotEmpty(message = "User Id Not Empty")
	@Pattern(regexp = "\\d+", message = "Please Inter Only Numeric Value")
	private String userId;
	
}
