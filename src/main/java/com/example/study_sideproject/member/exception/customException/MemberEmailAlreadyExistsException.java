package com.example.study_sideproject.member.exception.customException;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;

public class MemberEmailAlreadyExistsException extends CustomException {

	public MemberEmailAlreadyExistsException() {
		super(ErrorCode.DUPLICATE_EMAIL);
	}
}
