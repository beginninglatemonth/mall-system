package com.example.demo.controller.user;

import com.example.demo.service.GoodsService;
import com.example.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * GoodsCarouselImagesController
 * 商品详细页
 */
@RestController
@RequestMapping("/goodsDetail")
public class GoodsCarouselImagesController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 根据商品ID获取商品详细信息与轮播图
     *
     * @param id 商品ID
     *
     * @return 包含商品详细信息与轮播图的结果对象
     */
    @GetMapping("/{id}")
    public ResultUtil getGoodsDetailById(@PathVariable Integer id) {
        Map<String, Object> goodsDetail = goodsService.getGoodsDetail(id);
        return new ResultUtil(goodsDetail);
    }
}
