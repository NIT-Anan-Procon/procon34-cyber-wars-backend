package com.example.procon34_CYBER_WARS_backend.utilities;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class UserIdGetter {

    public int getUserId(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        return (int) httpSession.getAttribute("sessionId");
    }

}
