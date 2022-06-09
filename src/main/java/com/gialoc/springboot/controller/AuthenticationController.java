package com.gialoc.springboot.controller;

import com.gialoc.springboot.exception.InputDataException;
import com.gialoc.springboot.exception.ResourceNotFoundException;
import com.gialoc.springboot.payload.request.LoginRequest;
import com.gialoc.springboot.payload.request.RegisterRequest;
import com.gialoc.springboot.service.authentication.AuthenticationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * create new user
     *
     * @param request
     **/
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Valid RegisterRequest request, BindingResult binding) throws ResourceNotFoundException, InputDataException {
        if (binding.hasErrors()) {
            LOGGER.error("Đăng ký không thành công!");
            throw new InputDataException(binding);
        }
        String createUser = authenticationService.registerUser(request);
        return new ResponseEntity<>(createUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest requestLogin) throws ResourceNotFoundException {
        return new ResponseEntity<>(authenticationService.login(requestLogin), HttpStatus.OK);
    }
}
