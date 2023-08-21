package com.example.procon34_CYBER_WARS_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.procon34_CYBER_WARS_backend.dto.Users.SearchUserByNameRequest;

@Configuration
public class Config {

    @Bean
    public SecurityFilterChain configureHttp(HttpSecurity http) throws Exception {
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public SearchUserByNameRequest searchUserByNameRequest() {
        return new SearchUserByNameRequest();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
