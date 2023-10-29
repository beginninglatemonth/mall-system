package com.example.demo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * MainTypeMapperTest
 */
@SpringBootTest
public class MainTypeMapperTest {

    @Autowired
    private MainTypeMapper mainTypeMapper;

    @Test
    public void testSelectList() {

        for (Map<String, Object> mainType : mainTypeMapper.selectListTenNoDeleted()) {
            System.out.println(mainType);
        }
    }

}
