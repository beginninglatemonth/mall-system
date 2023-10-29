package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入一条用户记录到数据库中。
     *
     * @param user 要插入的用户对象
     *
     * @return 插入操作影响的行数
     */
    public Integer save(User user) {
        return userMapper.insert(user);
    }

    /**
     * 根据用户的 OpenID 更新用户记录（如果未被删除）。
     *
     * @param user 要更新的用户对象
     *
     * @return 更新操作影响的行数
     */
    public Integer update(User user) {
        return userMapper.updateNoDeleted(user);
    }

    /**
     * 分页查询用户列表，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     *
     * @return 包含用户信息的列表
     */
    @Override
    public PageUtil getPageList(Integer current, Integer size) {
        return readPage(
                current,
                size,
                userMapper.count(),
                (offset, pageSize) -> userMapper.selectPageList(offset, pageSize)
        );

    }

    /**
     * 根据名称分页模糊查询用户列表，按创建时间降序排序。
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param name    模糊查询的名称
     *
     * @return 包含用户信息的列表
     */
    public PageUtil SearchPageByName(Integer current, Integer size, String name) {
        return readPage(
                current,
                size,
                userMapper.countByName(name),
                (offset, pageSize) -> userMapper.selectSearchPageByName(offset, pageSize, name)
        );
    }

    /**
     * 根据用户的 OpenID 查询一条用户记录。
     *
     * @param openId 用户的 OpenID
     *
     * @return 查询到的用户对象，如果不存在则返回 null
     */
    public User getOne(String openId) {
        return userMapper.SelectOne(openId);
    }
}
