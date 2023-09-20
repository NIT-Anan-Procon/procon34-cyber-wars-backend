package jp.ac.anan.procon.cyber_wars.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {
  @Bean
  public SecurityFilterChain configureHttp(final HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable();
    return httpSecurity.build();
  }

  @Bean
  public BCryptPasswordEncoder bcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
