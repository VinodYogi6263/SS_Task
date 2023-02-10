package com.ss.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ss.entity.AddressBlock;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressRequest {
	private Integer addressId;
	@NotBlank(message = "city shouldn't be Blank")
	@NotNull(message = "city shouldn't be null")
	private String city;
	@NotBlank(message = "state shouldn't be Blank")
	@NotNull(message = "state shouldn't be null")
	private String state;
	@NotBlank(message = "zipCode shouldn't be Blank")
	@NotNull(message = "zipCode shouldn't be null")
	@Pattern(regexp = "\\d+", message = "Please Inter Only Numeric Value")
	private String zipCode;
	@NotBlank(message = "country shouldn't be Blank")
	@NotNull(message = "country shouldn't be null")
	private String country;
	private AddressBlock addressBlock;

}
