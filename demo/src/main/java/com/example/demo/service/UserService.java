package com.example.demo.service;

import com.example.demo.pojo.User;
import com.example.demo.utils.PageUtil;

/**
 * UserService
 */

public interface UserService extends BaseService<User> {
    Integer save(User user);

    Integer update(User user);

    PageUtil getPageList(Integer current, Integer size);

    PageUtil SearchPageByName(Integer current, Integer size, String name);

    User getOne(String openId);
}
