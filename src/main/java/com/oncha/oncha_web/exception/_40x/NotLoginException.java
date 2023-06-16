package com.oncha.oncha_web.exception._40x;

public class NotLoginException extends UnauthorizedException {
    public NotLoginException() {}

    public NotLoginException(String message) {
        super(message);
    }
}
