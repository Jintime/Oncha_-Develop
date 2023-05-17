package com.oncha.oncha_web.exception;

import lombok.Getter;

@Getter
public class RedisEntityNotFoundException extends RuntimeException{

    private final String key;
    private final String expect;


    public RedisEntityNotFoundException(String key) {
        super(String.format("RedisEntityNotFound key:%s", key));
        this.key = key;
        this.expect = "";
    }

    public RedisEntityNotFoundException(String key, String expect) {
        super(String.format("RedisEntityNotFound key:%s, expect:%s", key, expect));
        this.key = key;
        this.expect = expect;
    }


}
