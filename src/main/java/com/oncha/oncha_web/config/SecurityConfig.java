package com.oncha.oncha_web.config;
import com.oncha.oncha_web.security.filter.JwtFilter;
import com.oncha.oncha_web.security.jwt.JwtAccessDeniedHandler;
import com.oncha.oncha_web.security.jwt.JwtAuthenticationEntryPoint;
import com.oncha.oncha_web.security.jwt.JwtTokenUtil;
import com.oncha.oncha_web.security.jwt.TokenProvider;
import com.oncha.oncha_web.security.oauth.OAuthLoginFailureHandler;
import com.oncha.oncha_web.security.oauth.OAuthLoginSuccessHandler;
import com.oncha.oncha_web.security.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuthLoginSuccessHandler oAuthLoginSuccessHandler;
    private final OAuthLoginFailureHandler oAuthLoginFailureHandler;
    private final PrincipalOauth2UserService principalOauth2UserService;
    private final TokenProvider tokenProvider;

    private final JwtAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtTokenUtil jwtTokenUtil;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring().requestMatchers("/h2-console/**","/favicon.ico", "/static/**");
    }

    @Bean
    protected SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        String LOGIN_PATH = "/login";

        http.csrf().disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/company/**").hasRole("MANAGER")
                    .anyRequest().permitAll()
                .and()

                .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler)
                .and()

                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .formLogin().disable()
                .httpBasic().disable()

                .oauth2Login()
                .loginPage(LOGIN_PATH)
                .userInfoEndpoint()
                    .userService(principalOauth2UserService)
                .and()
                    .successHandler(oAuthLoginSuccessHandler)
                    .failureHandler(oAuthLoginFailureHandler);

        http.addFilterBefore(new JwtFilter(tokenProvider, jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(new RefererFilter(), JwtFilter.class);
        return http.build();
    }

}
