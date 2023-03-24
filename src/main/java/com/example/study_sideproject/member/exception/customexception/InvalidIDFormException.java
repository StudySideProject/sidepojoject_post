package com.example.study_sideproject.member.exception.customexception;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;

public class InvalidIDFormException  extends CustomException {

	public InvalidIDFormException() {
		super(ErrorCode.INVALID_ID_FORM);
	}

}
