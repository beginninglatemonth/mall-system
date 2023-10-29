package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 通用 Mapper 接口，用于定义通用的数据库操作方法。
 *
 * @param <T> 实体类型的泛型
 */
@Mapper
public interface BaseMapper<T> {

    /**
     * 插入实体记录。
     *
     * @param entity 要插入的实体对象
     *
     * @return 插入的记录数
     */
    Integer insert(T entity);

    /**
     * 根据 ID 删除实体记录。
     *
     * @param id 要删除的记录的 ID
     *
     * @return 删除的记录数
     */
    Integer deleteById(Integer id);

    /**
     * 更新实体记录。
     *
     * @param entity 要更新的实体对象
     *
     * @return 更新的记录数
     */
    Integer update(T entity);

    /**
     * 根据 ID 查询实体记录。
     *
     * @param id 要查询的记录的 ID
     *
     * @return 查询到的实体对象，如果不存在则返回 null
     */
    T selectById(Integer id);

    /**
     * 查询所有实体记录。
     *
     * @return 包含所有实体记录的列表
     */
    List<T> selectAll();

    /**
     * 查询实体记录的总数。
     *
     * @return 实体记录的总数
     */

    Integer count();

    /**
     * 分页查询实体记录。
     *
     * @param current 当前页码偏移量位置
     * @param size    每页大小
     *
     * @return 包含分页结果的列表
     */
    List<T> findPageList(Integer current, Integer size);
}
