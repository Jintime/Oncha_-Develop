package com.oncha.oncha_web.exception._40x;

public class EntityNotFoundException extends BadRequestException {
    public EntityNotFoundException () {}

    public EntityNotFoundException (String key, Class domain) {
        super(String.format("key: %s, entity:%s not Found", key, domain.getName()));
    }

    public EntityNotFoundException (Long key, Class domain) {
        super(String.format("key: %d, entity:%s not Found", key, domain.getName()));
    }
}
