package com.taskflow.service2.repository;

import com.taskflow.service2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUserID(Integer id);
    User findByEmail(String email);
    UserDetails findUserByEmail(String email);
    Boolean existsByEmail(String email);
}
