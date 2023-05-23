package com.example.procon34_CYBER_WARS_backend.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.procon34_CYBER_WARS_backend.dto.UsersCredentialsRequest;

@Mapper
public interface UsersMapper {

    @Insert("INSERT INTO users(name, password) VALUES(#{name}, #{password})")
    void register(UsersCredentialsRequest usersRegisterRequest);

}
