package com.example.procon34_CYBER_WARS_backend.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PasswordEncoder(final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // パスワードエンコード
    public String encodePassword(final String unhashedPassword) {
        return bCryptPasswordEncoder.encode(unhashedPassword);
    }

    // パスワードマッチ
    public boolean matchPassword(final String requestPassword, final String databasePassword) {
        return bCryptPasswordEncoder.matches(requestPassword, databasePassword);
    }

}
