package com.example.demo.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * GoodsCarouselImages
 * 商品详细页轮播图片
 */
@Data
public class GoodsCarouselImages {

    private Integer id;
    // 图片
    private String image;

    // 排序序号
    private Integer sort;
    // 商品ID
    private Integer goodsId;

    // 创建时间
    private Timestamp createTime;
    // 修改时间
    private Timestamp reviseTime;
    // 是否删除数据
    private Integer isDeleted;


}
