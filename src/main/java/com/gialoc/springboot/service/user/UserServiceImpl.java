package com.gialoc.springboot.service.user;

import com.gialoc.springboot.model.User;
import com.gialoc.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findAccountByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllAccounts() {
        return userRepository.findAll();
    }

    @Override
    public User getCurrentUser() {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuth != null) {
            return findAccountByEmail(currentAuth.getName());
        }
        return null;
    }
}
