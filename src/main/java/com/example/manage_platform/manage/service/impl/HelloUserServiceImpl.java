package com.example.manage_platform.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.manage_platform.manage.dao.HelloUserDao;
import com.example.manage_platform.manage.entity.ProductSkuEntity;
import com.example.manage_platform.manage.entity.UserEntity;
import com.example.manage_platform.manage.service.HelloUserService;
import com.example.manage_platform.utils.ReadExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> readExcelFile(MultipartFile file) {
        ReadExcelUtils readExcel = new ReadExcelUtils();
        // 解析excel，获取上传的事件单
        List<?> dataList = readExcel.getExcelInfo(file);
        String string = dataList.toString();
        JSONObject jsonObject = JSON.parseObject(string);
        List<ProductSkuEntity> list = JSON.toJavaObject(JSON.parseObject(string), List.class);
        return null;
    }
}
