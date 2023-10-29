package com.example.demo.service.impl;

import com.example.demo.mapper.BaseMapper;
import com.example.demo.service.BaseService;
import com.example.demo.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;


/**
 * 通用 Service 实现类，提供通用的业务逻辑操作。
 *
 * @param <T> 实体类型的泛型
 */

public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;


    // 创建实体
    @Override
    public Integer create(T entity) {
        return baseMapper.insert(entity);
    }

    // 删除实体
    @Override
    public Integer delete(Integer id) {
        return baseMapper.deleteById(id);
    }

    // 更新实体
    @Override
    public Integer update(T entity) {
        return baseMapper.update(entity);
    }

    // 获取实体列表
    @Override
    public List<T> readList() {
        return baseMapper.selectAll();
    }

    // 根据ID读取实体
    @Override
    public T readById(Integer id) {
        return baseMapper.selectById(id);
    }

    /**
     * 分页查询通用方法-使用自定义mapper查询语句
     *
     * @param current        当前页码
     * @param size           每页大小
     * @param count          总记录数
     * @param selectFunction 用于执行分页查询的函数
     *
     * @return 包含分页结果的 PageUtil 对象
     */
    public PageUtil<T> readPage(Integer current, Integer size, Integer count, BiFunction<Integer, Integer, List<T>> selectFunction) {
        //  校验并修正当前页码和每页大小
        if (current == null || current < 1) {
            current = 1;
        }

        // 默认每页大小为 10
        if (size == null || size < 1) {
            size = 10;
        }

        //  计算偏移量
        int offset = (current - 1) * size;

        //  防止当前页超出总页数范围
        int maxPage = (int) Math.ceil((double) count / size);
        if (current > maxPage && maxPage > 0) {
            current = maxPage;
            offset = (current - 1) * size;
        }

        //  执行分页查询
        List<T> pageList = selectFunction.apply(offset, size);

        //  创建并返回 PageUtil 对象
        PageUtil<T> page = new PageUtil<>(current, size, count, pageList);

        return page;
    }

    /**
     * 分页查询通用方法-使用自定义mapper查询语句
     *
     * @param current        当前页码
     * @param size           每页大小
     * @param count          总记录数
     * @param selectFunction 用于执行分页查询的函数
     *
     * @return 包含分页结果的 PageUtil 对象
     */
    public PageUtil<Map<String, Object>> readPageMapList(Integer current, Integer size, Integer count, BiFunction<Integer, Integer, List<Map<String, Object>>> selectFunction) {
        //  校验并修正当前页码和每页大小
        if (current == null || current < 1) {
            current = 1;
        }

        // 默认每页大小为 10
        if (size == null || size < 1) {
            size = 10;
        }

        //  计算偏移量
        int offset = (current - 1) * size;

        //  防止当前页超出总页数范围
        int maxPage = (int) Math.ceil((double) count / size);
        if (current > maxPage && maxPage > 0) {
            current = maxPage;
            offset = (current - 1) * size;
        }

        //  执行分页查询
        List<Map<String, Object>> pageList = selectFunction.apply(offset, size);

        //  创建并返回 PageUtil 对象
        PageUtil<Map<String, Object>> page = new PageUtil<>(current, size, count, pageList);

        return page;
    }

    /**
     * 分页查询通用方法-使用BaseMapper查询语句
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 包含分页结果的 PageUtil 对象
     */
    @Override
    public PageUtil<T> readPageList(Integer current, Integer size) {
        return readPage(
                current,
                size,
                baseMapper.count(),
                (offset, pageSize) -> baseMapper.findPageList(offset, pageSize)
        );
    }

}
