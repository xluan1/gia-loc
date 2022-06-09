package com.gialoc.springboot.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class InputDataException extends Exception {
    private BindingResult bindingResult;
    private Map<String, String> errors;

    public InputDataException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
        this.errors = getAllErrors();
    }

    private Map<String, String> getAllErrors() {
        errors = new HashMap<>();
        bindingResult.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
