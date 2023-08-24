package com.example.procon34_CYBER_WARS_backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.mapper.UtilityMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UtilityRepository {

    private final UtilityMapper utilityMapper;

    // ユーザー取得 by ユーザー名
    public Users getUserByName(final Users user) {
        return utilityMapper.getUserByName(user);
    }

    // 動作中部屋取得
    public List<Rooms> getActiveRooms() {
        return utilityMapper.getActiveRooms();
    }

}
