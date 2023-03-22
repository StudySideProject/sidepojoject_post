package com.example.study_sideproject.global.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

	private HttpStatus httpStatus;
	private String code;
	private String errorMessage;

	public ErrorResponse(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.errorMessage = message;
	}

	public ErrorResponse(ErrorCode code) {
		this.httpStatus = code.getHttpStatus();
		this.code = code.getCode();
		this.errorMessage = code.getMessage();
	}

}