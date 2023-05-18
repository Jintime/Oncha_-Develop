package com.oncha.oncha_web.security.jwt.redis.exception;

public class CustomJwtException extends RuntimeException{

    public CustomJwtException(String message) {
        super(message);
    }
}
