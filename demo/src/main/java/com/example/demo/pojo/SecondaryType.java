package com.example.demo.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * SecondaryType
 * 次要分类
 */
@Data
public class SecondaryType {

    private Integer id;
    // 类型名称
    private String name;

    // 备注
    private String remark;

    // MainType主键
    private Integer mainTypeId;

    // 创建时间
    private Timestamp createTime;
    // 修改时间
    private Timestamp reviseTime;
    // 是否删除数据
    private Integer isDeleted;

}
