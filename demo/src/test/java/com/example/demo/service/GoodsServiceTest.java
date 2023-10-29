package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * GoodsServiceTest
 */
@SpringBootTest
public class GoodsServiceTest {


    @Autowired
    private GoodsService goodsService;

    @Test
    public void textGetCarousel() {
        List carousel = goodsService.getCarousel();
        System.out.println(carousel.size());
        carousel.forEach(System.out::println);
    }

    @Test
    public void testGetGoodsDetail() {
        Map<String, Object> goodsDetail = goodsService.getGoodsDetail(1);

        System.out.println("goodsDetail = " + goodsDetail);

    }
}
