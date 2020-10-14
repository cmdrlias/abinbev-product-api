package com.abinbev.product.api.exception;

import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {

    private HttpStatus status;

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ResponseException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() { return status; }
}
