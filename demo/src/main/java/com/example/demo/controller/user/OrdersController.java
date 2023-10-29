package com.example.demo.controller.user;

import com.example.demo.controller.Validator;
import com.example.demo.pojo.Orders;
import com.example.demo.pojo.OrdersDetail;
import com.example.demo.service.OrdersDetailService;
import com.example.demo.service.OrdersService;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrdersController
 * 处理用户订单相关操作
 */
@RestController
@RequestMapping("/user/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersDetailService ordersDetailService;
    @Autowired
    private Validator validator;


    /**
     * 创建订单
     *
     * @param orders 订单对象
     *
     * @return 创建的订单信息
     */
    @PostMapping()
    public ResultUtil save(@RequestBody Orders orders) {
        // 订单号
        orders.setOrderNo(String.valueOf(System.currentTimeMillis()));
        // 用户id
        orders.setUserId(validator.getOpenId());
        // 创建订单
        ordersService.create(orders);
        // 创建订单详情
        List<OrdersDetail> orderDatail = orders.getOrderDatail();
        for (OrdersDetail ordersDatail : orderDatail) {
            ordersDatail.setOrderId(orders.getId());
            ordersDetailService.save(ordersDatail);
        }
        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("orderNo", orders.getOrderNo());
        return new ResultUtil(result);
    }

    /**
     * 查询订单
     *
     * @param status  订单状态
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 包含订单信息的分页结果
     */
    @GetMapping("/{status}/{current}/{size}")
    public ResultUtil getList(@PathVariable Integer status, @PathVariable Integer current, @PathVariable Integer size) {
        PageUtil page;
        if (status == 0) {
            page = ordersService.getPageList(current, size);
        } else {
            page = ordersService.getPageListByStatus(current, size, status);
        }
        return new ResultUtil(page);
    }
}
