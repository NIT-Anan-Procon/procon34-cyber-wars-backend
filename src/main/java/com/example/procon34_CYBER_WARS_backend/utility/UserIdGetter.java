package com.example.procon34_CYBER_WARS_backend.utility;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class UserIdGetter {

    // ユーザーID取得
    public int getUserId(final HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        return (int) httpSession.getAttribute("sessionId");
    }

}
