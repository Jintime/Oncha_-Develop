package com.oncha.oncha_web.feature;

import lombok.Data;

@Data
public class ResponseType<T> {

    private T data;

    public ResponseType(T data) {
        this.data = data;
    }
}
