package jp.ac.anan.procon.cyber_wars.application.utility.credential;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoggedInChecker {

  private final SessionTimeoutUpdater sessionTimeoutUpdater;

  // ユーザーログイン判定
  public boolean isLoggedIn(final HttpServletRequest httpServletRequest) {
    final HttpSession httpSession = httpServletRequest.getSession(false);

    // ユーザーセッションが存在しない場合
    if (httpSession == null) {
      return false;
    }

    sessionTimeoutUpdater.updateSessionTimeout(httpSession);

    return true;
  }
}
