package com.example.aviacase.service.exception;

public class TourNotFoundException extends RuntimeException{
    public TourNotFoundException(Long id) {
        super(String.format("Тур с ID %s не найден", id));
    }
}
