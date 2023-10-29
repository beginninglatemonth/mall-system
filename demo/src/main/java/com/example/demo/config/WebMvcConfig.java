package com.example.demo.config;

import com.example.demo.config.Interceptor.AdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 启用拦截器配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 在这里添加自己定义的拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 用户拦截
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");

    }

    /**
     * 资源访问配置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 商品图片
        registry.addResourceHandler("/image/goods/**").addResourceLocations("classpath:/static/images/goods/");
        // 商品轮播图
        registry.addResourceHandler("/image/carousel/**").addResourceLocations("classpath:/static/images/carousel/");
        // 一级分类
        registry.addResourceHandler("/image/mainType/**").addResourceLocations("classpath:/static/images/mainType/");
        // 商品介绍图片
        registry.addResourceHandler("/image/introductoryPictures/**").addResourceLocations("classpath:/static/images/introductoryPictures/");
        // 商品规格参数图片
        registry.addResourceHandler("/image/pictureSpecifications/**").addResourceLocations("classpath:/static/images/pictureSpecifications/");
        // 商品详细页轮播图
        registry.addResourceHandler("/image/detailCarousel/**").addResourceLocations("classpath:/static/images/detailCarousel/");
    }

}
