package com.example.procon34_CYBER_WARS_backend.utility;

import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserManager {

    private final UserRepository userRepository;

    // ユーザーログイン判定
    public boolean isLoggedIn(HttpServletRequest httpServletRequest) {
        final HttpSession httpSession = httpServletRequest.getSession(false);
        // ユーザーセッションが存在しない場合
        if (httpSession == null) {
            return false;
        }
        setSession(httpSession);
        return true;
    }

    // ユーザーセッション設定
    public void setSession(HttpSession httpSession) {
        httpSession.setMaxInactiveInterval(60 * 60);
    }

    // ユーザー取得 by ユーザー名
    public Users getUserByName(final String name) {
        return userRepository.getUserByName(name);
    }

    // ユーザーID取得
    public int getUserId(final HttpServletRequest httpServletRequest) {
        return (int) httpServletRequest.getSession(false).getAttribute("sessionId");
    }

}
