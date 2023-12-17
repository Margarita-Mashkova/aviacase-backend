package com.example.aviacase.repository;

import com.example.aviacase.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByCountryIgnoreCaseContainingAndStartDateGreaterThanEqual(String country, Date date);
    List<Tour> findByCountryIgnoreCaseContaining(String country);
    List<Tour> findByStartDateGreaterThanEqual(Date date);
}
