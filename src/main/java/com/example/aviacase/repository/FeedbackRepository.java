package com.example.aviacase.repository;

import com.example.aviacase.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUserIdAndTourId(Long userId, Long tourId);
}
