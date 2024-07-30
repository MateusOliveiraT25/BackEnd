package com.example.feedback.service;

import com.example.feedback.model.Response;
import com.example.feedback.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    public Response saveResponse(Response response) {
        return responseRepository.save(response);
    }

    public List<Response> getAllResponses() {
        return responseRepository.findAll();
    }

    public List<Response> getResponsesByFeedbackId(Long feedbackId) {
        return responseRepository.findByFeedbackId(feedbackId);
    }
}
