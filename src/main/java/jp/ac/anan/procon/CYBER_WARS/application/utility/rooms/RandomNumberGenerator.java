package jp.ac.anan.procon.cyber_wars.application.utility.rooms;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import jp.ac.anan.procon.cyber_wars.domain.entity.Rooms;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.RoomsRepository;

@Component
@RequiredArgsConstructor
public class RandomNumberGenerator {

  private final RoomsRepository roomsRepository;

  // 乱数生成
  public short generateRandomNumber() {
    short inviteId;

    while (true) {
      inviteId = (short) (new Random().nextInt(9000) + 1000);
      for (final Rooms room : roomsRepository.fetchActiveRooms()) {
        // 招待IDが等しい場合
        if (inviteId == room.getInviteId()) {
          continue;
        }
      }
      break;
    }

    return inviteId;
  }
}
