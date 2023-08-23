package com.example.procon34_CYBER_WARS_backend.utility;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.mapper.UtilityMapper;

@Component
public class FourDigitRandomNumberGenerator {

    private final UtilityMapper utilityMapper;

    @Autowired
    public FourDigitRandomNumberGenerator(final UtilityMapper utilityMapper) {
        this.utilityMapper = utilityMapper;
    }

    // 4桁乱数生成
    public short generateFourDigitRandomNumber() {
        short inviteId;
        while (true) {
            final Random random = new Random();
            inviteId = (short) (random.nextInt(9000) + 1000);
            final List<Rooms> rooms = utilityMapper.getValidInviteIds();
            System.out.println(rooms);
            // if (rooms == null) {
            // break;
            // }
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
