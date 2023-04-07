package com.example.study_sideproject.member.exception.customException;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;

public class WrongPasswordConfirmException extends CustomException {

	public WrongPasswordConfirmException() {
		super(ErrorCode.WRONG_PASSWORD_CONFIRM);
	}
}