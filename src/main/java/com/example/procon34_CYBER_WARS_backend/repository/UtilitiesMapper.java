package com.example.procon34_CYBER_WARS_backend.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.procon34_CYBER_WARS_backend.dto.utilities.SearchUserByNameRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;

@Mapper
public interface UtilitiesMapper {

    // ユーザー検索 by ユーザー名
    @Select("SELECT * FROM users WHERE name = #{name}")
    Users searchUserByName(SearchUserByNameRequest searchUserByNameRequest);

}
