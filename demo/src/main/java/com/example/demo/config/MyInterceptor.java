package com.example.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器配置
 *
 * @Author https://www.javastudy.cloud
 **/
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 在请求到达处理程序之前调用
     *
     * @param request  表示当前的HTTP请求对象。通过HttpServletRequest对象，可以获取当前请求的信息，如请求头、请求参数等。
     * @param response 表示当前的HTTP响应对象。通过HttpServletResponse对象，可以设置响应状态码、响应头等HTTP协议相关信息。
     * @param handler  表示当前处理请求的Controller对象及其处理方法。通过handler对象，可以获取Controller对象和处理方法的信息，如Controller的类型和名称，处理方法的注解、参数等信息。
     *
     * @return true:继续处理后续的拦截器和处理程序；false:表示拦截器执行完毕，后续的拦截器和处理程序不再执行
     *
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // // 获取请求头中的Token值
        // String token = request.getHeader("token");
        // // token = header.substring(1, header.length() - 1);
        // if (JwtUtils.validateToken(token)) {
        //     String UserName = JwtUtils.getUsernameFromToken(token);
        //     // response.setHeader("UserName", UserName);
        //     request.setAttribute("UserName", UserName);
        //     return true;
        // }
        // return false; // 如果返回false，则请求将被拦截
        return true;
    }

    /**
     * preHandle 方法返回 true 时才会被调用
     * 在处理程序方法执行之后，视图渲染之前调用，允许修改ModelAndView对象。此时可以处理数据，并且可以选择是否需要再次拦截请求。
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在视图渲染完毕之后调用，释放资源。
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在视图渲染完成后执行的代码

    }
}
