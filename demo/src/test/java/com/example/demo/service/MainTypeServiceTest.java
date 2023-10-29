package com.example.demo.service;

/**
 * MainTypeServiceTest
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MainTypeServiceTest {
    @Autowired
    private MainTypeService mainTypeService;

    @Test
    public void textGetList() {
        List<Map<String, Object>> list = mainTypeService.getListTenNoDeleted();

        list.forEach(System.out::println);
    }
}
