package jp.ac.anan.procon.CYBER_WARS.utility.credential;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionTimeoutUpdater {

    // ユーザーセッションタイムアウト更新
    public void updateSessionTimeout(final HttpSession httpSession) {
        httpSession.setMaxInactiveInterval(60 * 60);
    }

}
