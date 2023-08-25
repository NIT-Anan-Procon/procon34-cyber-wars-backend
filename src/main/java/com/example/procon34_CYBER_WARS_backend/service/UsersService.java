package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordResponse;
import com.example.procon34_CYBER_WARS_backend.repository.UsersRepository;
import com.example.procon34_CYBER_WARS_backend.utility.PasswordEncoder;
import com.example.procon34_CYBER_WARS_backend.utility.StringFormatter;
import com.example.procon34_CYBER_WARS_backend.utility.UserManager;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserManager userManager;
    private final StringFormatter stringFormatter;
    private final PasswordEncoder passwordEncoder;

    // ユーザー登録
    public RegisterResponse register(final RegisterRequest registerRequest) {
        final String formattedName = stringFormatter.format(registerRequest.getName());

        // ユーザー名が空の場合
        if (formattedName.isEmpty()) {
            return new RegisterResponse(false);
        }

        // ユーザーが存在する場合
        if (userManager.getUserByName(formattedName) != null) {
            return new RegisterResponse(false);
        }

        final String formattedPassword = stringFormatter.format(registerRequest.getPassword());

        // ユーザーパスワードが空の場合
        if (formattedPassword.isEmpty()) {
            return new RegisterResponse(false);
        }

        usersRepository.register(formattedName, passwordEncoder.hash(formattedPassword));

        return new RegisterResponse(true);
    }

    // ユーザー名更新
    public UpdateNameResponse updateName(final UpdateNameRequest updateNameRequest,
            final HttpServletRequest httpServletRequest) {
        final String formattedName = stringFormatter.format(updateNameRequest.getName());

        // ユーザー名が空の場合
        if (formattedName.isEmpty()) {
            return new UpdateNameResponse(false);
        }

        // ユーザーが存在する場合
        if (userManager.getUserByName(formattedName) != null) {
            return new UpdateNameResponse(false);
        }

        usersRepository.updateName(userManager.getUserId(httpServletRequest), formattedName);

        return new UpdateNameResponse(true);
    }

    // ユーザーパスワード更新
    public UpdatePasswordResponse updatePassword(final UpdatePasswordRequest updatePasswordRequest,
            final HttpServletRequest httpServletRequest) {
        final String formattedPassword = stringFormatter.format(updatePasswordRequest.getPassword());

        // ユーザーパスワードが空の場合
        if (formattedPassword.isEmpty()) {
            return new UpdatePasswordResponse(false);
        }

        usersRepository.updatePassword(userManager.getUserId(httpServletRequest),
                passwordEncoder.hash(formattedPassword));

        return new UpdatePasswordResponse(true);
    }

}
