package com.example.manage_platform.manage.service;

import com.example.manage_platform.manage.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface HelloUserService {
    UserEntity getUser2();

    List<UserEntity> getUser3();

    Map<String, Object> readExcelFile(MultipartFile file);
}
