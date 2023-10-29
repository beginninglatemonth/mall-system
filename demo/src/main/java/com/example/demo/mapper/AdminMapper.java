package com.example.demo.mapper;

import com.example.demo.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * AdminMapper
 * 管理员数据访问层接口
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 更新管理员密码（未删除的记录）
     *
     * @param userName 用户名
     * @param passWord 新密码
     *
     * @return 受影响的行数
     */
    @Update("update admin set pass_word=#{passWord} where is_deleted=0 and user_name=#{userName}")
    Integer revisePassWordNoDeleted(String userName, String passWord);

    /**
     * 根据用户名查询管理员记录（未删除的记录）
     *
     * @param userName 用户名
     *
     * @return 查询到的管理员实体对象，如果不存在则返回 null
     */
    @Select("select * from admin where is_deleted=0 and user_name=#{userName}")
    Admin selectByUserNameNoDeleted(String userName);
}
