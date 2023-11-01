package com.example.aviacase.controller;

import com.example.aviacase.dto.FeedbackDto;
import com.example.aviacase.model.User;
import com.example.aviacase.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

//    @PostMapping
//    public FeedbackDto createFeedback(@AuthenticationPrincipal User user, @RequestBody FeedbackDto feedbackDto){
//
//    }
}
