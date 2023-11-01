package com.example.aviacase.service.exception;

public class PurchaseNotFoundException extends RuntimeException{
    public PurchaseNotFoundException(Long id) {
        super(String.format("Покупка с ID %s не найдена", id));
    }
}
