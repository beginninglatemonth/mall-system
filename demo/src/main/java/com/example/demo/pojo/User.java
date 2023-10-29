package com.example.demo.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * User
 * 用户
 */
@Data
public class User {


    private Integer id;

    // 微信用户标识
    private String openId;

    // 昵称
    private String nickName;

    // 头像
    private String avatarUrl;

    // 创建时间
    private Timestamp createTime;
    // 修改时间
    private Timestamp reviseTime;
    // 是否删除数据
    private Integer isDeleted;

}
