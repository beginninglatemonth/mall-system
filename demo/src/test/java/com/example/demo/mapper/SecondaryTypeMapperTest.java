package com.example.demo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * SecondaryTypeMapperTest
 */
@SpringBootTest
public class SecondaryTypeMapperTest {
    @Autowired
    private SecondaryTypeMapper secondaryTypeMapper;

    @Test
    public void selectPageListTest() {
        List<Map<String, Object>> list = secondaryTypeMapper.selectPageList(1, 10);
        list.forEach(System.out::println);
    }

}
