package com.deutschebahn.rest.data.exception;

import org.springframework.http.HttpStatus;

public class OperationOfficeAPIException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public OperationOfficeAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public OperationOfficeAPIException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
