package jp.ac.anan.procon.CYBER_WARS.utility.users;

import jp.ac.anan.procon.CYBER_WARS.entity.Users;
import jp.ac.anan.procon.CYBER_WARS.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFetcherByName {

  private final UsersRepository usersRepository;

  // ユーザー取得 by ユーザー名
  public Users fetchUserByName(final String name) {
    return usersRepository.fetchUserByName(name);
  }
}
