package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateUserNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateUserNameResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateUserPasswordRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateUserPasswordResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.utilities.PasswordEncoder;
import com.example.procon34_CYBER_WARS_backend.utilities.UserIdGetter;
import com.example.procon34_CYBER_WARS_backend.utilities.UserSearcherByName;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsersService {

    private final UsersMapper usersMapper;
    private final UserSearcherByName userSearcherByName;
    private final PasswordEncoder passwordEncoder;
    private final UserIdGetter userIdGetter;

    @Autowired
    public UsersService(UsersMapper usersMapper, UserSearcherByName userSearcherByName, PasswordEncoder passwordEncoder,
            UserIdGetter userIdGetter) {
        this.usersMapper = usersMapper;
        this.userSearcherByName = userSearcherByName;
        this.passwordEncoder = passwordEncoder;
        this.userIdGetter = userIdGetter;
    }

    private final RegisterUserResponse registerUserResponse = new RegisterUserResponse();
    private final UpdateUserNameResponse updateUserNameResponse = new UpdateUserNameResponse();
    private final UpdateUserPasswordResponse updateUserPasswordResponse = new UpdateUserPasswordResponse();

    // ユーザー登録
    public RegisterUserResponse registerUser(RegisterUserRequest registerUsersRequest) {
        registerUsersRequest.setName(registerUsersRequest.getName().trim());
        Users users = userSearcherByName.searchUserByName(registerUsersRequest.getName());
        // ユーザーが存在する場合
        if (users != null) {
            registerUserResponse.setSuccess(false);
            return registerUserResponse;
        }
        registerUsersRequest.setPassword(registerUsersRequest.getPassword().trim());
        String hashedPassword = passwordEncoder.encodePassword(registerUsersRequest.getPassword());
        registerUsersRequest.setPassword(hashedPassword);
        usersMapper.registerUser(registerUsersRequest);
        registerUserResponse.setSuccess(true);
        return registerUserResponse;
    }

    // ユーザー名更新
    public UpdateUserNameResponse updateUserName(UpdateUserNameRequest updateUserNameRequest,
            HttpServletRequest httpServletRequest) {
        updateUserNameRequest.setName(updateUserNameRequest.getName().trim());
        // ユーザー名が空の場合
        if (updateUserNameRequest.getName().isEmpty()) {
            updateUserNameResponse.setSuccess(false);
            return updateUserNameResponse;
        }
        Users users = userSearcherByName.searchUserByName(updateUserNameRequest.getName());
        // ユーザーが存在する場合
        if (users != null) {
            updateUserNameResponse.setSuccess(false);
            return updateUserNameResponse;
        }
        int userId = userIdGetter.getUserId(httpServletRequest);
        updateUserNameRequest.setUserId(userId);
        usersMapper.updateUserName(updateUserNameRequest);
        updateUserNameResponse.setSuccess(true);
        return updateUserNameResponse;
    }

    // ユーザーパスワード更新
    public UpdateUserPasswordResponse updateUserPassword(UpdateUserPasswordRequest updateUserPasswordRequest,
            HttpServletRequest httpServletRequest) {
        updateUserPasswordRequest.setPassword(updateUserPasswordRequest.getPassword().trim());
        // ユーザーパスワードが空の場合
        if (updateUserPasswordRequest.getPassword().isEmpty()) {
            updateUserPasswordResponse.setSuccess(false);
            return updateUserPasswordResponse;
        }
        int userId = userIdGetter.getUserId(httpServletRequest);
        updateUserPasswordRequest.setUserId(userId);
        String hashedPassword = passwordEncoder.encodePassword(updateUserPasswordRequest.getPassword());
        updateUserPasswordRequest.setPassword(hashedPassword);
        usersMapper.updateUserPassword(updateUserPasswordRequest);
        updateUserPasswordResponse.setSuccess(true);
        return updateUserPasswordResponse;
    }

}
