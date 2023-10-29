package com.example.demo.service;

import com.example.demo.pojo.Admin;

/**
 * AdminService
 */

public interface AdminService extends BaseService<Admin> {
    /**
     * 更新管理员密码（未删除的记录）
     *
     * @param userName 用户名
     * @param passWord 新密码
     *
     * @return 受影响的行数
     */
    Integer revisePassWord(String userName, String passWord);

    /**
     * 根据用户名查询管理员记录（未删除的记录）
     *
     * @param userName 用户名
     *
     * @return 查询到的管理员实体对象，如果不存在则返回 null
     */
    Admin getByUserName(String userName);

}
