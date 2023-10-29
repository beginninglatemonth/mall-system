package com.example.demo.service;

import com.example.demo.pojo.GoodsCarouselImages;

import java.util.Map;
import java.util.stream.Stream;

/**
 * UserService
 */

public interface GoodsCarouselImagesService {

    /**
     * 插入商品轮播图
     *
     * @param goodsCarouselImages 商品轮播图对象
     *
     * @return 插入记录的数量
     */
    Integer update(GoodsCarouselImages goodsCarouselImages);

    /**
     * 根据ID删除商品轮播图（逻辑删除）
     *
     * @param id 商品轮播图ID
     *
     * @return 更新记录的数量
     */
    Integer updateDeleteById(Integer id);

    /**
     * 根据商品ID获取商品轮播图列表
     *
     * @param goodsId 商品ID
     *
     * @return 商品轮播图列表
     */
    Stream<Map<String, Object>> getByGoodsIdList(Integer goodsId);
}
