package com.example.demo.controller.admin;

import com.example.demo.pojo.OrdersDetail;
import com.example.demo.service.OrdersDetailService;
import com.example.demo.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * AdminOrderDetailController
 * 管理员订单详情控制器
 */
@RestController
@RequestMapping("/admin/orderDetail")
public class AdminOrderDetailController {


    private final OrdersDetailService ordersDetailService;

    public AdminOrderDetailController(OrdersDetailService ordersDetailService) {
        this.ordersDetailService = ordersDetailService;
    }

    /**
     * 根据订单ID获取订单详情列表
     *
     * @param orderId 订单ID
     *
     * @return 包含订单详情的结果对象
     */
    @GetMapping("/{orderId}")
    public ResultUtil getListById(@PathVariable Integer orderId) {

        List<OrdersDetail> ordersDetails = ordersDetailService.selectById(orderId);

        return new ResultUtil(ordersDetails);
    }

}
