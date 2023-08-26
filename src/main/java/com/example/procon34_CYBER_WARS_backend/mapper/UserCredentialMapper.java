package com.example.procon34_CYBER_WARS_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.procon34_CYBER_WARS_backend.entity.Users;

@Mapper
public interface UserCredentialMapper {

    // ユーザー取得 by ユーザーID
    @Select("""
            SELECT
                *
            FROM
                users
            WHERE
                user_id = #{userId}
            """)
    @Results(id = "Users", value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password")
    })
    Users getUserByUserId(final int userId);

}
