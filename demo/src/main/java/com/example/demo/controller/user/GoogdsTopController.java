package com.example.demo.controller.user;

import com.example.demo.service.GoodsService;
import com.example.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * GoodsTopController
 * 获取热门推荐商品
 */
@RestController
@RequestMapping("goodsTop")
public class GoogdsTopController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 获取热门推荐商品列表
     *
     * @return 包含热门推荐商品的结果对象
     */
    @GetMapping
    private ResultUtil getGoodsTop() {
        List goodsTop = goodsService.selectGoodsTop();

        return new ResultUtil(goodsTop);
    }

}
