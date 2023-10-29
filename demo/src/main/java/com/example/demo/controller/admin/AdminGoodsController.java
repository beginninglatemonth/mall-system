package com.example.demo.controller.admin;

import com.example.demo.pojo.Goods;
import com.example.demo.pojo.MainType;
import com.example.demo.service.GoodsService;
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
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * AdminGoodsController
 */
@RestController
@RequestMapping("admin/goods")
public class AdminGoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 创建商品信息
     *
     * @param goods
     *
     * @return
     */
    @PostMapping()
    public ResultUtil save(@RequestBody Goods goods) {

        System.out.println("goods = " + goods);
        // 验证请求参数是否合法
        if (StringValidateUtil.isBlank(goods.getName()) || (goods.getPrice().compareTo(BigDecimal.ZERO) < 0) || (goods.getStock() < 0) || (StringValidateUtil.isBlank(goods.getDescription())) || (goods.getType() < 0)) {
            return new ResultUtil(ResultCodeUtil.NOT_DATA);
        }
        // 检查商品名称是否已存在
        Goods one = goodsService.getOne(goods.getName());
        if (one != null) {
            return new ResultUtil(ResultCodeUtil.NAME_EXIST);
        }
        // 保存商品信息
        goodsService.save(goods);
        return new ResultUtil(ResultCodeUtil.SUCCESS);

    }

    /**
     * 上传商品图片
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
        String filePath = "src/main/resources/static/images/goods/";
        Goods one = goodsService.getOne(id);
        if (one != null) {
            try {
                // 保存文件
                Path path = Paths.get(filePath + saveFileName);
                file.transferTo(path);
                one.setImage("image/goods/" + saveFileName);
                goodsService.updateImages(one);
            } catch (IOException e) {
                throw new BusinessException(e.getMessage(), ResultCodeUtil.BUSINESS_ERROR.getCode());
            }
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 更新商品轮播图
     *
     * @param id
     * @param file
     * @param carouselOrdering
     *
     * @return
     */
    @PostMapping("/carouselImage/{id}")
    public ResultUtil updateCarouselImages(@PathVariable Integer id,
                                           @RequestParam(value = "file", required = false) MultipartFile file,
                                           @RequestParam(value = "carouselOrdering", required = false) Integer carouselOrdering
    ) {

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
        String filePath = "src/main/resources/static/images/carousel/";
        Goods one = goodsService.getOne(id);
        if (one != null) {
            try {
                // 保存文件
                Path path = Paths.get(filePath + saveFileName);
                file.transferTo(path);
                one.setCarouselImages("image/carousel/" + saveFileName);
                one.setCarouselOrdering(carouselOrdering);
                goodsService.updateCarouselImages(one);
            } catch (IOException e) {
                throw new BusinessException(e.getMessage(), ResultCodeUtil.BUSINESS_ERROR.getCode());
            }
        }
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 更新商品是否置顶推荐
     *
     * @param goods
     *
     * @return
     */
    @PutMapping("/isTopRecommendations")
    public ResultUtil updateIsTopRecommendations(@RequestBody Goods goods) {
        if (goods.getIsTopRecommendations()) {
            goods.setHotDateTime(new Timestamp(System.currentTimeMillis()));
        } else {
            goods.setHotDateTime(null);
        }
        goodsService.updateIsTopRecommendations(goods);
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 更新商品是否轮播图展示
     *
     * @param goods
     *
     * @return
     */
    @PutMapping("/isCarouselImages")
    public ResultUtil updateIsCarouselImages(@RequestBody Goods goods) {
        goodsService.updateIsCarouselImages(goods);
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 删除商品
     *
     * @param id
     *
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultUtil updateDeleteByID(@PathVariable Integer id) {
        goodsService.updateDeleteById(id);
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }


    @PutMapping()
    public ResultUtil update(@RequestBody Goods goods) {

        Goods one = goodsService.getOne(goods.getId());
        if (one == null) {
            return new ResultUtil(ResultCodeUtil.ID_ERROR);
        }

        if (StringValidateUtil.isBlank(goods.getName()) || (goods.getPrice().compareTo(BigDecimal.ZERO) < 0) || (goods.getStock() < 0) || (StringValidateUtil.isBlank(goods.getIntroductory())) || (StringValidateUtil.isBlank(goods.getSpecifications())) || (StringValidateUtil.isBlank(goods.getDescription())) || (goods.getType() < 0)) {
            return new ResultUtil(ResultCodeUtil.NOT_DATA);
        }

        goodsService.update(goods);

        return new ResultUtil(ResultCodeUtil.SUCCESS);

    }

    @GetMapping("/{current}/{size}")
    public ResultUtil getPageList(@PathVariable Integer current, @PathVariable Integer size, @RequestParam String name) {

        PageUtil<MainType> page;
        if (StringValidateUtil.isBlank(name)) {
            page = goodsService.getPageList(current, size);
        } else {
            page = goodsService.getPageListByName(current, size, name);
        }

        return new ResultUtil(page);
    }


}
