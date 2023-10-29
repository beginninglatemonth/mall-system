package com.example.demo.mapper;

import com.example.demo.pojo.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * GoodsMapper
 * 商品映射器
 */
@Mapper
public interface GoodsMapper {
    /**
     * 插入商品记录到数据库中。
     *
     * @param goods 要插入的商品对象
     *
     * @return 插入操作影响的行数
     */
    @Insert("INSERT INTO goods(name, price, stock, introductory, specifications, description, type) VALUES (#{name}, #{price}, #{stock}, #{introductory}, #{specifications}, #{description}, #{type})")
    Integer insert(Goods goods);

    /**
     * 更新商品热门推荐状态
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    @Update("update goods set is_top_recommendations=#{isTopRecommendations},hot_date_time=#{hotDateTime} where is_deleted = 0 and id=#{id}")
    Integer updateIsTopRecommendations(Goods goods);

    /**
     * 更新商品轮播图状态
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    @Update("update goods set is_carousel_images=#{isCarouselImages} where is_deleted = 0 and id=#{id}")
    Integer updateIsCarouselImages(Goods goods);

    /**
     * 更新商品图片
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    @Update("update goods set image=#{image} where is_deleted = 0 and id=#{id}")
    Integer updateImages(Goods goods);

    /**
     * 更新商品轮播图信息
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    @Update("update goods set carousel_images=#{carouselImages},carousel_ordering=#{carouselOrdering} where is_deleted = 0 and id=#{id}")
    Integer updateCarouselImages(Goods goods);

    /**
     * 更新商品信息
     *
     * @param goods 包含要更新的商品信息的对象
     *
     * @return 更新记录的数量
     */
    @Update("update goods set name=#{name},price=#{price},stock=#{stock},introductory=#{introductory},specifications=#{specifications},description=#{description},type=#{type} where is_deleted = 0 and id=#{id}")
    Integer update(Goods goods);

    /**
     * 根据ID删除商品（逻辑删除）
     *
     * @param id 商品ID
     *
     * @return 更新记录的数量
     */
    @Update("update goods set is_deleted=1 where is_deleted = 0 and id=#{id}")
    Integer updateDeleteById(Integer id);


    /**
     * 根据ID查询商品
     *
     * @param id 商品ID
     *
     * @return 商品对象，如果不存在则返回 null
     */
    @Select("select * from goods WHERE is_deleted=0 and id=#{id}")
    Goods selectOneById(Integer id);

    /**
     * 根据商品名称查询商品信息
     *
     * @param name 商品名称
     *
     * @return 查询到的商品对象，如果不存在则返回 null
     */
    @Select("select * from goods WHERE is_deleted=0 and name=#{name}")
    Goods selectOneByName(String name);

    /**
     * 查询所有未删除商品信息列表
     *
     * @return 未删除商品信息的列表
     */
    @Select("select * from goods where is_deleted=0")
    List<Goods> selectListNoDeleted();


    /**
     * 查询轮播图商品信息列表
     *
     * @return 轮播图商品信息的列表
     */
    @Select("select id,name,carousel_images from goods where is_carousel_images=1 and is_deleted=0 order by carousel_ordering")
    List<Map<String, Object>> selectCarouselNoDeleted();

    /**
     * 查询热门推荐商品信息列表
     *
     * @return 热门推荐商品信息的列表
     */
    @Select("select id,name,price,image from goods where is_top_recommendations=1 and is_deleted=0 order by hot_date_time like 8")
    List<Map<String, Object>> selectGoodsTopNoDeleted();

    /**
     * 根据商品ID查询商品详细页信息
     *
     * @param id 商品ID
     *
     * @return 查询到的商品详细页信息
     */
    @Select("select id,name,price,introductory,specifications,description from goods where id=#{id} and is_deleted=0")
    Map<String, Object> selectByIdNoDeleted(Integer id);

    /**
     * 根据名称模糊搜索商品信息列表
     *
     * @param name 商品名称关键字
     *
     * @return 符合条件的商品信息列表
     */
    @Select("select id,name from goods where name LIKE '%${name}%' and is_deleted=0")
    List<Map<String, Object>> selectLikeByNameNoDeleted(String name);

    /**
     * 分页查询商品列表
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 分页的商品信息列表
     */
    @Select("SELECT * FROM goods ORDER BY is_deleted,id LIMIT #{current}, #{size}")
    List<Goods> selectPageList(Integer current, Integer size);

    /**
     * 根据名称模糊搜索商品信息并分页查询
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param name    商品名称关键字
     *
     * @return 符合条件的分页商品信息列表
     */
    @Select("SELECT * FROM goods  WHERE name LIKE '%${name}%' ORDER BY is_deleted,id LIMIT #{current}, #{size}")
    List<Goods> selectPageListByName(Integer current, Integer size, String name);

    /**
     * 查询商品总数
     *
     * @return 商品总数
     */
    @Select("SELECT COUNT(*) FROM goods ")
    Integer count();

    /**
     * 根据名称模糊搜索商品数量
     *
     * @param name 商品名称关键字
     *
     * @return 符合搜索条件的商品数量
     */
    @Select("SELECT COUNT(*) FROM goods WHERE name LIKE '%${name}%'")
    Integer countByName(String name);
}
