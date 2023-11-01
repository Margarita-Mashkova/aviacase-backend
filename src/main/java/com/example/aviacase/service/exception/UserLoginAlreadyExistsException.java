package com.example.aviacase.service.exception;

public class UserLoginAlreadyExistsException extends RuntimeException{
    public UserLoginAlreadyExistsException(String login) {
        super(String.format("Пользователь с логином \"%s\" уже существует", login));
    }
}
