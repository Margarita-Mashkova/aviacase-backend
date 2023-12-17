package com.example.aviacase.controller;

import com.example.aviacase.AviacaseApplication;
import com.example.aviacase.dto.FeedbackDto;
import com.example.aviacase.mapper.FeedbackMapper;
import com.example.aviacase.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    FeedbackMapper feedbackMapper;

    @GetMapping("/feedbacks")
    public List<FeedbackDto> findAllFeedbacks(){
        return feedbackService.findAllFeedbacks()
                .stream()
                .map(feedback -> feedbackMapper.toFeedbackDto(feedback))
                .toList();
    }

    @PostMapping
    public FeedbackDto createFeedback(@RequestBody FeedbackDto feedbackDto){
        return feedbackMapper.toFeedbackDto(feedbackService.addFeedback(AviacaseApplication.authenticateUser,
                feedbackDto.getTourId(), feedbackDto.getText(), feedbackDto.getRate()));
    }

    @PutMapping("/{id}")
    public FeedbackDto editFeedback(@PathVariable Long id, @RequestBody FeedbackDto feedbackDto){
        return feedbackMapper.toFeedbackDto(feedbackService.updateFeedback(id, feedbackDto.getText(), feedbackDto.getRate()));
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id){
        feedbackService.deleteFeedback(id);
    }
}
