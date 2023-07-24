package com.oncha.oncha_web.exception._40x;

public class AccessDinedException extends ForbiddenException{

    public AccessDinedException (String key ,Long memberId, Class domain) {
        super(String.format("Access Denied id:%d, entity:%s, key:%s ", memberId, domain.getName(), key));
    }

    public AccessDinedException (Long key ,Long memberId, Class domain) {
        super(String.format("Access Denied id:%d, entity:%s, key:%d ", memberId, domain.getName(), key));
    }

    public AccessDinedException (String message) {
        super(message);
    }
}
