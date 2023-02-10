package com.ss.response;

import lombok.Data;

@Data
public class GeneralResponse {

	private Object Data;
	private String massage;
	private int statusCode;
	private PaginationResponse paginationResponse;

	public GeneralResponse(Object data, String massage, int statusCode, PaginationResponse paginationResponse) {
		super();
		Data = data;
		this.massage = massage;
		this.statusCode = statusCode;
		this.paginationResponse = paginationResponse;
	}

	public GeneralResponse(Object data, String massage, int statusCode) {
		super();
		Data = data;
		this.massage = massage;
		this.statusCode = statusCode;
	}

	public GeneralResponse() {
		super();
	}

}
