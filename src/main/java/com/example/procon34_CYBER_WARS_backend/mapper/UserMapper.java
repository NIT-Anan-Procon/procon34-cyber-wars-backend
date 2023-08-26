package com.example.procon34_CYBER_WARS_backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.procon34_CYBER_WARS_backend.entity.Users;

@Mapper
public interface UserMapper {

    // ユーザー登録
    @Insert("""
            INSERT INTO
                users(name, password)
            VALUES
                (#{name}, #{password})
            """)
    void register(final String name, final String password);

    // ユーザー名更新
    @Update("""
            UPDATE
                users
            SET
                name = #{name}
            WHERE
                user_id = #{userId}
            """)
    void updateName(final int userId, final String name);

    // ユーザーパスワード更新
    @Update("""
            UPDATE
                users
            SET
                password = #{password}
            WHERE
                user_id = #{userId}
            """)
    void updatePassword(final int userId, final String password);

    // ユーザー取得 by ユーザー名
    @Select("""
            SELECT
                *
            FROM
                users
            WHERE
                name = #{name}
            """)
    @Results(id = "Users", value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password")
    })
    Users getUserByName(final String name);

}
