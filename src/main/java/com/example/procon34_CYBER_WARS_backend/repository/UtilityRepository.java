package com.example.procon34_CYBER_WARS_backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.dto.utility.GetUserByNameRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.mapper.UtilityMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UtilityRepository {

    private final UtilityMapper utilityMapper;

    // ユーザー取得 by ユーザー名
    Users getUserByName(final GetUserByNameRequest getUserByNameRequest) {
        return utilityMapper.getUserByName(getUserByNameRequest);
    }

    // 有効招待ID取得
    List<Rooms> getValidInviteIds() {
        return utilityMapper.getValidInviteIds();
    }

}
