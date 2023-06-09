package com.oncha.oncha_web.exception._40x;

public class BadRequestException extends RuntimeException{

    public BadRequestException() {}

    public BadRequestException(String message) {
        super(message);
    }
}
