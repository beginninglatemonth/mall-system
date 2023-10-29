package com.example.demo.controller.user;

import com.example.demo.service.MainTypeService;
import com.example.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * MainTypeController
 * 一级类型分类
 */
@RestController
@RequestMapping("mainType")
public class MainTypeController {

    @Autowired
    private MainTypeService mainTypeService;

    /**
     * 获取未删除的前十个一级类型分类
     *
     * @return 包含一级类型分类信息的结果对象
     */
    @GetMapping
    public ResultUtil getListNoDeleted() {
        List<Map<String, Object>> mainTypes = mainTypeService.getListTenNoDeleted();
        return new ResultUtil(mainTypes);
    }

    /**
     * 获取未删除的一级类型分类列表
     *
     * @return 包含一级类型分类信息的结果对象
     */
    @GetMapping("/list")
    public ResultUtil getList() {
        List<Map<String, Object>> mainTypes = mainTypeService.getNameListNoDeleted();
        return new ResultUtil(mainTypes);
    }
}
