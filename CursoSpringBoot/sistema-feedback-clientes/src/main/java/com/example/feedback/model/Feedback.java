package com.example.feedback.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String comment;
    private int rating;
    private LocalDateTime submittedAt;

    // Getters e Setters
}
