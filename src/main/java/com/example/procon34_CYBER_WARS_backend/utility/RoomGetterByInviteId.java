package com.example.procon34_CYBER_WARS_backend.utility;

import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.repository.UtilityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomGetterByInviteId {

    private final UtilityRepository utilityRepository;

    // ルーム取得 by 招待ID
    public Rooms getRoomByInviteId(final short inviteId) {
        Rooms room = Rooms.builder()
                .invite_id(inviteId)
                .build();
        return utilityRepository.getRoomByInviteId(room);
    }

}
