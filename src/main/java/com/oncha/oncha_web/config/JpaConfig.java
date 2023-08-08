package com.oncha.oncha_web.config;

import com.oncha.oncha_web.security.auth.PrincipalDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@Slf4j
public class JpaConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public AuditorAware<Long> auditorProvider() {

        // 익명 클래스를 이용
        return new AuditorAware<Long>() {
            @Override
            public Optional<Long> getCurrentAuditor() {
                try {
                    Object object = SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal();
                    PrincipalDetails principalDetails = (PrincipalDetails) object;
                    return Optional.of(principalDetails.getId());
                } catch (Exception e) {
                    log.debug("사용자 특정에 실패했습니다.");
                    return Optional.empty();
                }
            }
        };
    }
}
