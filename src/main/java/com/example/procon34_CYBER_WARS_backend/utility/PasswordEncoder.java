package com.example.procon34_CYBER_WARS_backend.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // パスワードエンコード
    public String hashPassword(final String unhashedPassword) {
        return bCryptPasswordEncoder.encode(unhashedPassword);
    }

    // パスワードマッチ
    public boolean matchPassword(final String requestPassword, final String databasePassword) {
        return bCryptPasswordEncoder.matches(requestPassword, databasePassword);
    }

}
