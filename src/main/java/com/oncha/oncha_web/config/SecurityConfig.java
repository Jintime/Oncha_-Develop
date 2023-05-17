package com.oncha.oncha_web.config;
import com.oncha.oncha_web.config.filter.JwtFilter;
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

    @Bean
    protected SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeHttpRequests()
                    .requestMatchers("/user/**").authenticated()
                    .requestMatchers("/manager/**").hasAnyRole("ADMIN","MANAGER")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
                .and()

                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .formLogin().disable()
//                .loginPage("/loginForm")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/") 이거 있으면 loginSuccessHandler 무시된다.
//                .and()
                .httpBasic().disable()

                .oauth2Login()
                .userInfoEndpoint()
                    .userService(principalOauth2UserService)
                .and()
                    .successHandler(oAuthLoginSuccessHandler)
                    .failureHandler(oAuthLoginFailureHandler);

        http.addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
