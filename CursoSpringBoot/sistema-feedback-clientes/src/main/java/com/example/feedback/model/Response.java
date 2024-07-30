package com.example.feedback.model;

import javax.persistence.*;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    private String responseText;

    // Getters e Setters
}
