package com.example.aviacase.service.exception;

public class TourNotFoundException extends RuntimeException{
    public TourNotFoundException(Long id) {
        super(String.format("Tour with ID %s not found", id));
    }
}
