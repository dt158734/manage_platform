package com.example.manage_platform.manage.service.impl;

import com.example.manage_platform.manage.dao.HelloUserDao;
import com.example.manage_platform.manage.entity.UserEntity;
import com.example.manage_platform.manage.service.HelloUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HelloUserServiceImpl implements HelloUserService {

    @Override
    public UserEntity getUser2() {
        UserEntity userEntity = UserEntity.builder().id(2).username("董涛").password("21111").validFlag(1)
                .createTime(new Date()).updateTime(new Date()).build();
        System.out.println(userEntity);
        return userEntity;
    }

    @Autowired
    private HelloUserDao helloUserDao;

    @Override
    public List<UserEntity> getUser3() {
        List<UserEntity> userEntities = helloUserDao.getUser3();
        return userEntities;
    }
}
