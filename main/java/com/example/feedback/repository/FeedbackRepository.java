package com.example.feedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedback.model.Feedback;
import com.example.feedback.model.User;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUser(User user);
    List<Feedback> findAllByOrderByCreatedAtDesc();
}
