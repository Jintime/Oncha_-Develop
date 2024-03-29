package com.oncha.oncha_web.security.jwt.redis.exception;

import lombok.Getter;

@Getter
public class NotEqualRefreshTokenException extends CustomJwtException {

    private final String infoId;

    private final String jwtId;

    public NotEqualRefreshTokenException(String infoId, String jwtId) {
        super(String.format("NotEqual refresh to RefreshTokenInfo: %s from jwt %s:",infoId, jwtId));
        this.infoId = infoId;
        this.jwtId = jwtId;
    }
}
