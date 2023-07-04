package com.example.procon34_CYBER_WARS_backend.util;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginChecker {

    public boolean checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return true;
        } else {
            return false;
        }
    }

}
