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
    void registerUser(UsersRequest usersRequest);

    // ユーザー名検索（ユーザー登録・ユーザー名変更・ユーザーログイン）
    @Select("SELECT * FROM users WHERE name = #{name}")
    Users searchUserByName(UsersRequest usersRequest);

    // ユーザー名変更
    @Update("UPDATE users SET name = #{name} WHERE user_id = #{userId}")
    void updateUserName(UsersUpdateRequest usersUpdateRequest);

    // ユーザーパスワード変更
    @Update("UPDATE users SET password = #{password} WHERE user_id = #{userId}")
    void updateUserPassword(UsersUpdateRequest usersUpdateRequest);

}
