package com.example.study_sideproject.global.exception.customException;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;

public class CommentNotExistException extends CustomException {
    public CommentNotExistException() {
        super(ErrorCode.COMMENT_NOT_EXIST);
    }
}
