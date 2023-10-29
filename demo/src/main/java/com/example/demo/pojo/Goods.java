package com.example.demo.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Goods
 * 商品
 */
@Data
public class Goods {

    private Integer id;

    // 名称
    private String name;

    // 价格
    private BigDecimal price;

    // 库存
    private Integer stock;

    // 商品图片
    private String image;

    // 是否热门推荐商品（是；否）
    private Boolean isTopRecommendations;

    // 商品热门推荐日期
    private Timestamp hotDateTime;

    // 是否轮播图片商品
    private Boolean isCarouselImages;

    // 商品轮播图片
    private String carouselImages;

    // 轮播排序
    private Integer carouselOrdering;

    // 商品介绍
    private String introductory;

    // 商品规格参数
    private String specifications;

    // 描述
    private String description;

    // 类型
    private Integer type;

    // 创建时间
    private Timestamp createTime;

    // 修改时间
    private Timestamp reviseTime;

    // 是否删除数据
    private Integer isDeleted;

}
