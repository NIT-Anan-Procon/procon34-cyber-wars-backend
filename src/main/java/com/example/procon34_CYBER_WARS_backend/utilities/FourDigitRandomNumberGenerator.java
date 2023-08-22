package com.example.procon34_CYBER_WARS_backend.utilities;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.repository.UtilitiesMapper;

@Component
public class FourDigitRandomNumberGenerator {

    private final UtilitiesMapper utilitiesMapper;

    @Autowired
    public FourDigitRandomNumberGenerator(UtilitiesMapper utilitiesMapper) {
        this.utilitiesMapper = utilitiesMapper;
    }

    // 4桁乱数生成
    public short generateFourDigitRandomNumber() {
        Random random = new Random();
        List<Rooms> rooms = utilitiesMapper.getValidInviteIds();
        short inviteId;
        while (true) {
            inviteId = (short) (random.nextInt(9000) + 1000);
            for (Rooms room : rooms) {
                if (room.getInviteId() == inviteId) {
                    continue;
                }
            }
            break;
        }
        return inviteId;
    }

}
