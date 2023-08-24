package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersRepository;
import com.example.procon34_CYBER_WARS_backend.utility.PasswordEncoder;
import com.example.procon34_CYBER_WARS_backend.utility.StringFormatter;
import com.example.procon34_CYBER_WARS_backend.utility.UserGetterByName;
import com.example.procon34_CYBER_WARS_backend.utility.UserIdGetter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserGetterByName userGetterByName;
    private final UserIdGetter userIdGetter;
    private final StringFormatter stringFormatter;
    private final PasswordEncoder passwordEncoder;

    // ユーザー登録
    public RegisterUserResponse registerUser(final RegisterUserRequest registerUserRequest) {
        final String formattedName = stringFormatter.formatString(registerUserRequest.getName());
        final RegisterUserResponse registerUserResponse;

        // ユーザー名が空の場合
        if (formattedName.isEmpty()) {
            registerUserResponse = RegisterUserResponse.builder()
                    .success(false)
                    .build();
            return registerUserResponse;
        }

        // ユーザーが存在する場合
        if (userGetterByName.getUserByName(formattedName) != null) {
            registerUserResponse = RegisterUserResponse.builder()
                    .success(false)
                    .build();
            return registerUserResponse;
        }

        final String formattedPassword = stringFormatter.formatString(registerUserRequest.getPassword());

        // ユーザーパスワードが空の場合
        if (formattedPassword.isEmpty()) {
            registerUserResponse = RegisterUserResponse.builder()
                    .success(false)
                    .build();
            return registerUserResponse;
        }

        final Users user = Users.builder()
                .name(formattedName)
                .password(passwordEncoder.hashPassword(formattedPassword))
                .build();
        usersRepository.registerUser(user);

        registerUserResponse = RegisterUserResponse.builder()
                .success(true)
                .build();
        return registerUserResponse;
    }

    // ユーザー名更新
    public UpdateNameResponse updateName(final UpdateNameRequest updateNameRequest,
            final HttpServletRequest httpServletRequest) {
        final String formattedName = stringFormatter.formatString(updateNameRequest.getName());
        final UpdateNameResponse updateNameResponse;

        // ユーザー名が空の場合
        if (formattedName.isEmpty()) {
            updateNameResponse = UpdateNameResponse.builder()
                    .success(false)
                    .build();
            return updateNameResponse;
        }

        // ユーザーが存在する場合
        if (userGetterByName.getUserByName(formattedName) != null) {
            updateNameResponse = UpdateNameResponse.builder()
                    .success(false)
                    .build();
            return updateNameResponse;
        }

        final Users user = Users.builder()
                .user_id(userIdGetter.getUserId(httpServletRequest))
                .name(formattedName)
                .build();
        usersRepository.updateName(user);

        updateNameResponse = UpdateNameResponse.builder()
                .success(true)
                .build();
        return updateNameResponse;
    }

    // ユーザーパスワード更新
    public UpdatePasswordResponse updatePassword(final UpdatePasswordRequest updatePasswordRequest,
            final HttpServletRequest httpServletRequest) {
        final String formattedPassword = stringFormatter.formatString(updatePasswordRequest.getPassword());
        final UpdatePasswordResponse updatePasswordResponse;

        // ユーザーパスワードが空の場合
        if (formattedPassword.isEmpty()) {
            updatePasswordResponse = UpdatePasswordResponse.builder()
                    .success(false)
                    .build();
            return updatePasswordResponse;
        }

        final Users user = Users.builder()
                .user_id(userIdGetter.getUserId(httpServletRequest))
                .password(passwordEncoder.hashPassword(formattedPassword))
                .build();
        usersRepository.updatePassword(user);

        updatePasswordResponse = UpdatePasswordResponse.builder()
                .success(true)
                .build();
        return updatePasswordResponse;
    }

}
