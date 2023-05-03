package com.oncha.oncha_web.util;

import com.oncha.oncha_web.security.auth.PrincipalDetails;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


@NoArgsConstructor
public class SecurityUtil {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    @Deprecated
    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //홀딩되는 시점은 jwtFilter에서 request가 들어올때 authentication 객체를 저장해서 사용하게 됨
        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다");
            return Optional.empty();
        }

        String userName = null;
        if (authentication.getPrincipal() instanceof PrincipalDetails principalDetails) {
//            userName = principalDetails.getUsername();
            userName = null;
         } else if (authentication.getPrincipal() instanceof String) {
            userName = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(userName);
    }

    public static Optional<Long> getCurrentId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //홀딩되는 시점은 jwtFilter에서 request가 들어올때 authentication 객체를 저장해서 사용하게 됨
        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다");
            return Optional.empty();
        }

        Long id = null;
        if (authentication.getPrincipal() instanceof PrincipalDetails principalDetails) {
            id = principalDetails.getId();
        }

        return Optional.ofNullable(id);
    }

    public static boolean isAdmin() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //홀딩되는 시점은 jwtFilter에서 request가 들어올때 authentication 객체를 저장해서 사용하게 됨
        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다");
            return false;
        }

        if (authentication.getPrincipal() instanceof PrincipalDetails principalDetails) {
            return principalDetails.getAuthorities().contains(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ROLE_ADMIN";
                }
            });
        }

        return false;
    }
}
