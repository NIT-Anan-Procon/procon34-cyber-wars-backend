package com.example.procon34_CYBER_WARS_backend.utility.rooms;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.repository.RoomsRepository;

import lombok.RequiredArgsConstructor;

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
