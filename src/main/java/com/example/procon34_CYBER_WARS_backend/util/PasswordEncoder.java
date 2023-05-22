package com.example.procon34_CYBER_WARS_backend.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    public String encodePassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA3-256");
            byte[] result = messageDigest.digest(password.getBytes());
            return String.format("%040x", new BigInteger(1, result));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
