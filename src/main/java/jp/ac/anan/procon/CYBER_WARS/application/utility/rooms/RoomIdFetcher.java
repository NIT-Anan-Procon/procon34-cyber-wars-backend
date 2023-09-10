package jp.ac.anan.procon.cyber_wars.application.utility.rooms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.AllocationsRepository;

@Component
@RequiredArgsConstructor
public class RoomIdFetcher {

  private final AllocationsRepository allocationsRepository;

  // ルームID取得
  public int fetchRoomId(final int userId) {
    return allocationsRepository.fetchRoomId(userId);
  }
}
