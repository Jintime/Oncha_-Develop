package com.oncha.oncha_web.security.jwt.redis.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@Getter
@RedisHash(value = "user_token", timeToLive = 60 * 60 * 24)
@AllArgsConstructor
public class RefreshTokenInfo {
    @Id
    private String id;

    private String refreshToken;

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
