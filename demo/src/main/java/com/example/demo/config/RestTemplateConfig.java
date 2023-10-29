package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateConfig
 */
@Configuration    // 使用@Configuration注解标记这是一个Spring配置类，它包含了一些Bean的配置信息。
public class RestTemplateConfig {
    @Bean// 通过@Bean注解，定义了一个名为"restTemplate"的Spring Bean，它返回一个新的RestTemplate实例。
    public RestTemplate restTemplate() {
        // 创建并返回一个新的RestTemplate实例，该实例用于执行HTTP请求的工具类。
        // RestTemplate是Spring提供的HTTP客户端工具，用于简化HTTP请求的发送和处理。
        // 默认情况下，Spring Boot会自动配置一个RestTemplate，可以根据需要进行自定义配置。
        return new RestTemplate();
    }
}
