package com.example.demo.service.impl;

import com.example.demo.mapper.OrdersMapper;
import com.example.demo.pojo.Orders;
import com.example.demo.service.OrdersService;
import com.example.demo.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrdersServiceImpl
 */
@Service
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 修改订单状态，未删除的订单。
     *
     * @param id     订单ID
     * @param status 订单状态
     *
     * @return 更新的记录数
     */
    public Integer updateByStatus(Integer id, String status) {
        return ordersMapper.updateByStatusNoDeleted(id, status);
    }

    /**
     * 删除订单，未删除的订单。
     *
     * @param id 订单ID
     *
     * @return 更新的记录数
     */
    public Integer updateByIsDeleted(Integer id) {
        return ordersMapper.updateByIsDeleted(id);
    }

    /**
     * 分页查询所有未删除的订单，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 订单列表
     */
    public PageUtil getPageList(Integer current, Integer size) {
        return readPage(
                current,
                size,
                ordersMapper.count(),
                (offset, pageSize) -> ordersMapper.selectPageList(offset, pageSize)
        );

    }

    /**
     * 根据订单状态分页查询未删除的订单，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param status  订单状态
     *
     * @return 订单列表
     */
    public PageUtil getPageListByStatus(Integer current, Integer size, Integer status) {
        return readPage(
                current,
                size,
                ordersMapper.countByStatusNoDeleted(status),
                (offset, pageSize) -> ordersMapper.selectPageListByStatus(offset, pageSize, status)
                // ordersMapper::selectPageListByStatus
        );
    }

    /**
     * 根据订单号模糊搜索未删除的订单，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param orderNo 订单号
     *
     * @return 订单列表
     */
    public PageUtil getPageListByOrderNo(Integer current, Integer size, String orderNo) {
        return readPage(
                current,
                size,
                ordersMapper.countByOrderNo(orderNo),
                (offset, pageSize) -> ordersMapper.selectPageListByOrderNo(offset, pageSize, orderNo)
        );
    }
}
