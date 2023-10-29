package com.example.demo.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * OrdersDetail
 * 订单数据
 */
@Data
public class OrdersDetail {

    private Integer id;
    private Integer orderId;
    private Integer goodsId;

    // 名称
    private String name;

    // 价格
    private BigDecimal price;

    // 数量
    private Integer number;

    // 商品图片
    private String image;

    // 创建时间
    private Timestamp createTime;

    // 修改时间
    private Timestamp reviseTime;

    // 是否删除数据
    private Integer isDeleted;

}
