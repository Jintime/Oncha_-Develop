package com.oncha.oncha_web.domain.user.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_MANAGER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
