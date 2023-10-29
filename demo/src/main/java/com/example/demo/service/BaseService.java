package com.example.demo.service;

import com.example.demo.utils.PageUtil;

import java.util.List;

/**
 * 通用的基础服务接口，定义了常见的 CRUD 操作。
 *
 * @param <T> 实体类型的泛型
 */
public interface BaseService<T> {

    /**
     * 创建实体对象。
     *
     * @param entity 要创建的实体对象
     *
     * @return 创建的记录数
     */
    Integer create(T entity);

    /**
     * 根据 ID 删除实体对象。
     *
     * @param id 要删除的记录的 ID
     *
     * @return 删除的记录数
     */
    Integer delete(Integer id);

    /**
     * 更新实体对象。
     *
     * @param entity 要更新的实体对象
     *
     * @return 更新的记录数
     */
    Integer update(T entity);

    /**
     * 获取所有实体对象的列表。
     *
     * @return 包含所有实体对象的列表
     */
    List<T> readList();


    /**
     * 根据 ID 读取实体对象。
     *
     * @param id 要查询的记录的 ID
     *
     * @return 查询到的实体对象，如果不存在则返回 null
     */
    T readById(Integer id);

    /**
     * 分页查询实体对象列表。
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 包含分页结果的 PageUtil 对象
     */
    PageUtil<T> readPageList(Integer current, Integer size);


}
