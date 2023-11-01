package com.example.aviacase.service.exception;

public class HotelNotFoundException extends RuntimeException{
    public HotelNotFoundException(Long id) {
        super(String.format("Отель с ID %s не найден", id));
    }
}
