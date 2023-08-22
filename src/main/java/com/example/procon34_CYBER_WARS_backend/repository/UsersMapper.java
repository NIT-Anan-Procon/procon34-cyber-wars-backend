package com.example.procon34_CYBER_WARS_backend.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateUserNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateUserPasswordRequest;

@Mapper
public interface UsersMapper {

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
