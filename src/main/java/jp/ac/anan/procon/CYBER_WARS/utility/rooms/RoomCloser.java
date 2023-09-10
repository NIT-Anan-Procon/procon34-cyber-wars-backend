package jp.ac.anan.procon.CYBER_WARS.utility.rooms;

import org.springframework.stereotype.Component;

import jp.ac.anan.procon.CYBER_WARS.repository.RoomsRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomCloser {

    private final RoomsRepository roomsRepository;

    // ルーム閉鎖
    public void close(final int roomId) {
        roomsRepository.close(roomId);
    }

}
