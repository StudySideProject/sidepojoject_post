package com.example.study_sideproject.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

	/* 400 error: BAD_REQUEST */

	/* 401 error: UNAUTHORIZED */

	/* 403 error: FORBIDDEN */

	/* 404 error: Not Found */

	/* 409 error: CONFLICT */
	BAD_PASSWORD_CONFIRM(HttpStatus.CONFLICT.value(), "Password and PasswordConfirm don't match", "비밀번호와 비밀번호확인이 다릅니다."),
	DUPLICATE_MEMBER_EMAIL(HttpStatus.CONFLICT.value(), "Member is duplicated", "이 Email을 사용하는 이용자가 이미 존재합니다.");

	/* 500 error: INTERNAL_SERVER_ERROR */

	private final Integer httpStatus;
	private final String message;
	private final String detail;
}