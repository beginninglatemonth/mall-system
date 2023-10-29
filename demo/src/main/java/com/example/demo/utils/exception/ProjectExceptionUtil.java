package com.example.demo.utils.exception;

import com.example.demo.utils.ResultCodeUtil;
import com.example.demo.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */

@Slf4j
@RestControllerAdvice
public class ProjectExceptionUtil {
    // 配置拦截异常的类型     @ExceptionHandler(Exception.class)

    /**
     * 系统异常统一拦截
     *
     * @param e SystemException 异常对象
     *
     * @return 统一返回格式的ResultUtil对象
     */
    @ExceptionHandler(SystemException.class)
    public ResultUtil systemException(SystemException e) {
        // 1.记录日志
        log.error("全局系统异常捕获>>>:" + e.getMessage());
        // 2.发送运维
        // 3.发送开发者

        return new ResultUtil(e.getCode(), e.getMessage());
    }

    /**
     * 业务异常统一拦截
     *
     * @param e BusinessException 异常对象
     *
     * @return 统一返回格式的ResultUtil对象
     */
    @ExceptionHandler(BusinessException.class)
    public ResultUtil businessException(BusinessException e) {
        log.error("全局业务异常捕获>>>:" + e.getMessage());
        return new ResultUtil(e.getCode(), e.getMessage());
    }

    /**
     * 其他异常统一拦截
     *
     * @param e Exception 异常对象
     *
     * @return 统一返回格式的ResultUtil对象
     */
    @ExceptionHandler(Exception.class)
    public ResultUtil projectException(Exception e) {
        log.error("全局其他异常捕获>>>:" + e.getMessage());
        return new ResultUtil(ResultCodeUtil.NETWORK_ERROR);
    }

}
