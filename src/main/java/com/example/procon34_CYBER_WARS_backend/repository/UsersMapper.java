package com.example.procon34_CYBER_WARS_backend.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.procon34_CYBER_WARS_backend.dto.UsersRegisterRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;

@Mapper
public interface UsersMapper {

    @Select("INSERT INTO users(name, password) VALUES(#{name}, #{password})")
    Users register(UsersRegisterRequest usersRegisterRequest);

}
