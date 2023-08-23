package com.example.procon34_CYBER_WARS_backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordRequest;

@Mapper
public interface UsersMapper {

    // ユーザー登録
    @Insert("INSERT INTO users(name, password) VALUES(#{name}, #{password})")
    void registerUser(final RegisterUserRequest registerUserRequest);

    // ユーザー名更新
    @Update("UPDATE users SET name = #{name} WHERE user_id = #{user_id}")
    void updateName(final UpdateNameRequest updateNameRequest);

    // ユーザーパスワード更新
    @Update("UPDATE users SET password = #{password} WHERE user_id = #{user_id}")
    void updatePassword(final UpdatePasswordRequest updatePasswordRequest);

}
