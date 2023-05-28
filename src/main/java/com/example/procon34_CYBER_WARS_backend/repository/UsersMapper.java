package com.example.procon34_CYBER_WARS_backend.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.procon34_CYBER_WARS_backend.dto.UsersCredentialsRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;

@Mapper
public interface UsersMapper {

    @Insert("INSERT INTO users(name, password) VALUES(#{name}, #{password})")
    void register(UsersCredentialsRequest usersCredentialsRequest);

    @Select("SELECT * FROM users WHERE name = #{name}")
    Users search(UsersCredentialsRequest usersCredentialsRequest);

}
