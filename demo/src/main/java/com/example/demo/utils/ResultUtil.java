package com.example.demo.utils;

/**
 * 通用返回结果工具类
 *
 * @param <T> 返回数据类型
 */

public class ResultUtil<T> {

    // 状态码
    private int code;

    // 消息
    private String message;

    // 数据
    private T data;

    /**
     * 默认构造函数
     */
    public ResultUtil() {

    }

    /**
     * 成功返回的构造函数
     *
     * @param data 数据
     */
    public ResultUtil(T data) {
        this.code = ResultCodeUtil.SUCCESS.getCode();
        this.message = ResultCodeUtil.SUCCESS.getMessage();
        this.data = data;
    }

    /**
     * 根据传入的ResultCodeUtil对象设置code和message
     *
     * @param result 自定义ResultCodeUtil
     */
    public ResultUtil(ResultCodeUtil result) {
        this.code = result.getCode();
        this.message = result.getMessage();
    }

    /**
     * 根据传入的code和message设置属性值
     *
     * @param code    状态码
     * @param message 消息
     */
    public ResultUtil(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
