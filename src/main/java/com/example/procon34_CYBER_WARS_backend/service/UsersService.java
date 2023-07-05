package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.UsersRequest;
import com.example.procon34_CYBER_WARS_backend.dto.UsersResponse;
import com.example.procon34_CYBER_WARS_backend.dto.UsersUpdateRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.util.PasswordEncoder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UsersService {

    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersMapper usersMapper, PasswordEncoder passwordEncoder) {
        this.usersMapper = usersMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // ユーザー登録
    public UsersResponse register(UsersRequest usersRequest, UsersResponse usersResponse) {
        Users users = usersMapper.search(usersRequest);
        if (users != null) {
            usersResponse.setSuccess(false);
        } else {
            String hashedPassword = passwordEncoder.encodePassword(usersRequest.getPassword());
            usersRequest.setPassword(hashedPassword);
            usersMapper.register(usersRequest);
            usersResponse.setSuccess(true);
        }
        return usersResponse;
    }

    // ユーザー名変更
    public UsersResponse updateName(UsersUpdateRequest usersUpdateRequest, UsersResponse usersResponse,
            HttpServletRequest request) {
        usersUpdateRequest.setName(usersUpdateRequest.getName().trim());
        if (usersUpdateRequest.getName().isEmpty()) {
            usersResponse.setSuccess(false);
        } else {
            UsersRequest usersRequest = new UsersRequest();
            usersRequest.setName(usersUpdateRequest.getName());
            Users users = usersMapper.search(usersRequest);
            if (users != null) {
                usersResponse.setSuccess(false);
            } else {
                HttpSession session = request.getSession(false);
                usersUpdateRequest.setUserId((Long) session.getAttribute("sessionId"));
                usersMapper.updateName(usersUpdateRequest);
                usersResponse.setSuccess(true);
            }
        }
        return usersResponse;
    }

    // ユーザーパスワード変更
    public UsersResponse updatePassword(UsersUpdateRequest usersUpdateRequest, UsersResponse usersResponse,
            HttpServletRequest request) {
        usersUpdateRequest.setPassword(usersUpdateRequest.getPassword().trim());
        if (usersUpdateRequest.getPassword().isEmpty()) {
            usersResponse.setSuccess(false);
        } else {
            HttpSession session = request.getSession(false);
            usersUpdateRequest.setUserId((Long) session.getAttribute("sessionId"));
            String hashedPassword = passwordEncoder.encodePassword(usersUpdateRequest.getPassword());
            usersUpdateRequest.setPassword(hashedPassword);
            usersMapper.updatePassword(usersUpdateRequest);
            usersResponse.setSuccess(true);
        }
        return usersResponse;
    }

    // ユーザーログイン
    public UsersResponse login(UsersRequest usersRequest, UsersResponse usersResponse, HttpServletRequest request) {
        Users users = usersMapper.search(usersRequest);
        if (users == null) {
            usersResponse.setSuccess(false);
        } else if (!passwordEncoder.checkPassword(usersRequest.getPassword(), users.getPassword())) {
            usersResponse.setSuccess(false);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("sessionId", users.getUserId());
            session.setMaxInactiveInterval(20);
            usersResponse.setSuccess(true);
        }
        return usersResponse;
    }

    // ユーザーログアウト
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
    }

}
