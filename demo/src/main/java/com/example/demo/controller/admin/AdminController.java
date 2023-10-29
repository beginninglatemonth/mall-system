package com.example.demo.controller.admin;

import com.example.demo.controller.Validator;
import com.example.demo.pojo.Admin;
import com.example.demo.service.AdminService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.PasswordEncryptionUtil;
import com.example.demo.utils.ResultCodeUtil;
import com.example.demo.utils.ResultUtil;
import com.example.demo.utils.Validate.StringValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * AdminController
 */
@RestController
@RequestMapping("/admin")
public class AdminController {


    private final AdminService adminService;
    private final Validator validator;

    @Autowired
    public AdminController(AdminService adminService, Validator validator) {
        this.adminService = adminService;
        this.validator = validator;
    }

    /**
     * 生成包含用户名和类型的JWT令牌的辅助方法
     *
     * @param userName
     *
     * @return
     */
    private static Map<String, Object> getTokenResultMap(String userName) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("userName", userName);
        payload.put("type", "admin");
        String token = JwtUtil.generateToken(payload);
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        return result;
    }

    // 辅助方法：验证管理员输入是否合法
    private static ResultUtil validateAdminInput(Admin admin) {
        if (StringValidateUtil.isBlank(admin.getUserName())) return new ResultUtil(ResultCodeUtil.USERNAME_NO_NULL);
        if (StringValidateUtil.isBlank(admin.getPassWord())) return new ResultUtil(ResultCodeUtil.PASSWORD_NO_NULL);
        return null;
    }

    /**
     * 登录。
     *
     * @param admin 要创建的实体对象
     *
     * @return 创建的实体对象
     */
    @PostMapping("/login")
    public ResultUtil login(@RequestBody Admin admin) {
// 验证管理员输入
        ResultUtil adminInput = validateAdminInput(admin);
        if (adminInput != null) return adminInput;

        // 查询数据库中的管理员信息
        Admin read = adminService.getByUserName(admin.getUserName());
        if (read == null) return new ResultUtil(ResultCodeUtil.USER_NO_EXIST);
        // 验证密码
        if (!PasswordEncryptionUtil.matchesPassword(admin.getPassWord(), read.getPassWord())) {
            return new ResultUtil(ResultCodeUtil.PASSWORD_ERROR);
        }
        // 返回包含 Token 的结果
        return new ResultUtil(getTokenResultMap(read.getUserName()));
    }

    /**
     * 修改密码
     *
     * @param admin
     *
     * @return 创建的实体对象
     */
    @PostMapping("/revisePassWord")
    public ResultUtil revisePassword(@RequestBody Admin admin) {
        // 获取当前用户的用户名
        String userName = validator.getUserName();
        // 验证管理员输入
        ResultUtil adminInput = validateAdminInput(admin);
        if (adminInput != null) return adminInput;
        // 验证当前用户是否具有权限修改密码
        if (!userName.equals(admin.getUserName())) return new ResultUtil(ResultCodeUtil.PERMISSION_ERROR);
        // 查询数据库中的管理员信息
        Admin read = adminService.getByUserName(admin.getUserName());
        if (read == null) return new ResultUtil(ResultCodeUtil.USER_NO_EXIST);
        // 对新密码进行加密
        String passWord = PasswordEncryptionUtil.encodePassword(admin.getPassWord());
        // 更新密码
        adminService.revisePassWord(userName, passWord);
        // 返回包含 Token 的结果
        return new ResultUtil(getTokenResultMap(read.getUserName()));
    }
}
