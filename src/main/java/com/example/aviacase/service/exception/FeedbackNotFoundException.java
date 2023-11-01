package com.example.aviacase.service.exception;

public class FeedbackNotFoundException extends RuntimeException{
    public FeedbackNotFoundException(Long id){
        super(String.format("Отзыв с ID %s не найден", id));
    }
}
