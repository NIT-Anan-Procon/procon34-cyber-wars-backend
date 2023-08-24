package com.example.procon34_CYBER_WARS_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.entity.Users;

@Mapper
public interface UtilityMapper {

    // ユーザー取得 by ユーザー名
    @Select("SELECT * FROM users WHERE name = #{name}")
    Users getUserByName(final Users user);

    // 動作中部屋取得
    @Select("SELECT * FROM rooms WHERE status IN(0, 1)")
    List<Rooms> getActiveRooms();

}
