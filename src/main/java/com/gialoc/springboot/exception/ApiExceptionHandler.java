package com.gialoc.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {
    private Object error(HttpStatus httpStatus, Object errorMessage, String errorDetail) {
        HashMap<String, Object> result = new HashMap();
        result.put("status", httpStatus);
        result.put("errorMessage", errorMessage);
        result.put("errorDetail", errorDetail);
        return result;
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleServiceException(ServiceException e) {
        Object err = error(e.getStatus(), e.getErrorMessage(), e.getErrorDetail());
        return new ResponseEntity<>(err, e.getStatus());
    }

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<Object> handleInputDataException(InputDataException e) {
        InputDataException exception = new InputDataException(e.getBindingResult());
        Object error = error(HttpStatus.BAD_REQUEST, exception.getErrors(), "Dữ liệu nhập vào không hợp lệ!");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
