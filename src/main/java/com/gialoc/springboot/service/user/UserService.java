package com.gialoc.springboot.service.user;

import com.gialoc.springboot.exception.ResourceNotFoundException;
import com.gialoc.springboot.model.User;

import java.util.List;

public interface UserService {
    User findAccountByEmail(String email);

    List<User> findAllAccounts();

    User getCurrentUser();
}
