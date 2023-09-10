package jp.ac.anan.procon.CYBER_WARS.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jp.ac.anan.procon.CYBER_WARS.dto.user.credential.IsLoggedInResponse;
import jp.ac.anan.procon.CYBER_WARS.dto.user.credential.LogInRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.user.credential.LogInResponse;
import jp.ac.anan.procon.CYBER_WARS.entity.Users;
import jp.ac.anan.procon.CYBER_WARS.repository.UsersRepository;
import jp.ac.anan.procon.CYBER_WARS.utility.StringFormatter;
import jp.ac.anan.procon.CYBER_WARS.utility.credential.LoggedInChecker;
import jp.ac.anan.procon.CYBER_WARS.utility.credential.SessionTimeoutUpdater;
import jp.ac.anan.procon.CYBER_WARS.utility.users.PasswordEncoder;
import jp.ac.anan.procon.CYBER_WARS.utility.users.UserFetcherByName;
import jp.ac.anan.procon.CYBER_WARS.utility.users.UserIdFetcher;
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
