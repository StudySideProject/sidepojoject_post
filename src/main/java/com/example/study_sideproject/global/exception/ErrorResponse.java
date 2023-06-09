package com.example.study_sideproject.global.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String code;
    private String errorMessage;


    public ErrorResponse(String s) {
        this.errorMessage = s;
    }

    public ErrorResponse(ErrorCode code) {
        this.code = code.getCode();
        this.errorMessage = code.getMessage();
    }


    public ErrorResponse(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
}