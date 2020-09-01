package com.example.manage_platform.manage.dao;

import com.example.manage_platform.manage.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelloUserDao {

    List<UserEntity> getUser3();

}
