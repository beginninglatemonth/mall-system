package com.example.demo.controller;

import com.example.demo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Validator
 * 数据验证与校验
 */
@RestController
public class Validator {

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取Token中的openId
     *
     * @return openId
     */
    public String getOpenId() {
        Claims claims = JwtUtil.getRequestToClaims(request);
        String openId = claims.get("openId").toString();
        return openId;
    }

    /**
     * 获取Token中的nickName
     *
     * @return nickName
     */
    public String getNickName() {
        Claims claims = JwtUtil.getRequestToClaims(request);
        String nickName = claims.get("nickName").toString();
        return nickName;
    }

    /**
     * 获取Token中的userName
     *
     * @return nickName
     */
    public String getUserName() {
        Claims claims = JwtUtil.getRequestToClaims(request);
        String userName = claims.get("userName").toString();
        return userName;
    }
}
