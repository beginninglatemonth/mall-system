package com.example.demo.pojo;


import lombok.Data;

import java.sql.Timestamp;

/**
 * MainType
 * 主要分类
 */
@Data
public class MainType {

    private Integer id;

    // 类型名称
    private String name;

    // 备注
    private String remark;

    // 图片
    private String image;

    // 创建时间
    private Timestamp createTime;
    // 修改时间
    private Timestamp reviseTime;
    // 是否删除数据
    private Integer isDeleted;


}
