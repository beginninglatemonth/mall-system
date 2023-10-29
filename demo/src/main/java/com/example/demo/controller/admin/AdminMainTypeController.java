package com.example.demo.controller.admin;

import com.example.demo.pojo.MainType;
import com.example.demo.service.MainTypeService;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.ResultCodeUtil;
import com.example.demo.utils.ResultUtil;
import com.example.demo.utils.Validate.ImageValidateUtil;
import com.example.demo.utils.Validate.StringValidateUtil;
import com.example.demo.utils.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * AdminMainTypeController
 */
@RestController
@RequestMapping("admin/mainType")
public class AdminMainTypeController {

    @Autowired
    private MainTypeService mainTypeService;

    /**
     * 创建一级类型分类
     *
     * @param mainType
     *
     * @return
     */
    @PostMapping()
    public ResultUtil save(@RequestBody MainType mainType) {
        // 检查是否已存在相同名称的一级类型分类
        MainType one = mainTypeService.getOne(mainType.getName());
        if (one != null) {
            return new ResultUtil(ResultCodeUtil.NAME_EXIST);
        }
        // 保存一级类型分类
        mainTypeService.save(mainType);
        return new ResultUtil(ResultCodeUtil.SUCCESS);

    }

    /**
     * 上传一级类型分类图片
     *
     * @param id
     * @param file
     *
     * @return
     */
    @PostMapping("/image/{id}")
    public ResultUtil uploadImage(@PathVariable Integer id, @RequestParam(value = "file", required = false) MultipartFile file) {
        // 检查文件是否为空
        if (file.isEmpty()) {
            return new ResultUtil<>(ResultCodeUtil.NOT_DATA);
        }
        // 检查图片类型是否合法
        if (!ImageValidateUtil.isTypeValid(file)) {
            return new ResultUtil(ResultCodeUtil.IMAGE_TYPE_ERROR);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 生成一个唯一的文件名，以避免文件名冲突
        String saveFileName = UUID.randomUUID() + "_" + fileName;
        // 指定文件保存的目录
        String filePath = "src/main/resources/static/images/mainType/";
        MainType mainType = mainTypeService.getOne(id);
        if (mainType != null) {
            try {
                // 保存文件
                Path path = Paths.get(filePath + saveFileName);
                file.transferTo(path);
                mainType.setImage("image/mainType/" + saveFileName);
                mainTypeService.update(id, mainType);
            } catch (IOException e) {
                throw new BusinessException(e.getMessage(), ResultCodeUtil.BUSINESS_ERROR.getCode());
            }
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 删除一级类型分类
     *
     * @param id
     *
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultUtil updateDeleteByID(@PathVariable Integer id) {
        mainTypeService.updateDeleteByID(id);
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 更新一级类型分类
     *
     * @param id
     * @param mainType
     *
     * @return
     */
    @PutMapping("/{id}")
    public ResultUtil update(@PathVariable Integer id, @RequestBody MainType mainType) {
        mainTypeService.update(id, mainType);
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 分页获取一级类型分类列表
     *
     * @param current
     * @param size
     * @param name
     *
     * @return
     */
    @GetMapping("/{current}/{size}")
    public ResultUtil getPageList(@PathVariable Integer current, @PathVariable Integer size, @RequestParam String name) {
        PageUtil<MainType> page;
        if (StringValidateUtil.isBlank(name)) {
            page = mainTypeService.getPageList(current, size);
        } else {
            page = mainTypeService.getPageListByName(current, size, name);
        }
        return new ResultUtil(page);
    }


}
