package com.justsayit.core.security.config;

import com.justsayit.core.jwt.JwtTokenProvider;
import com.justsayit.core.jwt.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final String ROLE_MEMBER = "MEMBER";
    private final String ROLE_ADMIN = "ADMIN";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            http.sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf()
                    .disable()
                    .formLogin()
                    .disable() // 시큐리티의 기본 로그인폼 비활성화
                    .httpBasic() // JWT토큰을 사용하여 http Bearer 사용할 것이므로 비활성화;
                    .disable();
            http.exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler);
            http.apply(new MyCustomDsl())
                    .and()
                    .authorizeRequests()
                    // MEMBER
                    .antMatchers("/members/login", "/members/check")
                    .permitAll()
                    .antMatchers("/members/**")
                    .authenticated()
                    // STORY
                    .antMatchers("/stories/**")
                    .authenticated();
            return http.build();
        } catch (Exception e) {
            log.info("security config 에러 발생", e);
            throw new RuntimeException(e);
        }
    }

    private class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) {
            http.addFilter(corsFilter)
                    .addFilterBefore(
                            new JwtAuthenticationFilter(jwtTokenProvider),
                            UsernamePasswordAuthenticationFilter.class);
        }
    }
}
