package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.UsersRequest;
import com.example.procon34_CYBER_WARS_backend.dto.UsersResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.util.PasswordEncoder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;
    PasswordEncoder passwordEncoder;

    // ユーザー登録
    public UsersResponse register(UsersRequest usersRequest, UsersResponse usersResponse) {
        Users users = usersMapper.search(usersRequest);
        if (users == null) {
            String hashedPassword = passwordEncoder.encodePassword(usersRequest.getPassword());
            usersRequest.setPassword(hashedPassword);
            usersMapper.register(usersRequest);
            usersResponse.setSuccess(true);
        } else {
            usersResponse.setSuccess(false);
        }
        return usersResponse;
    }

    // ユーザー情報変更
    public UsersResponse update(UsersRequest usersRequest, UsersResponse usersResponse, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println(session.getAttribute("key"));
            if (session.getAttribute("key") == "1") {
                usersResponse.setSuccess(true);
            } else {
                usersResponse.setSuccess(false);
            }
        }
        return usersResponse;
    }

    // ユーザーログイン
    public UsersResponse login(UsersRequest usersRequest, UsersResponse usersResponse, HttpServletRequest request) {
        Users users = usersMapper.search(usersRequest);
        if (users != null && passwordEncoder.checkPassword(usersRequest.getPassword(), users.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("key", users.getUserId());
            session.setMaxInactiveInterval(20);
            System.out.println(session.getAttribute("key"));
            usersResponse.setSuccess(true);
        } else {
            usersResponse.setSuccess(false);
        }
        return usersResponse;
    }

    // ユーザーログアウト

}
