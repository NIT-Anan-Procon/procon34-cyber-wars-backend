package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.users.Credentials.CheckUserLoginResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.Credentials.LoginUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.Credentials.LoginUserResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.utilities.PasswordEncoder;
import com.example.procon34_CYBER_WARS_backend.utilities.UserSearcherByName;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UsersCredentialsService {

    private final UserSearcherByName userSearcherByName;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersCredentialsService(UserSearcherByName userSearcherByName, PasswordEncoder passwordEncoder) {
        this.userSearcherByName = userSearcherByName;
        this.passwordEncoder = passwordEncoder;
    }

    private final LoginUserResponse loginUserResponse = new LoginUserResponse();
    private final CheckUserLoginResponse checkUserLoginResponse = new CheckUserLoginResponse();

    // ユーザーログイン
    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest, HttpServletRequest httpServletRequest) {
        loginUserRequest.setName(loginUserRequest.getName().trim());
        Users users = userSearcherByName.searchUserByName(loginUserRequest.getName());
        // ユーザーが存在しない場合
        if (users == null) {
            loginUserResponse.setSuccess(false);
            return loginUserResponse;
        }
        loginUserRequest.setPassword(loginUserRequest.getPassword().trim());
        // ユーザーパスワードが異なる場合
        if (!passwordEncoder.checkPassword(loginUserRequest.getPassword(), users.getPassword())) {
            loginUserResponse.setSuccess(false);
            return loginUserResponse;
        }
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("sessionId", users.getUserId());
        httpSession.setMaxInactiveInterval(60 * 60);
        loginUserResponse.setSuccess(true);
        return loginUserResponse;
    }

    // ユーザーログインチェック
    public CheckUserLoginResponse checkUserLogin(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession(false);
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
    public void logoutUser(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        httpSession.invalidate();
    }

}
