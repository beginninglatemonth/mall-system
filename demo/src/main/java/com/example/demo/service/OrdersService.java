package com.example.demo.service;

import com.example.demo.pojo.Orders;
import com.example.demo.utils.PageUtil;

/**
 * OrdersService
 */

public interface OrdersService extends BaseService<Orders> {
    /**
     * 修改订单状态，未删除的订单。
     *
     * @param id     订单ID
     * @param status 订单状态
     *
     * @return 更新的记录数
     */
    Integer updateByStatus(Integer id, String status);

    /**
     * 删除订单，未删除的订单。
     *
     * @param id 订单ID
     *
     * @return 更新的记录数
     */
    Integer updateByIsDeleted(Integer id);

    /**
     * 分页查询所有未删除的订单，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 订单列表
     */
    PageUtil getPageList(Integer current, Integer size);

    /**
     * 根据订单状态分页查询未删除的订单，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param status  订单状态
     *
     * @return 订单列表
     */
    PageUtil getPageListByStatus(Integer current, Integer size, Integer status);

    PageUtil getPageListByOrderNo(Integer current, Integer size, String orderNo);

}
