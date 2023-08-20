package com.studyolle17.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration      //스프링 설정 클래스
@EnableWebSecurity  //웹 보안 활성화, 웹 보안 설정 재정의
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        RequestMatcher[] matchers = {
                new MvcRequestMatcher(introspector, "/"),
                new MvcRequestMatcher(introspector, "/login"),
                new MvcRequestMatcher(introspector, "/sign-up"),
                new MvcRequestMatcher(introspector, "/check-email"),
                new MvcRequestMatcher(introspector, "/check-email-token"),
                new MvcRequestMatcher(introspector, "/email-login"),
                new MvcRequestMatcher(introspector, "/check-email-login"),
                new MvcRequestMatcher(introspector, "/login-link"),
                new MvcRequestMatcher(introspector, "/profile/*")
        };

        //보안 요청에 대한 권한 및 역활 설정
        http.authorizeRequests()
                //배열에 있는 경로들에 대한 모든 요청을 허용
                .requestMatchers(matchers).permitAll()
                //지저오딘 경로외의 모든 요청은 인증된 사용자만 접근할 수 있도록 함
                .anyRequest().authenticated();

        // POST에 대한 별도 조건을 설정하려면 추가 코드 필요

        return http.build();
    }
}
