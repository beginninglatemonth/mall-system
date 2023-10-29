package com.example.demo.utils.exception;

/**
 * BusinessException 表示业务逻辑出现异常。
 * 继承自 RuntimeException，表示这是一个非受查异常（unchecked exception），
 * 不需要显式地在方法签名中声明该异常。
 */
public class BusinessException extends RuntimeException {

    /**
     * 异常代码
     */
    private final int code;

    /**
     * 构造函数，传入异常信息和异常代码
     *
     * @param message 异常信息
     * @param code    异常代码
     */
    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    /**
     * 构造函数，传入异常信息、原因和异常代码
     *
     * @param message 异常信息
     * @param cause   原因
     * @param code    异常代码
     */
    public BusinessException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
