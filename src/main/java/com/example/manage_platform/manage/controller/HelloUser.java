package com.example.manage_platform.manage.controller;

import com.example.manage_platform.manage.annotation.LoginInfo;
import com.example.manage_platform.manage.entity.UserEntity;
import com.example.manage_platform.manage.service.HelloUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class HelloUser {

    @RequestMapping("/login")
    @ResponseBody
    public UserEntity getUser(){
        UserEntity userEntity = UserEntity.builder().id(1).username("董涛").password("21111").validFlag(1)
                .createTime(new Date()).updateTime(new Date()).build();
        System.out.println(userEntity);
        return userEntity;
    }

    @Autowired
    private HelloUserService helloUserService;

    @RequestMapping("/login2")
    @ResponseBody
    public UserEntity getUser2(){
        UserEntity userEntity = helloUserService.getUser2();
        return userEntity;
    }

    @LoginInfo(value = "1")
    @RequestMapping(value = "/login3",method = RequestMethod.POST)
    @ResponseBody
    public List<UserEntity> getUser3(){
        List<UserEntity> userEntity = helloUserService.getUser3();
        return userEntity;
    }


}
