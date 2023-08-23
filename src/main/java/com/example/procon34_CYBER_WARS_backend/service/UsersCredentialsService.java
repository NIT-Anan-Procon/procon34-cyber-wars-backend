package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;

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
public class UsersCredentialsService {

    private final UserGetterByName userGetterByName;
    private final StringFormatter stringFormatter;
    private final PasswordEncoder passwordEncoder;

    private final LoginUserResponse loginUserResponse = new LoginUserResponse();
    private final CheckUserLoginResponse checkUserLoginResponse = new CheckUserLoginResponse();

    // ユーザーログイン
    public LoginUserResponse loginUser(final LoginUserRequest loginUserRequest,
            final HttpServletRequest httpServletRequest) {
        final String formattedName = stringFormatter.formatString(loginUserRequest.getName());
        loginUserRequest.setName(formattedName);

        final Users users = userGetterByName.getUserByName(loginUserRequest.getName());

        // ユーザーが存在しない場合
        if (users == null) {
            loginUserResponse.setSuccess(false);
            return loginUserResponse;
        }

        final String formattedPassword = stringFormatter.formatString(loginUserRequest.getPassword());
        loginUserRequest.setPassword(formattedPassword);

        // ユーザーパスワードが異なる場合
        if (!passwordEncoder.matchPassword(loginUserRequest.getPassword(), users.getPassword())) {
            loginUserResponse.setSuccess(false);
            return loginUserResponse;
        }

        final HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("sessionId", users.getUser_id());
        httpSession.setMaxInactiveInterval(60 * 60);

        loginUserResponse.setSuccess(true);
        return loginUserResponse;
    }

    // ユーザーログインチェック
    public CheckUserLoginResponse checkUserLogin(final HttpServletRequest httpServletRequest) {
        final HttpSession httpSession = httpServletRequest.getSession(false);

        // セッションが存在しない場合
        if (httpSession == null) {
            checkUserLoginResponse.setLogged_in(false);
            return checkUserLoginResponse;
        }

        httpSession.setMaxInactiveInterval(60 * 60);

        checkUserLoginResponse.setLogged_in(true);
        return checkUserLoginResponse;
    }

    // ユーザーログアウト
    public void logoutUser(final HttpServletRequest httpServletRequest) {
        final HttpSession httpSession = httpServletRequest.getSession(false);
        httpSession.invalidate();
    }

}
