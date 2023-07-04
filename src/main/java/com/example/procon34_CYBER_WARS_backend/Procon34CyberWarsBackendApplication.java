package com.example.procon34_CYBER_WARS_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class Procon34CyberWarsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Procon34CyberWarsBackendApplication.class, args);
	}

}
