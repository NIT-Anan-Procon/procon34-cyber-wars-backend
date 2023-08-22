package com.example.procon34_CYBER_WARS_backend.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.procon34_CYBER_WARS_backend.dto.utilities.SearchUserByNameRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.entity.Users;

@Mapper
public interface UtilitiesMapper {

    // ユーザー検索 by ユーザー名
    @Select("SELECT * FROM users WHERE name = #{name}")
    Users searchUserByName(SearchUserByNameRequest searchUserByNameRequest);

    // 有効招待ID取得
    @Select("SELECT invite_id FROM rooms WHERE status IN(0, 1)")
    List<Rooms> getValidInviteIds();

}
