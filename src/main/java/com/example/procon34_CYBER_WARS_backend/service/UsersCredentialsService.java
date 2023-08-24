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
        final LoginUserResponse loginUserResponse;

        // ユーザーが存在しない場合
        if (user == null) {
            loginUserResponse = LoginUserResponse.builder()
                    .success(false)
                    .build();
            return loginUserResponse;
        }

        // ユーザーパスワードが異なる場合
        if (!passwordEncoder.matchPassword(stringFormatter.formatString(loginUserRequest.getPassword()),
                user.getPassword())) {
            loginUserResponse = LoginUserResponse.builder()
                    .success(false)
                    .build();
            return loginUserResponse;
        }

        final HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("sessionId", user.getUser_id());
        httpSession.setMaxInactiveInterval(60 * 60);

        loginUserResponse = LoginUserResponse.builder()
                .success(true)
                .build();
        return loginUserResponse;
    }

    // ユーザーログインチェック
    public CheckUserLoginResponse checkUserLogin(final HttpServletRequest httpServletRequest) {
        final HttpSession httpSession = httpServletRequest.getSession(false);
        final CheckUserLoginResponse checkUserLoginResponse;

        // セッションが存在しない場合
        if (httpSession == null) {
            checkUserLoginResponse = CheckUserLoginResponse.builder()
                    .loggedIn(false)
                    .build();
            return checkUserLoginResponse;
        }

        httpSession.setMaxInactiveInterval(60 * 60);

        checkUserLoginResponse = CheckUserLoginResponse.builder()
                .loggedIn(true)
                .build();
        return checkUserLoginResponse;
    }

    // ユーザーログアウト
    public void logoutUser(final HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession(false).invalidate();
    }

}
