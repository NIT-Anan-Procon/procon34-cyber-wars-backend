package jp.ac.anan.procon.CYBER_WARS.utility.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // パスワードハッシュ
    public String hash(final String unhashedPassword) {
        return bCryptPasswordEncoder.encode(unhashedPassword);
    }

    // ユーザーパスワード一致判定
    public boolean arePasswordsEqual(final String requestPassword, final String databasePassword) {
        return bCryptPasswordEncoder.matches(requestPassword, databasePassword);
    }

}
