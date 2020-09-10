package com.example.manage_platform.manage.controller;

//import com.alibaba.fastjson.JSON;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.FIFOCache;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.manage_platform.manage.annotation.LoginInfo;
import com.example.manage_platform.manage.entity.UserEntity;
import com.example.manage_platform.manage.service.HelloUserService;
import com.example.manage_platform.utils.RedisUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

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

    @RequestMapping(value = "/threadRun", method=RequestMethod.POST)
    @ResponseBody
    public void threadRun() throws ExecutionException, InterruptedException {
        //多线程处理
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService executor = new ThreadPoolExecutor(6, 2*4+1,
                200, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture<List<UserEntity>> f1 = CompletableFuture.supplyAsync(() ->  helloUserService.getUser3(),executor);
        CompletableFuture<List<UserEntity>> f2 = CompletableFuture.supplyAsync(() ->  helloUserService.getUser3(),executor);
        CompletableFuture<List<UserEntity>> f3 = CompletableFuture.supplyAsync(() ->  helloUserService.getUser3(),executor);
        CompletableFuture<List<UserEntity>> f4 = CompletableFuture.supplyAsync(() ->  helloUserService.getUser3(),executor);
        CompletableFuture<List<UserEntity>> f5 = CompletableFuture.supplyAsync(() ->  helloUserService.getUser3(),executor);
        CompletableFuture.allOf(f1,f2,f3,f4,f5).join();
//        while (!f1.isDone() && !f2.isDone() &&  !f3.isDone() &&
//                !f4.isDone() && !f5.isDone()) {
//            System.out.println(1);
//        }
        List<UserEntity> userEntities1 = null;
        List<UserEntity> userEntities2 = null;
        List<UserEntity> userEntities3 = null;
        List<UserEntity> userEntities4 = null;
        List<UserEntity> userEntities5 = null;
        try {
            userEntities1 = f1.get();
            userEntities2 = f2.get();
            userEntities3 = f3.get();
            userEntities4 = f4.get();
            userEntities5 = f5.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<UserEntity> userEntities6 = null;
        if (userEntities6 == null) throw new AssertionError();

        System.out.println(userEntities1.toString().length());
        System.out.println(Objects.requireNonNull(userEntities2).toString().length());
        System.out.println(userEntities3.toString().length());
        System.out.println(userEntities4.toString().length());
        System.out.println(userEntities5.toString().length());

    }

    private FIFOCache<String , XSSFWorkbook> objects;

    /**
     * 不适用!如果使用的模板是通过是通过读取某个地址下的文件!后期这个地址的文件内容被修改!无法实时生效!
     * 需要在增加触发事件来做到实施更新!
     * @throws IOException
     */
    @RequestMapping(value = "/cacheFile", method=RequestMethod.POST)
    @ResponseBody
    public void cacheFile() throws IOException {
        long l = System.currentTimeMillis();
        if (objects == null ) {
            objects = CacheUtil.newFIFOCache(10, 0);
        }
        if (objects.get("xssfSheets") != null || objects.get("xssfSheets1") != null ||
                objects.get("xssfSheets2") != null || objects.get("xssfSheets3") != null ||
                objects.get("xssfSheets4") != null || objects.get("xssfSheets5") != null) {
            XSSFWorkbook xssfSheets = objects.get("xssfSheets");
            XSSFWorkbook xssfSheets1 = objects.get("xssfSheets1");
            XSSFWorkbook xssfSheets2 = objects.get("xssfSheets2");
            XSSFWorkbook xssfSheets3 = objects.get("xssfSheets3");
            XSSFWorkbook xssfSheets4 = objects.get("xssfSheets4");
            XSSFWorkbook xssfSheets5 = objects.get("xssfSheets5");
        } else {
            FileInputStream fileInputStream = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0001r01.xlsx");
            FileInputStream fileInputStream1 = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0002r01.xlsx");
            FileInputStream fileInputStream2 = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0002r02.xlsx");
            FileInputStream fileInputStream3 = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0002r03.xlsx");
            FileInputStream fileInputStream4 = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0003r01.xlsx");
            FileInputStream fileInputStream5 = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0003r02.xlsx");
            XSSFWorkbook xssfSheets = new XSSFWorkbook(fileInputStream);
            XSSFWorkbook xssfSheets1 = new XSSFWorkbook(fileInputStream1);
            XSSFWorkbook xssfSheets2 = new XSSFWorkbook(fileInputStream2);
            XSSFWorkbook xssfSheets3 = new XSSFWorkbook(fileInputStream3);
            XSSFWorkbook xssfSheets4 = new XSSFWorkbook(fileInputStream4);
            XSSFWorkbook xssfSheets5 = new XSSFWorkbook(fileInputStream5);
            objects.put("xssfSheets",xssfSheets);
            objects.put("xssfSheets1",xssfSheets1);
            objects.put("xssfSheets2",xssfSheets2);
            objects.put("xssfSheets3",xssfSheets3);
            objects.put("xssfSheets4",xssfSheets4);
            objects.put("xssfSheets5",xssfSheets5);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("缓存: " + (l1-l));
        FileInputStream fileInputStream = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0001r01.xlsx");
        FileInputStream fileInputStream1 = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0002r01.xlsx");
        FileInputStream fileInputStream2 = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0002r02.xlsx");
        FileInputStream fileInputStream3 = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0002r03.xlsx");
        FileInputStream fileInputStream4 = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0003r01.xlsx");
        FileInputStream fileInputStream5 = new FileInputStream("D:\\LAMP\\lease\\template\\excel\\acc0003r02.xlsx");
        XSSFWorkbook xssfSheets = new XSSFWorkbook(fileInputStream);
        XSSFWorkbook xssfSheets1 = new XSSFWorkbook(fileInputStream1);
        XSSFWorkbook xssfSheets2 = new XSSFWorkbook(fileInputStream2);
        XSSFWorkbook xssfSheets3 = new XSSFWorkbook(fileInputStream3);
        XSSFWorkbook xssfSheets4 = new XSSFWorkbook(fileInputStream4);
        XSSFWorkbook xssfSheets5 = new XSSFWorkbook(fileInputStream5);
        long l2 = System.currentTimeMillis();
        System.out.println("非缓存 :" + (l2-l1));
    }

}
