package jp.ac.anan.procon.CYBER_WARS.utility.rooms;

import jp.ac.anan.procon.CYBER_WARS.repository.AllocationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomIdFetcher {

  private final AllocationsRepository allocationsRepository;

  // ルームID取得
  public int fetchRoomId(final int userId) {
    return allocationsRepository.fetchRoomId(userId);
  }
}
