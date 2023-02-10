package com.ss.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequest {
	@NotBlank(message = "pageNumber shouldn't be Blank")
	@NotNull(message = "pageNumber shouldn't be null")
    @NotEmpty( message = "pageNumber shouldn't be empty")
	@Pattern(regexp ="\\d+", message = "Please Inter Only Numeric Value")
	private String pageNumber;
	@NotBlank(message = "pageSize shouldn't be Blank")
	@NotNull(message = "pageSize shouldn't be null")
    @NotEmpty( message = "pageSize shouldn't be empty")
	@Pattern(regexp = "\\d+", message = "Please Inter Only Numeric Value")
	private String pageSize;
	@NotBlank(message = "sortBy shouldn't be Blank")
	@NotNull(message = "sortBy shouldn't be null")
	private String sortBy;
	private String search;

}
