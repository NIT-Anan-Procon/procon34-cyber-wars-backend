package com.example.procon34_CYBER_WARS_backend.utility;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.mapper.UtilityMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FourDigitRandomNumberGenerator {

    private final UtilityMapper utilityMapper;

    // 4桁乱数生成
    public short generateFourDigitRandomNumber() {
        short inviteId;
        while (true) {
            final Random random = new Random();
            inviteId = (short) (random.nextInt(9000) + 1000);
            final List<Rooms> rooms = utilityMapper.getActiveRooms();
            System.out.println(rooms);
            for (final Rooms room : rooms) {
                // 招待IDが等しい場合
                System.out.println(room);
                if (room.getInvite_id() == inviteId) {
                    continue;
                }
            }
            break;
        }
        return inviteId;
    }

}
