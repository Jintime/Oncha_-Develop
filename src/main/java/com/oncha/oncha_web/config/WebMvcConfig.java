package com.oncha.oncha_web.config;

import com.oncha.oncha_web.intercetor.SignUpCompletionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SignUpCompletionInterceptor())
            .excludePathPatterns("/css/**", "/file/**" ,"/img/**", "/js/**", "/video/**");
    }
}
