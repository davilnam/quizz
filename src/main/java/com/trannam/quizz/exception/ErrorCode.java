package com.trannam.quizz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_EXISTED(1002, "User existed",  HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005,"User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "unauthenticated",  HttpStatus.UNAUTHORIZED),
    KYTHI_EXISTED(1008, "KyThi existed",  HttpStatus.BAD_REQUEST),
    KYTHI_NOT_EXISTED(1009,"kythi not existed", HttpStatus.NOT_FOUND);
    
    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}
