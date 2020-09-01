package com.example.manage_platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.manage_platform.manage.dao")
public class ManagePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagePlatformApplication.class, args);
    }

}
