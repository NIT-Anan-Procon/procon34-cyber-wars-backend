package jp.ac.anan.procon.CYBER_WARS.utility.rooms;

import org.springframework.stereotype.Component;

import jp.ac.anan.procon.CYBER_WARS.repository.AllocationsRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomIdFetcher {

    private final AllocationsRepository allocationsRepository;

    // ルームID取得
    public int fetchRoomId(final int userId) {
        return allocationsRepository.fetchRoomId(userId);
    }

}
