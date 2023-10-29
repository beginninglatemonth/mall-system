package com.example.demo.service;

/**
 * MainTypeServiceTest
 */

import com.example.demo.utils.PageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SecondaryTypeServiceTest {
    @Autowired
    private SecondaryTypeService secondaryTypeService;

    @Test
    public void textSelectType() {
        List list = secondaryTypeService.selectType();
        list.forEach(System.out::println);
    }

    @Test
    public void getPageListTest() {
        PageUtil pageList = secondaryTypeService.getPageList(0, 10);

        pageList.getData().forEach(System.out::println);
    }
}
