package com.example.demo.controller.user;

import com.example.demo.pojo.SecondaryType;
import com.example.demo.service.SecondaryTypeService;
import com.example.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * SecondaryTypeController
 */
@RestController
@RequestMapping("secondaryType")
public class SecondaryTypeController {

    @Autowired
    private SecondaryTypeService secondaryTypeService;

    @GetMapping
    public ResultUtil getMainAndSecondaryTypeList() {
        List selectType = secondaryTypeService.selectType();
        return new ResultUtil(selectType);
    }

    @GetMapping("/{id}")
    public ResultUtil getSecondaryType(@PathVariable Integer id) {
        Map<String, Object> selectType = secondaryTypeService.getNoDeleted(id);
        return new ResultUtil(selectType);
    }

    @GetMapping("/mainType/{mainTypeId}")
    public ResultUtil getSecondaryTypeList(@PathVariable Integer mainTypeId) {
        List<SecondaryType> secondaryTypeList = secondaryTypeService.getListNoDeleted(mainTypeId);
        return new ResultUtil(secondaryTypeList);
    }
}
