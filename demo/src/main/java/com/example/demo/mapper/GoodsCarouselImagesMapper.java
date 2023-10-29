package com.example.demo.mapper;

import com.example.demo.pojo.GoodsCarouselImages;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * GoodsCarouselImagesMapper
 * 商品轮播图映射器
 */
@Mapper
public interface GoodsCarouselImagesMapper {
    /**
     * 插入商品轮播图
     *
     * @param goodsCarouselImages 商品轮播图对象
     *
     * @return 插入记录的数量
     */
    @Insert("INSERT INTO goods_carousel_images(image,sort,goods_id) VALUES (#{image}, #{sort}, #{goodsId})")
    Integer insert(GoodsCarouselImages goodsCarouselImages);

    /**
     * 根据ID删除商品轮播图（逻辑删除）
     *
     * @param id 商品轮播图ID
     *
     * @return 更新记录的数量
     */
    @Update("update goods_carousel_images set is_deleted=1 where is_deleted = 0 and id=#{id}")
    Integer updateDeleteById(Integer id);

    /**
     * 根据商品ID获取商品轮播图列表
     *
     * @param goodsId 商品ID
     *
     * @return 商品轮播图列表
     */
    @Select("select id,image,sort,is_deleted from goods_carousel_images WHERE goods_id=#{goodsId} order by is_deleted,sort")
    List<GoodsCarouselImages> getByGoodsIdList(Integer goodsId);

    /**
     * 根据商品ID获取未删除的商品轮播图列表
     *
     * @param id 商品ID
     *
     * @return 未删除的商品轮播图列表
     */
    @Select("select id,image,sort from goods_carousel_images where goods_id=#{id} and is_deleted=0 order by sort")
    List<Map<String, Object>> selectListByGoodsIdNoDeleted(Integer id);

}
