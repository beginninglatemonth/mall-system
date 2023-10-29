package com.example.demo.service;

import com.example.demo.pojo.Goods;
import com.example.demo.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * GoodsService
 */
public interface GoodsService {

    /**
     * 插入商品记录到数据库中。
     *
     * @param goods 要插入的商品对象
     *
     * @return 插入操作影响的行数
     */
    Integer save(Goods goods);

    /**
     * 更新商品热门推荐状态
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    Integer updateIsTopRecommendations(Goods goods);

    /**
     * 更新商品轮播图状态
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    Integer updateIsCarouselImages(Goods goods);

    /**
     * 更新商品图片
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    Integer updateImages(Goods goods);

    /**
     * 更新商品轮播图信息
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    Integer updateCarouselImages(Goods goods);

    /**
     * 根据ID删除商品（逻辑删除）
     *
     * @param id 商品ID
     *
     * @return 更新记录的数量
     */
    Integer updateDeleteById(Integer id);

    /**
     * 更新商品信息
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    Integer update(Goods goods);

    /**
     * 查询轮播图商品信息列表
     *
     * @return 轮播图商品信息的列表
     */
    List<Map<String, Object>> getCarousel();

    /**
     * 查询热门推荐商品信息列表
     *
     * @return 热门推荐商品信息的列表
     */
    List selectGoodsTop();

    /**
     * 根据商品ID获取详细页信息与轮播图
     *
     * @param id 商品ID
     *
     * @return 商品详细信息与轮播图
     */
    Map<String, Object> getGoodsDetail(Integer id);

    /**
     * 根据名称模糊搜索商品信息列表
     *
     * @param name 商品名称关键字
     *
     * @return 符合条件的商品信息列表
     */
    List<Map<String, Object>> getLikeByName(String name);


    Goods getOne(Integer id);

    Goods getOne(String name);

    /**
     * 分页查询商品列表
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 分页的商品信息列表
     */
    PageUtil getPageList(Integer current, Integer size);

    /**
     * 根据名称模糊搜索商品信息并分页查询
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param name    商品名称关键字
     *
     * @return 符合条件的分页商品信息列表
     */
    PageUtil getPageListByName(Integer current, Integer size, String name);

}
