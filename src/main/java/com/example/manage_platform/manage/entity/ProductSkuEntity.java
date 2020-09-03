package com.example.manage_platform.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSkuEntity implements Serializable {
    private String id;
    private String divisionName;
    private String storeName;
    private String paymediaType;
    private String unitName;
    private String productShowName;

    // Commodity的信息保存
    private String commodityID; // ID
    private String abbreviation; // 简称
    private String link; // 链接
    private String fg; // FG/赠品
    private String series; // 系列
    private String category; // 类目
    // 信息异常保存
    private String msg;
    private Integer userKey;
    private String userAccount;
    private String userNameCn;
    private String uploadDate;
    private String uploadDateTime;
    private String uuid;
    private Integer status;
    private String operation; // 操作状态



}
