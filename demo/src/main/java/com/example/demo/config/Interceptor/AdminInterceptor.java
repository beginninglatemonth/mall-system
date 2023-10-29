package com.example.demo.config.Interceptor;

import com.example.demo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * AdminInterceptor
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的Token值
        String token = request.getHeader("token");
        if (JwtUtil.validateToken(token)) {
            Claims claims = JwtUtil.getClaims(token);
            if ("admin".equals(claims.get("type"))) return true;
        }
        ServletOutputStream os = response.getOutputStream();
        String result = "{\"code\":11000,\"message\":\"用户未登录\"}";

        os.write(result.getBytes());
        os.flush();
        os.close();

        return false; // 如果返回false，则请求将被拦截
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
