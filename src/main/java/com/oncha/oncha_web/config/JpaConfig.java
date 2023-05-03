package com.oncha.oncha_web.config;

import com.oncha.oncha_web.security.auth.PrincipalDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class JpaConfig {
    @Bean
    public AuditorAware<Long> auditorProvider() {
        // 람다를 이용
        return () -> Optional.of(
                ((PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getId()
        );

        // 익명 클래스를 이용
        /*
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                return Optional.of(UUID.randomUUID().toString());
            }
        };*/
    }
}
