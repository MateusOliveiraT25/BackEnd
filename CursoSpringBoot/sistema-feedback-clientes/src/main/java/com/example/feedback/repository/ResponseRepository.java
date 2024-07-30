package com.example.feedback.repository;

import com.example.feedback.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByFeedbackId(Long feedbackId);
}
