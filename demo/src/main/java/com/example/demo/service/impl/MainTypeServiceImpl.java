package com.example.demo.service.impl;

import com.example.demo.mapper.MainTypeMapper;
import com.example.demo.pojo.MainType;
import com.example.demo.service.MainTypeService;
import com.example.demo.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * MainTypeServiceImpl
 */
@Service
public class MainTypeServiceImpl extends BaseServiceImpl<MainType> implements MainTypeService {
    @Autowired
    private MainTypeMapper mainTypeMapper;

    /**
     * 插入一条 MainType 记录到数据库中。
     *
     * @param mainType 要插入的 MainType 对象
     *
     * @return 插入操作影响的行数
     */
    @Override
    public Integer save(MainType mainType) {
        return mainTypeMapper.insert(mainType);
    }

    /**
     * 根据主键 ID 更新 MainType 记录。
     *
     * @param id       要更新的记录的 ID
     * @param mainType 包含要更新的字段的 MainType 对象
     *
     * @return 更新操作影响的行数
     */
    @Override
    public Integer update(Integer id, MainType mainType) {
        return mainTypeMapper.update(id, mainType);
    }

    /**
     * 根据主键 ID 删除 MainType 记录。
     *
     * @param id 要删除的记录的 ID
     *
     * @return 删除操作影响的行数
     */
    public Integer updateDeleteByID(Integer id) {
        return mainTypeMapper.updateDeleteByID(id);
    }

    /**
     * 根据 ID 查询 MainType 记录。
     *
     * @param id 要查询的记录的 ID
     *
     * @return 查询到的 MainType 对象，如果不存在则返回 null
     */
    @Override
    public MainType getOne(Integer id) {
        return mainTypeMapper.selectOneById(id);
    }

    /**
     * 根据 name 查询 MainType 记录。
     *
     * @param name 要查询的记录的 name
     *
     * @return 查询到的 MainType 对象，如果不存在则返回 null
     */
    @Override
    public MainType getOne(String name) {
        return mainTypeMapper.selectOneByName(name);
    }

    /**
     * 查询所有未删除的 MainType 记录列表。
     *
     * @return MainType 记录列表
     */
    public List<Map<String, Object>> getNameListNoDeleted() {
        return mainTypeMapper.selectListNoDeleted();
    }

    /**
     * 查询前 10 条未删除的 MainType 记录列表。
     *
     * @return 前 10 条未删除的 MainType 记录列表
     */
    @Override
    public List<Map<String, Object>> getListTenNoDeleted() {
        List<Map<String, Object>> collect = mainTypeMapper.selectListTenNoDeleted().stream().map(value -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", value.get("id"));
            map.put("name", value.get("name"));
            map.put("image", value.get("image"));
            return map;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 分页查询 MainType 记录列表。
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 分页查询的 MainType 记录列表
     */
    public PageUtil getPageList(Integer current, Integer size) {
        PageUtil<Map<String, Object>> page = readPageMapList(
                current,
                size,
                mainTypeMapper.count(),
                (offset, pageSize) -> mainTypeMapper.selectPageList(offset, pageSize)
        );

        List<Map<String, Object>> collect = page.getData().stream().map(value -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", value.get("id"));
            map.put("name", value.get("name"));
            map.put("remark", value.get("remark"));
            map.put("image", value.get("image"));
            map.put("isDeleted", value.get("is_deleted"));
            return map;
        }).collect(Collectors.toList());

        page.setData(collect);

        return page;
    }

    /**
     * 根据订单名称分页查询未删除的 MainType 记录列表，按照 ID 排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param name    订单名称关键字
     *
     * @return 根据订单名称分页查询的 MainType 记录列表
     */
    public PageUtil getPageListByName(Integer current, Integer size, String name) {
        PageUtil<Map<String, Object>> page = readPageMapList(
                current,
                size,
                mainTypeMapper.countByName(name),
                (offset, pageSize) -> mainTypeMapper.selectPageListByName(offset, pageSize, name)
        );

        List<Map<String, Object>> collect = page.getData().stream().map(value -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", value.get("id"));
            map.put("name", value.get("name"));
            map.put("remark", value.get("remark"));
            map.put("image", value.get("image"));
            map.put("isDeleted", value.get("is_deleted"));
            return map;
        }).collect(Collectors.toList());

        page.setData(collect);
        return page;
    }


}
