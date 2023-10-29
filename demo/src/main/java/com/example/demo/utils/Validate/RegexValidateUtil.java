package com.example.demo.utils.Validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则校验工具类
 */
public class RegexValidateUtil {

    /**
     * 验证手机号码格式是否正确
     *
     * @param str 待校验手机号
     *
     * @return true：正确；false：错误
     */
    public static boolean isMobile(String str) {

        // 匹配以1开头第二位在3-9之间的11位数字
        Pattern p = Pattern.compile("^1[3-9]\\d{9}$");
        // 使用 Pattern 对象创建 Matcher 对象，并调用 matches() 方法进行匹配
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 验证电子邮箱格式是否正确
     *
     * @param email 待校验电子邮箱
     *
     * @return true：正确；false：错误
     */
    public static boolean isEmail(String email) {
        // 正则表达式，用于验证电子邮箱
        Pattern p = Pattern.compile("^[\\w-.]+@[\\w-.]+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 验证身份证号码格式
     *
     * @param idCard 待校验身份证号码
     *
     * @return true：正确；false：错误
     */
    public static boolean isIdCard(String idCard) {
        // 匹配身份证号码（15位、18位或17位加一位校验码）
        Pattern p = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");
        Matcher m = p.matcher(idCard);
        return m.matches();
    }
}
