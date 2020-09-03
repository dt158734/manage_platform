package com.example.manage_platform.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
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
    private String createTimeString;
    private Date updateTime;
    private String updateTimeString;

    public Boolean getValidFlagStatus(){
        if (validFlag == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String getCreateTimeString(){
        String stringDate = null;
        if (createTime != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            stringDate = simpleDateFormat.format(createTime);
        }
        return stringDate;
    }

    public String getUpdateTimeString(){
        String stringDate = null;
        if (createTime != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            stringDate = simpleDateFormat.format(updateTime);
        }
        return stringDate;
    }
}
