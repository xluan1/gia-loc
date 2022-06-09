package com.gialoc.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gialoc.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    User findByEmail(String email);

    @Query("select a from User a where a.email=:email or a.userName=:username")
    User findByUserNameAndEmail(String email, String username);
}
