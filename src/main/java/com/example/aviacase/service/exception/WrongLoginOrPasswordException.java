package com.example.aviacase.service.exception;

public class WrongLoginOrPasswordException extends RuntimeException{
    public WrongLoginOrPasswordException(){
        super("Неверный логин или пароль");
    }
}
