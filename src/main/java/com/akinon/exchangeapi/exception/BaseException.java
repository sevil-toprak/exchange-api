package com.akinon.exchangeapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final String message;
    private final int code;
    private final HttpStatus httpStatus;

    public BaseException(String message, int code, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}