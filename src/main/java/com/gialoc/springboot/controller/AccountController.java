package com.gialoc.springboot.controller;

import com.gialoc.springboot.model.User;
import com.gialoc.springboot.service.user.UserPrincipalService;
import com.gialoc.springboot.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class AccountController {
    @Autowired
    private UserServiceImpl accountService;

    @GetMapping("/info")
    public ResponseEntity<User> getUserInfo(@AuthenticationPrincipal UserPrincipalService user) {
        User account = accountService.findAccountByEmail(user.getUsername());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
