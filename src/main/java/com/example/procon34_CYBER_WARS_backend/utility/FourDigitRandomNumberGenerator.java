package com.example.procon34_CYBER_WARS_backend.utility;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.repository.UtilityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FourDigitRandomNumberGenerator {

    private final UtilityRepository utilityRepository;

    // 4桁乱数生成
    public short generateFourDigitRandomNumber() {
        short inviteId;
        while (true) {
            inviteId = (short) (new Random().nextInt(9000) + 1000);
            for (final Rooms room : utilityRepository.getNotStartedRooms()) {
                // 招待IDが等しい場合
                if (inviteId == room.getInvite_id()) {
                    continue;
                }
            }
            break;
        }
        return inviteId;
    }

}
