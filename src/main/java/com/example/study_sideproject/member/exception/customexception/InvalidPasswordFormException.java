package com.example.study_sideproject.member.exception.customexception;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;

public class InvalidPasswordFormException  extends CustomException {

	public InvalidPasswordFormException() {
		super(ErrorCode.INVALID_PASSWORD_FORM);
	}
}
