package com.oncha.oncha_web.security.jwt.redis.repository;

import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenInfo, String> {

}
