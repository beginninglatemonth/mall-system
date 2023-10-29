package com.example.demo.utils.Validate;

/**
 * 字符串校验工具类
 */
public class StringValidateUtil {

    /**
     * 判断字符串是否为空（包括null和空格）
     *
     * @param str 待校验字符串
     *
     * @return true：为空；false：非空
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().isEmpty());

    }

    /**
     * 判断字符串是否为非空
     *
     * @param str 待校验字符串
     *
     * @return true：非空；false：为空
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
