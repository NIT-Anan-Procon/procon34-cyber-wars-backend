package com.example.procon34_CYBER_WARS_backend.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.procon34_CYBER_WARS_backend.dto.UsersRequest;
import com.example.procon34_CYBER_WARS_backend.dto.UsersUpdateRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;

@Mapper
public interface UsersMapper {

    // ユーザー登録
    @Insert("INSERT INTO users(name, password) VALUES(#{name}, #{password})")
    void register(UsersRequest usersRequest);

    // ユーザー検索（ユーザー登録・ユーザー情報変更・ユーザーログイン）
    @Select("SELECT * FROM users WHERE name = #{name}")
    Users search(UsersRequest usersRequest);

    // ユーザー名変更（ユーザー情報変更）
    @Update("UPDATE users SET name = #{name} WHERE user_id = #{userId}")
    Users updateName(UsersUpdateRequest usersUpdateRequest);

    // ユーザーパスワード変更（ユーザー情報変更）
    @Update("UPDATE users SET password = #{password} WHERE user_id = #{userId}")
    Users updatePassword(UsersUpdateRequest usersUpdateRequest);

    // セッション生成（ユーザーログイン）
    @Update("UPDATE users SET session_id = #{sessionId} WHERE user_id = #{userId}")
    Users createSession(UsersRequest usersRequest);

    // セッション削除（ユーザーログアウト）
    @Update("UPDATE users SET session_id = NULL WHERE user_id = #{userId}")
    Users deleteSession(UsersRequest usersRequest);

}
