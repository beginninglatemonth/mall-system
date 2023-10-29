package com.example.demo.service.impl;

import com.example.demo.mapper.OrdersDetailMapper;
import com.example.demo.pojo.OrdersDetail;
import com.example.demo.service.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * OrdersDetailServiceImpl
 */
@Service
public class OrdersDetailServiceImpl implements OrdersDetailService {

    @Autowired
    private OrdersDetailMapper ordersDatailMapper;

    /**
     * 插入订单详情数据到数据库中。
     *
     * @param ordersDetail 订单详情对象
     *
     * @return 插入操作影响的行数
     */
    public Integer save(OrdersDetail ordersDetail) {
        return ordersDatailMapper.insert(ordersDetail);
    }

    /**
     * 根据订单ID查询订单详情列表
     *
     * @param orderId 订单ID
     *
     * @return 订单详情列表
     */
    @Override
    public List<OrdersDetail> selectById(Integer orderId) {
        return ordersDatailMapper.selectById(orderId);
    }
}
