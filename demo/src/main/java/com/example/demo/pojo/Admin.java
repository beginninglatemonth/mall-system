package com.example.demo.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Orders
 * 订单
 */
@Data
public class Admin {

    private Integer id;

    // 用户名
    private String userName;

    // 密码
    private String passWord;

    // 创建时间
    private Timestamp createTime;

    // 修改时间
    private Timestamp reviseTime;

    // 是否删除数据
    private Integer isDeleted;

}
