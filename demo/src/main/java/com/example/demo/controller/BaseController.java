package com.example.demo.controller;

import com.example.demo.service.BaseService;
import com.example.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 通用的 RESTful API 控制器基类，用于处理 CRUD 操作。
 *
 * @param <T> 实体类型的泛型
 */

public class BaseController<T> {

    @Autowired
    private BaseService<T> baseService;

    /**
     * 创建新实体资源。
     *
     * @param entity 要创建的实体对象
     *
     * @return 创建的实体对象
     */

    public ResultUtil create(@RequestBody T entity) {
        return new ResultUtil(baseService.create(entity));
    }

    /**
     * 根据ID删除实体资源。
     *
     * @param id 要删除的实体的ID
     */

    public ResultUtil delete(@PathVariable Integer id) {
        return new ResultUtil(baseService.delete(id));
    }

    /**
     * 更新实体资源。
     *
     * @param entity 要更新的实体对象
     *
     * @return 更新后的实体对象
     */

    public ResultUtil update(@RequestBody T entity) {
        return new ResultUtil(baseService.update(entity));
    }

    /**
     * 获取所有实体资源列表。
     *
     * @return 包含所有实体对象的列表
     */

    public ResultUtil list() {
        return new ResultUtil(baseService.readList());
    }

    /**
     * 根据ID查找实体资源。
     *
     * @param id 要查找的实体的ID
     *
     * @return 查找到的实体对象，如果不存在则返回 null
     */

    public ResultUtil read(@PathVariable Integer id) {
        return new ResultUtil(baseService.readById(id));
    }

}
