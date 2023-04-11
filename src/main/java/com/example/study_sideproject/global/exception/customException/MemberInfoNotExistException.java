package com.example.study_sideproject.global.exception.customException;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;

public class MemberInfoNotExistException extends CustomException {
	public MemberInfoNotExistException() {
		super(ErrorCode.NEED_TO_LOGIN);
	}
}
