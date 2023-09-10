package jp.ac.anan.procon.cyber_wars.application.utility.allocations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.AllocationsRepository;

@Component
@RequiredArgsConstructor
public class OpponentNameFetcher {

  private final AllocationsRepository AllocationsRepository;

  // 相手ユーザー名取得
  public String fetchOpponentName(final int userId, final int roomId) {
    return AllocationsRepository.fetchOpponentName(userId, roomId);
  }
}
