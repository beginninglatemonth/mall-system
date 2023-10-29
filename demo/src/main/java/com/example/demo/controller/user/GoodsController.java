package com.example.demo.controller.user;

import com.example.demo.service.GoodsService;
import com.example.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * GoodsController
 * 商品
 */

@RestController
@RequestMapping("goods")

public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    /**
     * 根据名称模糊搜索商品信息
     *
     * @param name 商品名称关键字
     *
     * @return 包含商品信息的列表
     */
    @GetMapping("/search")
    public ResultUtil getGoodsDetailById(@RequestParam String name) {
        List<Map<String, Object>> likeByName = goodsService.getLikeByName(name);
        return new ResultUtil(likeByName);

    }
}
