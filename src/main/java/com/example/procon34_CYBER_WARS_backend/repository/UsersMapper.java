package com.example.procon34_CYBER_WARS_backend.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.procon34_CYBER_WARS_backend.dto.UsersCredentialsRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;

@Mapper
public interface UsersMapper {

    // ユーザー登録
    @Insert("INSERT INTO users(name, password) VALUES(#{name}, #{password})")
    void register(UsersCredentialsRequest usersCredentialsRequest);

    // ユーザー検索
    @Select("SELECT * FROM users WHERE name = #{name}")
    Users search(UsersCredentialsRequest usersCredentialsRequest);

    // ユーザー情報変更
    @Update("UPDATE users SET name = #{name}, password = #{password} WHERE user_id = #{userId}")
    Users update(UsersCredentialsRequest usersCredentialsRequest);

    // セッション生成
    @Update("UPDATE users SET session_id = #{sessionId} WHERE user_id = #{userId}")
    Users createSession(UsersCredentialsRequest usersCredentialsRequest);

    // セッション削除
    @Update("UPDATE users SET session_id = NULL WHERE user_id = #{userId}")
    Users deleteSession(UsersCredentialsRequest usersCredentialsRequest);

}
