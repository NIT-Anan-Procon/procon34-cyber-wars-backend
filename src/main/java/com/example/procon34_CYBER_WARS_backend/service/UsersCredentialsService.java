package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.users.Credentials.CheckUserLoginResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.Credentials.LoginUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.Credentials.LoginUserResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.utility.PasswordEncoder;
import com.example.procon34_CYBER_WARS_backend.utility.StringFormatter;
import com.example.procon34_CYBER_WARS_backend.utility.UserGetterByName;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersCredentialsService {

    private final UserGetterByName userGetterByName;
    private final StringFormatter stringFormatter;
    private final PasswordEncoder passwordEncoder;

    // ユーザーログイン
    public LoginUserResponse loginUser(final LoginUserRequest loginUserRequest,
            final HttpServletRequest httpServletRequest) {
        final Users user = userGetterByName.getUserByName(stringFormatter.formatString(loginUserRequest.getName()));

        // ユーザーが存在しない場合
        if (user == null) {
            return new LoginUserResponse(false);
        }

        // ユーザーパスワードが異なる場合
        if (!passwordEncoder.matchPassword(stringFormatter.formatString(loginUserRequest.getPassword()),
                user.getPassword())) {
            return new LoginUserResponse(false);
        }

        final HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("sessionId", user.getUserId());
        httpSession.setMaxInactiveInterval(60 * 60);

        return new LoginUserResponse(true);
    }

    // ユーザーログインチェック
    public CheckUserLoginResponse checkUserLogin(final HttpServletRequest httpServletRequest) {
        final HttpSession httpSession = httpServletRequest.getSession(false);

        // セッションが存在しない場合
        if (httpSession == null) {
            return new CheckUserLoginResponse(false);
        }

        httpSession.setMaxInactiveInterval(60 * 60);

        return new CheckUserLoginResponse(true);
    }

    // ユーザーログアウト
    public void logoutUser(final HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession(false).invalidate();
    }

}
