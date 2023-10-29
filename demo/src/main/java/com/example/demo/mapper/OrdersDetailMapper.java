package com.example.demo.mapper;

import com.example.demo.pojo.OrdersDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * OrdersDetailMapper
 * 订单详情映射器
 */
@Mapper
public interface OrdersDetailMapper {
    /**
     * 插入订单详情数据到数据库中。
     *
     * @param ordersDetail 订单详情对象
     *
     * @return 插入操作影响的行数
     */
    @Insert("insert into orders_datail(order_id,goods_id,name,price,number,image) values (#{orderId},#{goodsId},#{name},#{price},#{number},#{image})")
    Integer insert(OrdersDetail ordersDetail);

    /**
     * 根据订单ID查询订单详情列表
     *
     * @param orderId 订单ID
     *
     * @return 订单详情列表
     */
    @Select("select * from orders_detail where order_id=#{orderId}")
    List<OrdersDetail> selectById(Integer orderId);
}
