package jp.ac.anan.procon.CYBER_WARS.utility.users;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class UserIdFetcher {

    // ユーザーID取得
    public int fetchUserId(final HttpServletRequest httpServletRequest) {
        return (int) httpServletRequest.getSession(false).getAttribute("sessionId");
    }

}
