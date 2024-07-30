package com.example.feedback.controller;

import com.example.feedback.model.Feedback;
import com.example.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/")
    public String showFeedbackForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedbackForm";
    }

    @PostMapping("/submitFeedback")
    public String submitFeedback(@ModelAttribute Feedback feedback) {
        feedback.setSubmittedAt(LocalDateTime.now());
        feedbackService.saveFeedback(feedback);
        return "redirect:/feedbacks";
    }

    @GetMapping("/feedbacks")
    public String listFeedbacks(Model model) {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        model.addAttribute("feedbacks", feedbacks);
        return "feedbackList";
    }
}
