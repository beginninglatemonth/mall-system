package com.example.demo.service.impl;

import com.example.demo.mapper.AdminMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrdersServiceImpl
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    public AdminMapper adminMapper;

    /**
     * 更新管理员密码（未删除的记录）
     *
     * @param userName 用户名
     * @param passWord 新密码
     *
     * @return 受影响的行数
     */
    public Integer revisePassWord(String userName, String passWord) {
        return adminMapper.revisePassWordNoDeleted(userName, passWord);
    }

    /**
     * 根据用户名查询管理员记录（未删除的记录）
     *
     * @param userName 用户名
     *
     * @return 查询到的管理员实体对象，如果不存在则返回 null
     */
    @Override
    public Admin getByUserName(String userName) {
        return adminMapper.selectByUserNameNoDeleted(userName);
    }
}
