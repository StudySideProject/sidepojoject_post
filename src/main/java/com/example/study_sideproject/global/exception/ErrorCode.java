package com.example.study_sideproject.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

	UNDEFINED_ERROR(HttpStatus.CONFLICT, "E00", "알 수 없는 에러 발생!!!!!!!"),

	// 회원가입
	DUPLICATE_EMAIL(HttpStatus.CONFLICT, "R01", "이미 사용중인 이메일입니다."),
	WRONG_PASSWORD_CONFIRM(HttpStatus.CONFLICT, "R02", "비밀번호가 일치하지 않습니다."),
	INVALID_ID_FORM(HttpStatus.BAD_REQUEST, "R03", "아이디 형식이 맞지 않습니다."),
	INVALID_PASSWORD_FORM(HttpStatus.BAD_REQUEST, "R04", "비밀번호 형식이 맞지 않습니다."),

	// 로그인
	WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "L01", "비밀번호가 일치하지 않습니다."),
	ID_NOT_EXIST(HttpStatus.BAD_REQUEST, "L02", "아이디가 존재하지 않습니다."),

	// 로그인 필요
	NEED_TO_LOGIN(HttpStatus.UNAUTHORIZED, "T01", "토큰이 없습니다."),
	WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED, "T02", "잘못된 타입의 토큰입니다."),
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "T03", "만료된 토큰입니다."),
	UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "T04", "지원되지 않는 토큰입니다."),
	WRONG_TOKEN(HttpStatus.UNAUTHORIZED, "T05", "시그니처가 일치하지 않는 토큰입니다."),


	// 게시글
	POST_NOT_EXIST(HttpStatus.BAD_REQUEST, "P01","게시글이 존재하지 않습니다."),
	NOT_AUTHOR(HttpStatus.UNAUTHORIZED,"P02","게시글 작성자가 아닙니다."),

	//댓글
	COMMENT_NOT_EXIST(HttpStatus.BAD_REQUEST, "C01", "댓글이 존재하지 않습니다."),
	NOT_COMMENTER(HttpStatus.UNAUTHORIZED, "C02", "댓글 작성자가 아닙니다."),
	POST_ID_NOT_MATCH_WITH_PARENTCOMMENT(HttpStatus.BAD_REQUEST, "C03", "부모 댓글의 게시글 번호와 일치하지 않습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;


	ErrorCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}
}
