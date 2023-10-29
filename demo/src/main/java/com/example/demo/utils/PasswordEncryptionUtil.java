package com.example.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密和验证工具类，基于BCrypt算法。
 */

public class PasswordEncryptionUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    /**
     * 加密密码
     *
     * @param rawPassword 原始密码
     *
     * @return 加密后的密码
     */
    public static String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 验证密码是否匹配
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 加密后的密码
     *
     * @return 密码匹配返回true，否则返回false
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
