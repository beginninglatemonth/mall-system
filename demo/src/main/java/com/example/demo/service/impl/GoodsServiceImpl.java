package com.example.demo.service.impl;

import com.example.demo.mapper.GoodsCarouselImagesMapper;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.pojo.Goods;
import com.example.demo.service.GoodsService;
import com.example.demo.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GoodsServiceImpl
 * 商品服务实现类
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService {

    private final GoodsMapper goodsMapper;
    private final GoodsCarouselImagesMapper goodsCarouselImagesMapper;

    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper, GoodsCarouselImagesMapper goodsCarouselImagesMapper) {
        this.goodsMapper = goodsMapper;
        this.goodsCarouselImagesMapper = goodsCarouselImagesMapper;
    }

    /**
     * 插入商品记录到数据库中。
     *
     * @param goods 要插入的商品对象
     *
     * @return 插入操作影响的行数
     */
    public Integer save(Goods goods) {
        return goodsMapper.insert(goods);
    }

    /**
     * 更新商品热门推荐状态
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    public Integer updateIsTopRecommendations(Goods goods) {
        return goodsMapper.updateIsTopRecommendations(goods);
    }

    /**
     * 更新商品轮播图状态
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    public Integer updateIsCarouselImages(Goods goods) {
        return goodsMapper.updateIsCarouselImages(goods);
    }

    /**
     * 更新商品图片
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    public Integer updateImages(Goods goods) {
        return goodsMapper.updateImages(goods);
    }

    /**
     * 更新商品轮播图信息
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    public Integer updateCarouselImages(Goods goods) {
        return goodsMapper.updateCarouselImages(goods);
    }

    /**
     * 根据ID删除商品（逻辑删除）
     *
     * @param id 商品ID
     *
     * @return 更新记录的数量
     */
    public Integer updateDeleteById(Integer id) {
        return goodsMapper.updateDeleteById(id);
    }

    /**
     * 更新商品信息
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    public Integer update(Goods goods) {
        return goodsMapper.update(goods);
    }


    public Goods getOne(Integer id) {
        return goodsMapper.selectOneById(id);
    }

    @Override
    public Goods getOne(String name) {
        return goodsMapper.selectOneByName(name);
    }

    /**
     * 查询轮播图商品信息列表
     *
     * @return 轮播图商品信息的列表
     */
    @Override
    public List<Map<String, Object>> getCarousel() {
        List<Map<String, Object>> collect = goodsMapper.selectCarouselNoDeleted().stream().map(goods -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", goods.get("id"));
            map.put("name", goods.get("name"));
            map.put("carouselImages", goods.get("carousel_images"));
            return map;
        }).collect(Collectors.toList());
        /*
        List<Map<String, Object>> list = new ArrayList<>();
        goodsMapper.selectCarouselNoDeleted().forEach(goods -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", goods.get("id"));
            list.add(map);
        });
        System.out.println("collect = " + collect);
        System.out.println("list = " + list);
        */
        return collect;
    }

    /**
     * 查询热门推荐商品信息列表
     *
     * @return 热门推荐商品信息的列表
     */
    @Override
    public List selectGoodsTop() {
        return goodsMapper.selectGoodsTopNoDeleted();

    }

    /**
     * 根据商品ID获取详细页信息与轮播图
     *
     * @param id 商品ID
     *
     * @return 商品详细信息与轮播图
     */
    @Override
    public Map<String, Object> getGoodsDetail(Integer id) {
        // 获取商品详细信息
        Map<String, Object> goods = goodsMapper.selectByIdNoDeleted(id);


        Map<String, Object> map = new HashMap<>();
        map.put("id", goods.get("id"));
        map.put("name", goods.get("name"));
        map.put("price", goods.get("price"));
        map.put("introductory", goods.get("introductory"));
        map.put("specifications", goods.get("specifications"));
        map.put("description", goods.get("description"));

        // 获取商品轮播图信息
        List<Map<String, Object>> goodsCarouselImages = goodsCarouselImagesMapper.selectListByGoodsIdNoDeleted(id);
        // 添加轮播图信息到商品详细信息中
        map.put("goodsCarouselImages", goodsCarouselImages);

        return map;
    }

    /**
     * 根据名称模糊搜索商品信息列表
     *
     * @param name 商品名称关键字
     *
     * @return 符合条件的商品信息列表
     */
    public List<Map<String, Object>> getLikeByName(String name) {
        return goodsMapper.selectLikeByNameNoDeleted(name);
    }

    /**
     * 分页查询商品列表
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 分页的商品信息列表
     */
    public PageUtil getPageList(Integer current, Integer size) {
        return readPage(
                current,
                size,
                goodsMapper.count(),
                (offset, pageSize) -> goodsMapper.selectPageList(offset, pageSize)
        );


    }

    /**
     * 根据名称模糊搜索商品信息并分页查询
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param name    商品名称关键字
     *
     * @return 符合条件的分页商品信息列表
     */
    public PageUtil getPageListByName(Integer current, Integer size, String name) {

        return readPage(
                current,
                size,
                goodsMapper.countByName(name),
                (offset, pageSize) -> goodsMapper.selectPageListByName(offset, pageSize, name));

    }

    private PageUtil wrapPageQuery(PageUtil<Map<String, Object>> page) {
        // id, name, price, stock, image, is_top_recommendations, hot_date_time,
        // is_carousel_images, carousel_images, carousel_ordering,
        // introductory, specifications, description,
        // type, create_time, revise_time, is_deleted
        List<Map<String, Object>> collect = page.getData().stream().map(value -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", value.get("id"));
            map.put("name", value.get("name"));
            map.put("price", value.get("price"));
            map.put("stock", value.get("stock"));
            map.put("image", value.get("image"));
            map.put("isTopRecommendations", value.get("is_top_recommendations"));
            map.put("hotDateTime", value.get("hot_date_time"));
            map.put("isCarouselImages", value.get("is_carousel_images"));
            map.put("carouselImages", value.get("carousel_images"));
            map.put("carouselOrdering", value.get("carousel_ordering"));
            map.put("introductory", value.get("introductory"));
            map.put("specifications", value.get("specifications"));
            map.put("description", value.get("description"));
            map.put("type", value.get("type"));
            map.put("reviseTime", value.get("revise_time"));
            map.put("isDeleted", value.get("is_deleted"));
            return map;
        }).collect(Collectors.toList());

        page.setData(collect);
        return page;
    }

}
