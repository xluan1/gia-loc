package com.gialoc.springboot.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {
    private final HttpStatus status;
    private final String errorMessage;
    private final String errorDetail;

    public ServiceException(HttpStatus status, String errorMessage, String errorDetail) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }

    public ServiceException(String message, Throwable cause, HttpStatus status, String errorMessage, String errorDetail) {
        super(message, cause);
        this.status = status;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

}
