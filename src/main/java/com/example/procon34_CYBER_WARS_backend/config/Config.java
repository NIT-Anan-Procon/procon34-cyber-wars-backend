package com.example.procon34_CYBER_WARS_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class Config {

    @Bean
    public void configureHttp(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

}
