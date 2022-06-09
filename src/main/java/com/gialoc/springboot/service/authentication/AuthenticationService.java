package com.gialoc.springboot.service.authentication;

import com.gialoc.springboot.exception.ResourceNotFoundException;
import com.gialoc.springboot.payload.request.LoginRequest;
import com.gialoc.springboot.payload.request.RegisterRequest;

import java.util.Map;

public interface AuthenticationService {
    Map<String, String> login(LoginRequest login) throws ResourceNotFoundException;

    String registerUser(RegisterRequest createUser) throws ResourceNotFoundException;

    String registerEmployee(RegisterRequest createUser) throws ResourceNotFoundException;
}
