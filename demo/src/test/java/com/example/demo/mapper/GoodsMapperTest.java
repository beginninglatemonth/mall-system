package com.example.demo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;


/**
 * GoodsMapperTest
 */

@SpringBootTest
public class GoodsMapperTest {
    @Autowired
    private GoodsMapper goodsMapper;


    @Test
    public void testSelectList() {
        List<Map<String, Object>> list = goodsMapper.selectCarouselNoDeleted();
        for (Object o : list) {
            System.out.println("o = " + o);
        }


    }
}
