package com.example.manage_platform.manage.controller;

//import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.manage_platform.manage.annotation.LoginInfo;
import com.example.manage_platform.manage.entity.UserEntity;
import com.example.manage_platform.manage.service.HelloUserService;
import com.example.manage_platform.utils.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Log4j2
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
        //多线程处理
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("demo-pool-%d").build();
//        ExecutorService executor = new ThreadPoolExecutor(6, 2*4+1,
//                200, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
//        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> shopDaily2Dao.queryProductEffectTableByDailyByCNum(selectProductEffectEntity),executor);
//        //等待三个数据源都返回后，再组装数据。这里会有一个线程阻塞
//        CompletableFuture.allOf(f1).join();
//        Integer c = f1.get();
        return userEntity;
    }

    @RequestMapping(value = "/readExcelFile", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> readExcelFile(@RequestParam(value="file",required = false) MultipartFile file){
        Map<String, Object> map = new HashMap<String, Object>();
        if (file == null){
            map.put("error_code",0);
            map.put("mag","未获取到文件信息");
            map.put("success",false);
            map.put("data",new HashMap<>());
            log.error("文件信息为空");
            return map;
        }
        map = helloUserService.readExcelFile(file);
        if (map == null){
            map = new HashMap<String, Object>();
        }
        if (map.size() == 0){
            map.put("error_code",0);
            map.put("mag","上传失败，请检查Excel中数据格式，或联系管理员");
            map.put("data",new HashMap<>());
        }
        return map;
    }

}
