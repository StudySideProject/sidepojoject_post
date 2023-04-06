package com.example.study_sideproject.member.exception.customException;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;

public class MemberEamilAlreadyExistsException extends CustomException {

	public MemberEamilAlreadyExistsException() {
		super(ErrorCode.DUPLICATE_EMAIL);
	}
}
