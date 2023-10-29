package com.example.demo.utils.Validate;

/**
 * 数字校验工具类
 */
public class NumberValidateUtil {

    /**
     * 判断字符串是否为正整数
     *
     * @param str 待校验字符串
     *
     * @return true：是正整数；false：不是正整数
     */
    public static boolean isPositiveInteger(String str) {
        // 使用正则表达式匹配正整数的格式
        return str.matches("^[1-9]\\d*$");
    }
}
