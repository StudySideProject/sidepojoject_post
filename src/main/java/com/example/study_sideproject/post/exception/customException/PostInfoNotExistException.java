package com.example.study_sideproject.post.exception.customException;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;

public class PostInfoNotExistException extends CustomException {
	public PostInfoNotExistException() {
		super(ErrorCode.POST_NOT_EXIST);
	}
}
