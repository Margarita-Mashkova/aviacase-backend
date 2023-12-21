package com.example.aviacase.service;

import com.example.aviacase.model.Feedback;
import com.example.aviacase.model.Tour;
import com.example.aviacase.model.User;
import com.example.aviacase.repository.FeedbackRepository;
import com.example.aviacase.service.exception.FeedbackNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private TourService tourService;

    @Transactional(readOnly = true)
    public List<Feedback> findAllFeedbacks(){
        return feedbackRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Feedback findFeedback(Long id){
        final Optional<Feedback> feedback = feedbackRepository.findById(id);
        return feedback.orElseThrow(() -> new FeedbackNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public boolean allowCreateFeedback(Long userId, Long tourId){
        var feedbacks = feedbackRepository.findByUserIdAndTourId(userId, tourId);
        if (feedbacks.size() > 0){
            return false;
        }else return true;
    }

    @Transactional
    public Feedback addFeedback(User user, Long tourId, String text, int rate){
        final Tour tour = tourService.findTour(tourId);
        final Feedback feedback = new Feedback(text, rate, user, tour);
        Date date = new Date();
        feedback.setDate(date);
        return  feedbackRepository.save(feedback);
    }

    @Transactional
    public Feedback updateFeedback(Long id, String text, int rate){
        final Feedback feedback = findFeedback(id);
        feedback.setRate(rate);
        feedback.setText(text);
        return feedbackRepository.save(feedback);
    }

    @Transactional
    public Feedback deleteFeedback(Long id){
        final Feedback feedback = findFeedback(id);
        feedbackRepository.delete(feedback);
        return feedback;
    }

    @Transactional
    public void deleteAllFeedbacks() {
        feedbackRepository.deleteAll();
    }

}
