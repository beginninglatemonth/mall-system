package com.example.demo.service;

import com.example.demo.pojo.OrdersDetail;

import java.util.List;

/**
 * OrdersDetailService
 */

public interface OrdersDetailService {
    /**
     * 插入订单详情数据到数据库中。
     *
     * @param ordersDetail 订单详情对象
     *
     * @return 插入操作影响的行数
     */
    Integer save(OrdersDetail ordersDetail);

    /**
     * 根据订单ID查询订单详情列表
     *
     * @param orderId 订单ID
     *
     * @return 订单详情列表
     */
    List<OrdersDetail> selectById(Integer orderId);
}
