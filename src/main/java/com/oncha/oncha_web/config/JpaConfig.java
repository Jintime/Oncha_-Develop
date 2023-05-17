package com.oncha.oncha_web.config;

import com.oncha.oncha_web.security.auth.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@Slf4j
public class JpaConfig {
    @Bean
    public AuditorAware<Long> auditorProvider() {

        // 익명 클래스를 이용
        return new AuditorAware<Long>() {
            @Override
            public Optional<Long> getCurrentAuditor() {
                try {
                    return Optional.of(((PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
                }catch (NullPointerException e) {
                    log.debug("사용자 특정에 실패했습니다.");
                    return Optional.empty();
                }
            }
        };
    }
}
