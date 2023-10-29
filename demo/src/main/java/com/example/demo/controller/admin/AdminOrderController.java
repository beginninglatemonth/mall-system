package com.example.demo.controller.admin;

import com.example.demo.pojo.Orders;
import com.example.demo.service.OrdersService;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.ResultCodeUtil;
import com.example.demo.utils.ResultUtil;
import com.example.demo.utils.Validate.StringValidateUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * AdminOrderController
 * 管理员订单控制器
 */
@RestController
@RequestMapping("admin/order")
public class AdminOrderController {


    private final OrdersService ordersService;

    public AdminOrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /**
     * 更新订单状态
     *
     * @param id      订单ID
     * @param request 包含要更新的订单状态的请求体
     *
     * @return 操作结果
     */
    @PutMapping("/{id}")
    public ResultUtil updateStatus(@PathVariable Integer id, @RequestBody Map<String, Object> request) {

        String status = request.get("status").toString();
        // 标记订单为已删除
        if ("删除".equals(status)) ordersService.updateByIsDeleted(id);
            // 更新订单状态
        else ordersService.updateByStatus(id, status);
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 分页获取订单列表
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param orderNo 订单编号，可选参数，用于搜索指定订单
     *
     * @return 分页订单列表
     */
    @GetMapping("/{current}/{size}")
    public ResultUtil getPageList(@PathVariable Integer current, @PathVariable Integer size, @RequestParam String orderNo) {

        PageUtil<Orders> page;
        if (StringValidateUtil.isBlank(orderNo)) {
            // 如果订单编号为空，则获取所有订单
            page = ordersService.getPageList(current, size);
        } else {
            // 如果订单编号不为空，则根据订单编号搜索订单
            page = ordersService.getPageListByOrderNo(current, size, orderNo);
        }
        return new ResultUtil(page);
    }
}
