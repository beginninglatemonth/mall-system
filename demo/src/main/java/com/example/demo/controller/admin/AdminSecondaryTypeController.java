package com.example.demo.controller.admin;

import com.example.demo.pojo.MainType;
import com.example.demo.pojo.SecondaryType;
import com.example.demo.service.SecondaryTypeService;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.ResultCodeUtil;
import com.example.demo.utils.ResultUtil;
import com.example.demo.utils.Validate.StringValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * AdminSecondaryTypeController
 * 管理员二级类型分类控制器
 */
@RestController
@RequestMapping("admin/secondaryType")
public class AdminSecondaryTypeController {

    @Autowired
    private SecondaryTypeService secondaryTypeService;

    /**
     * 创建一个二级类型分类
     *
     * @param secondaryType 二级类型分类对象
     *
     * @return 包含操作结果的结果对象
     */
    @PostMapping()
    public ResultUtil save(@RequestBody SecondaryType secondaryType) {
        MainType one = secondaryTypeService.getOne(secondaryType.getName());
        if (one != null) {
            return new ResultUtil(ResultCodeUtil.NAME_EXIST);
        }
        secondaryTypeService.save(secondaryType);
        return new ResultUtil(ResultCodeUtil.SUCCESS);

    }

    /**
     * 根据ID删除二级类型分类
     *
     * @param id 二级类型分类的ID
     *
     * @return 包含操作结果的结果对象
     */
    @DeleteMapping("/{id}")
    public ResultUtil updateDeleteByID(@PathVariable Integer id) {
        secondaryTypeService.updateDeleteByID(id);
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 更新二级类型分类信息
     *
     * @param id            二级类型分类的ID
     * @param secondaryType 更新后的二级类型分类对象
     *
     * @return 包含操作结果的结果对象
     */
    @PutMapping("/{id}")
    public ResultUtil update(@PathVariable Integer id, @RequestBody SecondaryType secondaryType) {
        secondaryTypeService.update(id, secondaryType);
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 分页获取二级类型分类列表
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param name    类型名称关键字（可选）
     *
     * @return 包含分页信息和结果列表的结果对象
     */
    @GetMapping("/{current}/{size}")
    public ResultUtil getPageList(@PathVariable Integer current, @PathVariable Integer size, @RequestParam String name) {
        PageUtil<MainType> page;
        if (StringValidateUtil.isBlank(name)) {
            page = secondaryTypeService.getPageList(current, size);
        } else {
            page = secondaryTypeService.getPageListByName(current, size, name);
        }
        return new ResultUtil(page);
    }


}
