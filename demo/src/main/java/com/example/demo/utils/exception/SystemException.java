package com.example.demo.utils.exception;

/**
 * SystemException 表示系统在执行期间发生了意外的情况。
 * 继承自 RuntimeException，表示这是一个非受查异常（unchecked exception），
 * 不需要显式地在方法签名中声明该异常。
 */
public class SystemException extends RuntimeException {

    /**
     * 异常代码
     */
    private final int code;

    /**
     * 构造函数，传入异常代码和异常信息
     *
     * @param code    异常代码
     * @param message 异常信息
     */
    public SystemException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造函数，传入异常代码、异常信息和原因
     *
     * @param code    异常代码
     * @param message 异常信息
     * @param cause   原因
     */
    public SystemException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
