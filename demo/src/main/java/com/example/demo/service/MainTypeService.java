package com.example.demo.service;

import com.example.demo.pojo.MainType;
import com.example.demo.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * mainTypeService
 */
public interface MainTypeService {
    /**
     * 插入一条 MainType 记录到数据库中。
     *
     * @param mainType 要插入的 MainType 对象
     *
     * @return 插入操作影响的行数
     */
    Integer save(MainType mainType);

    /**
     * 根据主键 ID 更新 MainType 记录。
     *
     * @param id       要更新的记录的 ID
     * @param mainType 包含要更新的字段的 MainType 对象
     *
     * @return 更新操作影响的行数
     */
    Integer update(Integer id, MainType mainType);

    /**
     * 根据主键 ID 删除 MainType 记录。
     *
     * @param id 要删除的记录的 ID
     *
     * @return 删除操作影响的行数
     */
    Integer updateDeleteByID(Integer id);

    /**
     * 根据 ID 查询 MainType 记录。
     *
     * @param id 要查询的记录的 ID
     *
     * @return 查询到的 MainType 对象，如果不存在则返回 null
     */
    MainType getOne(Integer id);

    /**
     * 根据 name 查询 MainType 记录。
     *
     * @param name 要查询的记录的 name
     *
     * @return 查询到的 MainType 对象，如果不存在则返回 null
     */
    MainType getOne(String name);

    /**
     * 查询所有未删除的 MainType 记录列表。
     *
     * @return MainType 记录列表
     */
    List<Map<String, Object>> getNameListNoDeleted();

    /**
     * 查询前 10 条未删除的 MainType 记录列表。
     *
     * @return 前 10 条未删除的 MainType 记录列表
     */
    List<Map<String, Object>> getListTenNoDeleted();

    /**
     * 分页查询 MainType 记录列表。
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 分页查询的 MainType 记录列表
     */
    PageUtil getPageList(Integer current, Integer size);

    /**
     * 根据订单号分页查询实体对象列表。
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 包含分页结果的 PageUtil 对象
     */
    PageUtil getPageListByName(Integer current, Integer size, String name);
}
