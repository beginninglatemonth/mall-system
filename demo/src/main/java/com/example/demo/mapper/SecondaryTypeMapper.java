package com.example.demo.mapper;

import com.example.demo.pojo.MainType;
import com.example.demo.pojo.SecondaryType;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * SecondaryTypeMapper
 * 二级类型映射器
 */
@Mapper
public interface SecondaryTypeMapper {

    /**
     * 插入一条 SecondaryType 记录到数据库中。
     *
     * @param secondaryType 要插入的 SecondaryType 对象
     *
     * @return 插入操作影响的行数
     */
    @Insert("INSERT INTO secondary_type(name,remark,main_type_id) VALUES (#{name}, #{remark}, #{mainTypeId})")
    Integer insert(SecondaryType secondaryType);

    /**
     * 根据主键 ID 更新 SecondaryType 记录。
     *
     * @param id            要更新的记录的 ID
     * @param secondaryType 包含要更新的字段的 SecondaryType 对象
     *
     * @return 更新操作影响的行数
     */
    @Update("update secondary_type set name=#{table.name},remark=#{table.remark},main_type_id=#{table.mainTypeId} where is_deleted = 0 and id=#{id}")
    Integer update(@Param("id") Integer id, @Param("table") SecondaryType secondaryType);

    /**
     * 根据主键 ID 删除 SecondaryType 记录。
     *
     * @param id 要删除的记录的 ID
     *
     * @return 删除操作影响的行数
     */
    @Update("update secondary_type set is_deleted=1 where is_deleted = 0 and id=#{id}")
    Integer updateDeleteByID(Integer id);

    /**
     * 根据名称查询 MainType 记录。
     *
     * @param name 要查询的名称
     *
     * @return 查询到的 MainType 对象，如果不存在则返回 null
     */
    @Select("select * from secondary_type WHERE is_deleted=0 and name=#{name}")
    MainType selectOneByName(String name);

    /**
     * 查询指定 ID 的未删除 SecondaryType 记录。
     *
     * @param id SecondaryType 记录的 ID
     *
     * @return 查询到的 SecondaryType 对象，如果不存在则返回 null
     */
    @Select("select id,name,main_type_id from secondary_type where is_deleted=0 and id=#{id}")
    SecondaryType getNoDeleted(Integer id);

    /**
     * 根据主类型 ID 查询未删除的 SecondaryType 记录列表。
     *
     * @param mainTypeId 主类型 ID
     *
     * @return 未删除的 SecondaryType 记录列表
     */
    @Select("select id,name from secondary_type where is_deleted=0 and main_type_id=#{mainTypeId}")
    List<SecondaryType> selectListByMainTypeIdNoDeleted(Integer mainTypeId);


    @Select("select id,name,main_type_id from secondary_type where is_deleted=0")
    List<Map<String, Object>> selectListNoDeleted();

    /**
     * 查询所有未删除的 SecondaryType 记录列表。
     *
     * @return 所有未删除的 SecondaryType 记录列表
     */
    @Select("SELECT secondary_type.id,secondary_type.name,secondary_type.main_type_id,main_type.name as type,secondary_type.remark,secondary_type.is_deleted FROM secondary_type INNER JOIN main_type ON secondary_type.main_type_id = main_type.id ORDER BY secondary_type.is_deleted,secondary_type.id LIMIT #{current}, #{size}")
    List<Map<String, Object>> selectPageList(Integer current, Integer size);

    /**
     * 根据名称模糊搜索未删除的 SecondaryType 记录列表，分页显示。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param name    要搜索的名称
     *
     * @return 模糊搜索的 SecondaryType 记录列表
     */
    @Select("SELECT secondary_type.id,secondary_type.name,secondary_type.main_type_id,main_type.name as type,secondary_type.remark,secondary_type.is_deleted FROM secondary_type INNER JOIN main_type ON secondary_type.main_type_id = main_type.id WHERE secondary_type.name LIKE '%${name}%' ORDER BY secondary_type.is_deleted,secondary_type.id LIMIT #{current}, #{size}")
    List<Map<String, Object>> selectPageListByName(Integer current, Integer size, String name);

    /**
     * 查询未删除 SecondaryType 记录的总数量。
     *
     * @return 未删除 SecondaryType 记录的总数量
     */
    @Select("SELECT COUNT(*) FROM secondary_type ")
    Integer count();

    /**
     * 根据名称模糊搜索 SecondaryType 记录的总数量。
     *
     * @param name 要搜索的名称
     *
     * @return 模糊搜索 SecondaryType 记录的总数量
     */
    @Select("SELECT COUNT(*) FROM secondary_type WHERE name LIKE '%${name}%'")
    Integer countByName(String name);
}
