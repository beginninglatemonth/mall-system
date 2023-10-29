package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 过滤器跨域配置
 * 依赖 spring-boot-starter-web
 */
@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();

        // 允许跨域的头部信息
        config.addAllowedHeader("*");

        // 允许跨域的方法
        // config.addAllowedMethod("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");


        // 可访问的外部域
        config.addAllowedOrigin("*");
        // config.addAllowedOrigin("http://localhost:8000");
        // config.addAllowedOrigin("http://192.168.2.101:8000");

        // 需要跨域用户凭证（cookie、HTTP认证及客户端SSL证明等）
        // config.setAllowCredentials(true);
        // config.addAllowedOriginPattern("*");

        // 跨域路径配置
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
