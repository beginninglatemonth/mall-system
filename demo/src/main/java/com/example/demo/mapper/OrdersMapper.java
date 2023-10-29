package com.example.demo.mapper;

import com.example.demo.pojo.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * OrdersMapper
 * 订单映射器
 */
@Mapper
public interface OrdersMapper {

    /**
     * 插入订单数据到数据库中，使用自动生成的主键。
     *
     * @param orders 订单对象
     *
     * @return 插入记录的数量
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO orders(order_no, user_id, total_price, address, consignee, tel_number) VALUES (#{orderNo}, #{userId}, #{totalPrice}, #{address}, #{consignee}, #{telNumber})")
    Integer insert(Orders orders);

    /**
     * 修改订单状态，未删除的订单。
     *
     * @param id     订单ID
     * @param status 订单状态
     *
     * @return 更新的记录数
     */
    @Update("UPDATE orders SET status = #{status} WHERE is_deleted = 0 AND id = #{id}")
    Integer updateByStatusNoDeleted(Integer id, String status);

    /**
     * 删除订单，未删除的订单。
     *
     * @param id 订单ID
     *
     * @return 更新的记录数
     */
    @Update("UPDATE orders SET is_deleted = 1 WHERE is_deleted = 0 AND id = #{id}")
    Integer updateByIsDeleted(Integer id);

    /**
     * 分页查询所有未删除的订单，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 订单列表
     */
    @Select("SELECT * FROM orders  ORDER BY is_deleted,revise_time DESC LIMIT #{current}, #{size}")
    List<Orders> selectPageList(Integer current, Integer size);

    /**
     * 根据订单状态分页查询未删除的订单，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param status  订单状态
     *
     * @return 订单列表
     */
    @Select("SELECT * FROM orders WHERE status = #{status}  ORDER BY is_deleted,revise_time DESC LIMIT #{current}, #{size}")
    List<Orders> selectPageListByStatus(Integer current, Integer size, Integer status);

    /**
     * 根据订单号模糊搜索未删除的订单，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param orderNo 订单号
     *
     * @return 订单列表
     */
    @Select("SELECT * FROM orders WHERE order_no LIKE '%${orderNo}%'  ORDER BY revise_time DESC LIMIT #{current}, #{size}")
    List<Orders> selectPageListByOrderNo(Integer current, Integer size, String orderNo);

    /**
     * 查询未删除订单的总数量
     *
     * @return 订单数量
     */
    @Select("SELECT COUNT(*) FROM orders ")
    Integer count();

    /**
     * 查询未删除订单的总数量
     *
     * @return 订单数量
     */
    @Select("SELECT COUNT(*) FROM orders WHERE status = #{status} AND is_deleted = 0")
    Integer countByStatusNoDeleted(Integer status);

    /**
     * 查询未删除订单的总数量
     *
     * @return 订单数量
     */
    @Select("SELECT COUNT(*) FROM orders WHERE order_no LIKE '%${orderNo}%'")
    Integer countByOrderNo(String orderNo);

}
