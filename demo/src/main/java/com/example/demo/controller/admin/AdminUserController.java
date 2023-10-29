package com.example.demo.controller.admin;

import com.example.demo.service.UserService;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.ResultUtil;
import com.example.demo.utils.Validate.StringValidateUtil;
import org.springframework.web.bind.annotation.*;

/**
 * AdminUserController
 * 管理员用户控制器
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {


    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据名称模糊查询用户列表
     *
     * @param current 当前页码
     * @param size    每页大小
     * @param name    模糊查询的名称
     *
     * @return 包含用户信息的分页列表
     */
    @GetMapping("/{current}/{size}")
    public ResultUtil getBySearchPageList(@PathVariable Integer current, @PathVariable Integer size, @RequestParam String name) {
        PageUtil users;
        if (StringValidateUtil.isBlank(name)) {
            // 没有输入名称，获取所有用户列表
            users = userService.getPageList(current, size);
        } else {
            // 根据名称模糊查询用户列表
            users = userService.SearchPageByName(current, size, name);
        }
        return new ResultUtil(users);
    }
}
