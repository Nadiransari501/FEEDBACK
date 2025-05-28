package com.example.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedback.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
