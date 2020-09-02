package com.example.manage_platform.manage.controller;

//import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.manage_platform.manage.annotation.LoginInfo;
import com.example.manage_platform.manage.entity.UserEntity;
import com.example.manage_platform.manage.service.HelloUserService;
import com.example.manage_platform.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/login2",method = RequestMethod.POST)
    @ResponseBody
    public UserEntity getUser2(){
        if (redisUtil.get("user") != null) {
            Object user = redisUtil.get("user");
            JSONObject jsonObject = JSON.parseObject((String) user);
            UserEntity userEntity = jsonObject.toJavaObject(UserEntity.class);
            return userEntity;
        } else {
            UserEntity userEntity = helloUserService.getUser2();
            String string = JSON.toJSONString(userEntity);
            redisUtil.set("user",string,3*1000*60);
            return userEntity;
        }
//        if (redisUtil.get("user") != null) {
//            UserEntity userEntity = (UserEntity)JSON.parse(redisUtil.get("user").toString());
//            return userEntity;
//        } else {
//            UserEntity userEntity = helloUserService.getUser2();
//            String string = JSON.toJSONString(userEntity);
//            redisUtil.set("user",string,3*1000*60);
//            return userEntity;
//        }
    }

    @LoginInfo(value = "1")
    @RequestMapping(value = "/login3",method = RequestMethod.POST)
    @ResponseBody
    public List<UserEntity> getUser3(){
        List<UserEntity> userEntity = helloUserService.getUser3();
        return userEntity;
    }


}
