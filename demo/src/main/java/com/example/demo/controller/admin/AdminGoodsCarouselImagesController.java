package com.example.demo.controller.admin;

import com.example.demo.pojo.GoodsCarouselImages;
import com.example.demo.service.GoodsCarouselImagesService;
import com.example.demo.utils.ResultCodeUtil;
import com.example.demo.utils.ResultUtil;
import com.example.demo.utils.Validate.ImageValidateUtil;
import com.example.demo.utils.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * AdminGoodsCarouselImagesController
 */
@RestController
@RequestMapping("/admin/goodsDetail")
public class AdminGoodsCarouselImagesController {

    @Autowired
    private GoodsCarouselImagesService goodsCarouselImagesService;

    /**
     * 更新商品轮播图
     *
     * @param file
     * @param sort
     * @param goodsId
     *
     * @return
     */
    @PostMapping
    public ResultUtil update(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "sort", required = false) Integer sort,
            @RequestParam(value = "goodsId", required = false) Integer goodsId
    ) {
        // 验证参数是否合法
        if ((file == null || file.isEmpty()) || (sort == null || sort < 0) || (goodsId == null || goodsId < 0)) {
            return new ResultUtil(ResultCodeUtil.NOT_DATA);
        }
        // 验证图片类型是否合法
        if (!ImageValidateUtil.isTypeValid(file)) {
            return new ResultUtil(ResultCodeUtil.IMAGE_TYPE_ERROR);
        }
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 生成一个唯一的文件名，以避免文件名冲突
            String saveFileName = UUID.randomUUID() + "_" + fileName;
            // 指定文件保存的目录
            String filePath = "src/main/resources/static/images/detailCarousel/";
            // 构建文件保存的路径对象
            Path path = Paths.get(filePath + saveFileName);
            // 保存文件到指定路径
            file.transferTo(path);
            // 构建商品轮播图对象
            GoodsCarouselImages goodsCarouselImages = new GoodsCarouselImages();
            goodsCarouselImages.setImage("image/detailCarousel/" + saveFileName);
            goodsCarouselImages.setSort(sort);
            goodsCarouselImages.setGoodsId(goodsId);
            // 调用服务层进行商品轮播图的更新
            goodsCarouselImagesService.update(goodsCarouselImages);
        } catch (IOException e) {
            // 处理文件操作异常
            throw new BusinessException(e.getMessage(), ResultCodeUtil.BUSINESS_ERROR.getCode());
        }
        // 返回操作成功的结果
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 删除商品轮播图
     *
     * @param id
     *
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultUtil updateDeleteByID(@PathVariable Integer id) {
        goodsCarouselImagesService.updateDeleteById(id);
        return new ResultUtil(ResultCodeUtil.SUCCESS);
    }

    /**
     * 根据商品ID获取商品轮播图列表
     *
     * @param goodsId
     *
     * @return
     */
    @GetMapping("/goodsId/{goodsId}")
    public ResultUtil list(@PathVariable Integer goodsId) {
        // 调用服务层获取对应商品ID的商品轮播图列表
        Stream<Map<String, Object>> list = goodsCarouselImagesService.getByGoodsIdList(goodsId);
        // 返回结果
        return new ResultUtil(list);
    }
}
