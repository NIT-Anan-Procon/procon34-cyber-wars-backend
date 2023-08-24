package com.example.procon34_CYBER_WARS_backend.utility;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class UserIdGetter {

    // ユーザーID取得
    public int getUserId(final HttpServletRequest httpServletRequest) {
        return (int) httpServletRequest.getSession(false).getAttribute("sessionId");
    }

}
