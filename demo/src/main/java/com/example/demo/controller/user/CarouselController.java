package com.example.demo.controller.user;

import com.example.demo.service.GoodsService;
import com.example.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CarouselController
 * 轮播图
 */
@RestController
@RequestMapping("carousel")
public class CarouselController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 获取轮播图
     *
     * @return 包含轮播图信息的结果对象
     */
    @GetMapping
    public ResultUtil getCarousel() {
        List carousel = goodsService.getCarousel();
        return new ResultUtil(carousel);
    }
}
