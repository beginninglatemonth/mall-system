package com.example.demo.utils;

/**
 * ResultCodeUtil
 * 返回提示码
 */
public enum ResultCodeUtil {

    // 成功提示码
    SUCCESS(200, "成功"),


    /**
     * 异常模块错误码1000
     */
    NETWORK_ERROR(1000, "网络错误"),
    SYSTEM_ERROR(1100, "系统错误"),
    SYSTEM_TIMEOUT_ERROR(1101, "系统超时错误"),

    BUSINESS_ERROR(1200, "业务错误"),

    /**
     * 通用错误码10000
     */
    PERMISSION_ERROR(10000, "没有操作权限"),
    NOT_DATA(10001, "缺少数据"),
    NAME_EXIST(10002, "名称已存在"),
    ID_ERROR(10002, "ID不存在"),

    /**
     * 业务模块错误码10000
     * 用户11000~11099
     */
    USER_NO_LOGIN(11000, "用户未登录"),
    USER_EXIST(11001, "用户已存在"),
    USER_NO_EXIST(11002, "用户不存在"),


    USERNAME_NO_NULL(11003, "用户名不能为空"),
    PASSWORD_NO_NULL(11004, "密码不能为空"),
    // NICKNAME_NO_NULL(11005, "昵称不能为空"),
    PASSWORD_ERROR(11005, "密码错误"),


    // 10100
    IMAGE_TYPE_ERROR(10101, "图片类型错误"),

    // 模块错误码 20000
    // 模块错误码 30000

    A(1, "1");// 示例枚举值

    private final Integer code;
    private final String message;


    ResultCodeUtil(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
