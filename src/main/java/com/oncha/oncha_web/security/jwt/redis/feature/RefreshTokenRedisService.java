package com.oncha.oncha_web.security.jwt.redis.feature;

import com.oncha.oncha_web.security.jwt.redis.exception.CustomJwtException;
import com.oncha.oncha_web.security.jwt.redis.exception.NotEqualRefreshTokenException;
import com.oncha.oncha_web.security.jwt.redis.exception.NotFoundRedisEntityException;
import com.oncha.oncha_web.security.jwt.redis.repository.RefreshTokenInfo;
import com.oncha.oncha_web.security.jwt.redis.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenRedisService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenInfo getRefresh(String tokenKey) throws NotFoundRedisEntityException {
        return refreshTokenRepository.findById(tokenKey)
                .orElseThrow(() -> new NotFoundRedisEntityException(tokenKey));
    }

    public void saveNewRefresh(String tokenKey, String refresh) {
        RefreshTokenInfo refreshTokenInfo;
        try {
            refreshTokenInfo = getRefresh(tokenKey);
            refreshTokenInfo.setRefreshToken(refresh);
        } catch (NotFoundRedisEntityException e) {
            refreshTokenInfo = new RefreshTokenInfo(e.getKey(), refresh);
        }
        refreshTokenRepository.save(refreshTokenInfo);
    }

    /**
     * redis에 있는 refreshtoken과 대조후 새로운 refreshtoken을 저장
     */
    public void processingResetRefreshToken(String tokenKey, String olderRefresh, String reGenRefresh) throws CustomJwtException {
        //redis 검색
        RefreshTokenInfo refreshTokenInfo = getRefresh(tokenKey);

        //redis에 있는 refresh와 refresh 대조
        if (!refreshTokenInfo.getRefreshToken().equals( olderRefresh)){
            throw new NotEqualRefreshTokenException(refreshTokenInfo.getRefreshToken(),  olderRefresh);
        }

        refreshTokenInfo.setRefreshToken(reGenRefresh);
        refreshTokenRepository.save(refreshTokenInfo);
    }

}
