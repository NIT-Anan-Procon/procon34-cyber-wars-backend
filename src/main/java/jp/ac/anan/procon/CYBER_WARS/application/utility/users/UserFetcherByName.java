package jp.ac.anan.procon.cyber_wars.application.utility.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import jp.ac.anan.procon.cyber_wars.domain.entity.Users;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.UsersRepository;

@Component
@RequiredArgsConstructor
public class UserFetcherByName {

  private final UsersRepository usersRepository;

  // ユーザー取得 by ユーザー名
  public Users fetchUserByName(final String name) {
    return usersRepository.fetchUserByName(name);
  }
}
