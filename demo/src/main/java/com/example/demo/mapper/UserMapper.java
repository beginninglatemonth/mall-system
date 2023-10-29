package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * UserMapper
 * 用户映射器
 */
@Mapper
public interface UserMapper {
    /**
     * 插入一条用户记录到数据库中。
     *
     * @param user 要插入的用户对象
     *
     * @return 插入操作影响的行数
     */
    @Insert("insert INTO user (open_id,nick_name,avatar_url) values (#{openId},#{nickName},#{avatarUrl})")
    Integer insert(User user);

    /**
     * 根据用户的 OpenID 更新用户记录（如果未被删除）。
     *
     * @param user 要更新的用户对象
     *
     * @return 更新操作影响的行数
     */
    @Update("update user set nick_name=#{nickName},avatar_url=#{avatarUrl} where open_id=#{openId} and is_deleted=0 ")
    Integer updateNoDeleted(User user);

    /**
     * 分页查询用户列表，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 包含用户信息的列表
     */
    @Select("SELECT * FROM user ORDER BY is_deleted,create_time DESC LIMIT #{current}, #{size}")
    List<User> selectPageList(Integer current, Integer size);

    /**
     * 根据名称分页模糊查询用户列表，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param name    模糊查询的名称
     *
     * @return 包含用户信息的列表
     */
    @Select("SELECT * FROM user WHERE nick_name LIKE '%${name}%' ORDER BY is_deleted,create_time DESC LIMIT #{current}, #{size}")
    List<User> selectSearchPageByName(Integer current, Integer size, @Param("name") String name);

    /**
     * 根据用户的 OpenID 查询一条用户记录。
     *
     * @param openId 用户的 OpenID
     *
     * @return 查询到的用户对象，如果不存在则返回 null
     */
    @Select("select * from user where open_id=#{openId}")
    User SelectOne(String openId);

    /**
     * 查询未删除用户记录的总数量。
     *
     * @return 用户数量
     */
    @Select("SELECT COUNT(*) FROM user ")
    Integer count();

    /**
     * 根据名称模糊搜索用户记录的总数量。
     *
     * @param name 要搜索的名称
     *
     * @return 模糊搜索用户记录的总数量
     */
    @Select("SELECT COUNT(*) FROM user  WHERE nick_name LIKE '%${name}%'")
    Integer countByName(@Param("name") String name);
}
