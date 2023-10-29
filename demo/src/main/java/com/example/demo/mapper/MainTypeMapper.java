package com.example.demo.mapper;

import com.example.demo.pojo.MainType;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * MainTypeMapper
 * 访问与 MainType 相关的数据库操作。
 */
@Mapper
public interface MainTypeMapper {

    /**
     * 插入一条 MainType 记录到数据库中。
     *
     * @param mainType 要插入的 MainType 对象
     *
     * @return 插入操作影响的行数
     */
    @Insert("INSERT INTO main_type(name,remark,image) VALUES ( #{name}, #{remark}, #{image})")
    Integer insert(MainType mainType);

    /**
     * 根据主键 ID 更新 MainType 记录。
     *
     * @param id       要更新的记录的 ID
     * @param mainType 包含要更新的字段的 MainType 对象
     *
     * @return 更新操作影响的行数
     */
    @Update("update main_type set name=#{table.name},remark=#{table.remark},image=#{table.image} where is_deleted = 0 and id=#{id}")
    Integer update(@Param("id") Integer id, @Param("table") MainType mainType);

    /**
     * 根据主键 ID 删除 MainType 记录。
     *
     * @param id 要删除的记录的 ID
     *
     * @return 删除操作影响的行数
     */
    @Update("update main_type set is_deleted=1 where is_deleted = 0 and id=#{id}")
    Integer updateDeleteByID(Integer id);

    /**
     * 根据 ID 查询 MainType 记录。
     *
     * @param id 要查询的记录的 ID
     *
     * @return 查询到的 MainType 对象，如果不存在则返回 null
     */
    @Select("select * from main_type WHERE is_deleted=0 and id=#{id}")
    MainType selectOneById(Integer id);

    /**
     * 根据 name 查询 MainType 记录。
     *
     * @param name 要查询的记录的 name
     *
     * @return 查询到的 MainType 对象，如果不存在则返回 null
     */
    @Select("select * from main_type WHERE is_deleted=0 and name=#{name}")
    MainType selectOneByName(String name);

    /**
     * 查询所有未删除的 MainType 记录列表。
     *
     * @return MainType 记录列表
     */
    @Select("select id,name from main_type where is_deleted=0")
    List<Map<String, Object>> selectListNoDeleted();

    /**
     * 查询前 10 条未删除的 MainType 记录列表。
     *
     * @return 前 10 条未删除的 MainType 记录列表
     */
    @Select("select id,name,image from main_type where is_deleted=0 limit 10")
    List<Map<String, Object>> selectListTenNoDeleted();

    /**
     * 分页查询 MainType 记录列表。
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 分页查询的 MainType 记录列表
     */
    @Select("SELECT id,name,remark,image,is_deleted FROM main_type ORDER BY is_deleted,id LIMIT #{current}, #{size}")
    List<Map<String, Object>> selectPageList(Integer current, Integer size);

    /**
     * 根据订单名称分页查询未删除的 MainType 记录列表，按照 ID 排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param name    订单名称关键字
     *
     * @return 根据订单名称分页查询的 MainType 记录列表
     */
    @Select("SELECT id,name,remark,image,is_deleted FROM main_type WHERE name LIKE '%${name}%'  ORDER BY is_deleted,id  LIMIT #{current}, #{size}")
    List<Map<String, Object>> selectPageListByName(Integer current, Integer size, String name);

    /**
     * 查询 MainType 记录的总数量。
     *
     * @return MainType 记录的总数量
     */
    @Select("SELECT COUNT(*) FROM main_type ")
    Integer count();

    /**
     * 根据订单名称查询 MainType 记录数量。
     *
     * @param name 订单名称关键字
     *
     * @return 根据订单名称查询到的未删除的 MainType 记录数量
     */
    @Select("SELECT COUNT(*) FROM main_type WHERE name LIKE '%${name}%'")
    Integer countByName(String name);
}
