package com.example.procon34_CYBER_WARS_backend.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.procon34_CYBER_WARS_backend.dto.Users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.SearchUserByNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserPasswordRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;

@Mapper
public interface UsersMapper {

    // ユーザー検索 by ユーザー名
    @Select("SELECT * FROM users WHERE name = #{name}")
    Users searchUserByName(SearchUserByNameRequest searchUserByNameRequest);

    // ユーザー登録
    @Insert("INSERT INTO users(name, password) VALUES(#{name}, #{password})")
    void registerUser(RegisterUserRequest registerUserRequest);

    // ユーザー名更新
    @Update("UPDATE users SET name = #{name} WHERE user_id = #{userId}")
    void updateUserName(UpdateUserNameRequest updateUserNameRequest);

    // ユーザーパスワード更新
    @Update("UPDATE users SET password = #{password} WHERE user_id = #{userId}")
    void updateUserPassword(UpdateUserPasswordRequest updateUserPasswordRequest);

}
