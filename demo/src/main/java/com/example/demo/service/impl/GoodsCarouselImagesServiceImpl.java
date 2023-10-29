package com.example.demo.service.impl;

import com.example.demo.mapper.GoodsCarouselImagesMapper;
import com.example.demo.pojo.GoodsCarouselImages;
import com.example.demo.service.GoodsCarouselImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * GoodsCarouselImagesServiceImpl
 */
@Service
public class GoodsCarouselImagesServiceImpl implements GoodsCarouselImagesService {

    @Autowired
    private GoodsCarouselImagesMapper goodsCarouselImagesMapper;

    /**
     * 插入商品轮播图
     *
     * @param goodsCarouselImages 商品轮播图对象
     *
     * @return 插入记录的数量
     */
    public Integer update(GoodsCarouselImages goodsCarouselImages) {
        return goodsCarouselImagesMapper.insert(goodsCarouselImages);
    }

    /**
     * 根据ID删除商品轮播图（逻辑删除）
     *
     * @param id 商品轮播图ID
     *
     * @return 更新记录的数量
     */
    public Integer updateDeleteById(Integer id) {
        return goodsCarouselImagesMapper.updateDeleteById(id);
    }

    /**
     * 根据商品ID获取商品轮播图列表
     *
     * @param goodsId 商品ID
     *
     * @return 商品轮播图列表
     */
    public Stream<Map<String, Object>> getByGoodsIdList(Integer goodsId) {
        return goodsCarouselImagesMapper.getByGoodsIdList(goodsId).stream().map(value -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", value.getId());
            map.put("image", value.getImage());
            map.put("sort", value.getSort());
            map.put("isDeleted", value.getIsDeleted());
            return map;
        });


    }
}
