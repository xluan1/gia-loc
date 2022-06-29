package com.gialoc.springboot;

import com.gialoc.springboot.controller.AuthenticationController;
import com.gialoc.springboot.exception.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import com.gialoc.springboot.payload.request.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

class WatchStoreApplicationTests {
    @Autowired
    AuthenticationController authenticationController;

    @Test
    void testAuthenticationController() {
        LoginRequest request = new LoginRequest();
        request.setEmail("loc@gmail.com");
        request.setPassword("123");

        try {
            Map<String, String> user = authenticationController.login(request).getBody();
            assertNotNull(user);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }

}
