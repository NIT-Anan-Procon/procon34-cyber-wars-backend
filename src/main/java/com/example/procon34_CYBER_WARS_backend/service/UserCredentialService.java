package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.user.credential.IsLoggedInResponse;
import com.example.procon34_CYBER_WARS_backend.dto.user.credential.LogInRequest;
import com.example.procon34_CYBER_WARS_backend.dto.user.credential.LogInResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersRepository;
import com.example.procon34_CYBER_WARS_backend.utility.StringFormatter;
import com.example.procon34_CYBER_WARS_backend.utility.credential.LoggedInChecker;
import com.example.procon34_CYBER_WARS_backend.utility.credential.SessionTimeoutUpdater;
import com.example.procon34_CYBER_WARS_backend.utility.users.PasswordEncoder;
import com.example.procon34_CYBER_WARS_backend.utility.users.UserFetcherByName;
import com.example.procon34_CYBER_WARS_backend.utility.users.UserIdFetcher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCredentialService {

    private final UsersRepository usersRepository;
    private final UserFetcherByName userFetcherByName;
    private final UserIdFetcher userIdFetcher;
    private final LoggedInChecker loggedInChecker;
    private final SessionTimeoutUpdater sessionTimeoutUpdater;
    private final StringFormatter stringFormatter;
    private final PasswordEncoder passwordEncoder;

    // ユーザーログイン
    public LogInResponse logIn(final LogInRequest logInRequest, final HttpServletRequest httpServletRequest) {
        final Users user = userFetcherByName.fetchUserByName(stringFormatter.format(logInRequest.getName()));

        // ユーザーが存在しない場合
        if (user == null) {
            return new LogInResponse(false);
        }

        // ユーザーパスワードが異なる場合
        if (!passwordEncoder.arePasswordsEqual(stringFormatter.format(logInRequest.getPassword()),
                user.getPassword())) {
            return new LogInResponse(false);
        }

        final HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("sessionId", user.getUserId());
        sessionTimeoutUpdater.updateSessionTimeout(httpSession);

        return new LogInResponse(true);
    }

    // ユーザーログインチェック
    public IsLoggedInResponse isLoggedIn(final HttpServletRequest httpServletRequest) {
        final boolean isLoggedIn = loggedInChecker.isLoggedIn(httpServletRequest);

        // ユーザーログインをしていない場合
        if (!isLoggedIn) {
            return new IsLoggedInResponse(isLoggedIn, null);
        }

        return new IsLoggedInResponse(isLoggedIn,
                usersRepository.fetchUserByUserId(userIdFetcher.fetchUserId(httpServletRequest)).getName());
    }

    // ユーザーログアウト
    public void logOut(final HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession(false).invalidate();
    }

}
