package com.example.demo.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

/**
 * JwtUtil
 * <p>
 * JWT Token 工具类
 * <p>
 * 依赖jjwt-api/jjwt-impl/jjwt-jackson
 */
public class JwtUtil {

    // 定义加密秘钥
    private static final String SECRET_KEY = "123456";

    // 定义过期时间为一天
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * 增加自定义加密秘钥长度
     * 对密钥进行SHA-256哈希加密
     *
     * @return 返回64位编码作为kry
     */
    private static SecretKey key() {
        // 将字符串转换为字节数组
        byte[] bytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);

        // 使用SHA-256哈希将字节数组进行加密，生成32位长度的字节数组
        MessageDigest sha256 = null;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = sha256.digest(bytes);

        // 使用16进制编码将字节数组转换为64位长度的字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        // System.out.println("sb = " + sb);
        // 取32位
        // String encodedMessage = sb.substring(0, 32);

        SecretKey secretKey = Keys.hmacShaKeyFor(sb.toString().getBytes());

        return secretKey;
    }

    /**
     * 生成token
     *
     * @param payload 要存储在token中的数据
     *
     * @return token字符串
     */
    public static String generateToken(Map<String, Object> payload) {
        /**Jwts加密
         *第一部分为头部header，第二部分为载荷payload（消息主体），第三部分为签证signature
         *
         * header的格式（算法、token的类型）：{"alg": "HS512","typ": "JWT"}
         *payload的格式（用户名、创建时间、生成时间）：{"sub":"wang","created":1489079981393,"exp":1489684781} 客户端解密可获得数据
         *signature的生成算法：HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),SECRET_KEY(通过header中声明的加密方式进行加盐组合加密))
         *
         */
        return Jwts.builder()
                .setClaims(payload)// 数据
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, key()) // 设置算法及密钥
                .compact();
    }

    /**
     * 验证token是否有效
     *
     * @param token token字符串
     *
     * @return TRUE：有效；FALSE：无效
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对token进行解码获得数据
     *
     * @param token token字符串
     *
     * @return 数据map
     */
    public static Claims getClaims(String token) {
        // 使用Jwts对象解析token，获取Claims对象
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key()).parseClaimsJws(token);
        return claimsJws.getBody();
    }


    /**
     * 获取请求头中的Claims数据
     *
     * @param request HTTP请求对象
     *
     * @return Claims对象数据
     */
    public static Claims getRequestToClaims(HttpServletRequest request) {
        // 获取请求头中的Token值
        String token = request.getHeader("token");
        return getClaims(token);
    }
}
