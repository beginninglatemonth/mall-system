package com.example.demo.utils;

import java.util.List;

/**
 * PageUtil
 * 分页工具类
 *
 * @param <T> 数据类型
 */
public class PageUtil<T> {

    private final Integer current;  // 当前页码
    private final Integer size;  // 每页记录数
    private final Integer count;  // 总记录数
    private final Integer totalPages;  // 总页数
    private List<T> data;  // 数据列表

    /**
     * 构造函数
     *
     * @param current 当前页码
     * @param size    每页记录数
     * @param count   总记录数
     * @param data    数据列表
     */
    public PageUtil(Integer current, Integer size, Integer count, List<T> data) {
        this.current = current;
        this.size = size;
        this.count = count;
        this.totalPages = (int) Math.ceil((double) count / size);
        this.data = data;
    }

    /**
     * 获取当前页码
     *
     * @return 当前页码
     */
    public Integer getCurrent() {
        return current;
    }

    /**
     * 获取每页记录数
     *
     * @return 每页记录数
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 获取总记录数
     *
     * @return 总记录数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 获取数据列表
     *
     * @return 数据列表
     */
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * 判断是否有下一页
     *
     * @return 是否有下一页
     */
    public boolean hasNextPage() {
        return current < getTotalPages();
    }

    /**
     * 判断是否有上一页
     *
     * @return 是否有上一页
     */
    public boolean hasPreviousPage() {
        return current > 1;
    }
}
