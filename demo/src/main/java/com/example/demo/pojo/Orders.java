package com.example.demo.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * Orders
 * 订单
 */
@Data

public class Orders {

    private Integer id;

    // 订单号
    private String orderNo;

    // 用户ID
    private String userId;

    // 价格
    private String totalPrice;

    // 地址
    private String address;

    // 用户名
    private String consignee;

    // 联系电话
    private String TelNumber;

    // 创建时间
    private Timestamp createTime;

    // 支付时间
    private Timestamp payTime;

    // 状态
    private String status;

    // 修改时间
    private Timestamp reviseTime;

    // 是否删除数据
    private Integer isDeleted;


    private List<OrdersDetail> orderDatail;
}
