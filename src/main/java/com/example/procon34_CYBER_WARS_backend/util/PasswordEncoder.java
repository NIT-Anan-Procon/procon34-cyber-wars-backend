package com.example.procon34_CYBER_WARS_backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // パスワードエンコード
    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    // パスワードチェック
    public boolean checkPassword(String password, String hashedPassword) {
        return bCryptPasswordEncoder.matches(password, hashedPassword);
    }

}
