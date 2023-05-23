package com.example.procon34_CYBER_WARS_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {

    @Bean
    public SecurityFilterChain configureHttp(HttpSecurity http) throws Exception {
        http.csrf().disable();
        return http.build();
    }

    // @Bean
    // BCryptPasswordEncoder passwordEncoder() {
    // return new BCryptPasswordEncoder();
    // }

}
