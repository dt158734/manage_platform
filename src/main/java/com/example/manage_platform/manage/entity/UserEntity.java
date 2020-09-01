package com.example.manage_platform.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    private Integer id;
    private String username;
    private String password;
    private int validFlag;
    private Boolean validFlagStatus;
    private Date createTime;
    private Date updateTime;

    public Boolean getValidFlagStatus(){
        if (validFlag == 1) {
            return true;
        } else {
            return false;
        }
    }
}
