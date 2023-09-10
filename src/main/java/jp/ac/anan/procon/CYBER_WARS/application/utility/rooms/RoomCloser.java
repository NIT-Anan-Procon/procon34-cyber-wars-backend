package jp.ac.anan.procon.cyber_wars.application.utility.rooms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.RoomsRepository;

@Component
@RequiredArgsConstructor
public class RoomCloser {

  private final RoomsRepository roomsRepository;

  // ルーム閉鎖
  public void close(final int roomId) {
    roomsRepository.close(roomId);
  }
}
