package com.example.demo.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * HttpClientUtil
 * HTTP客户端工具类
 */
public class HttpClientUtil {

    private final RestTemplate restTemplate;

    // 构造函数注入RestTemplate实例
    public HttpClientUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 发送GET请求
     *
     * @param url 请求的URL
     *
     * @return ResponseEntity 包含HTTP响应和响应体的实体
     */
    public ResponseEntity<String> sendGetRequest(String url) {
        // 设置HTTP请求头
        HttpHeaders headers = new HttpHeaders();
        // 创建HTTP实体，包含请求头信息
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 使用RestTemplate发送HTTP GET请求
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response;
    }

    /**
     * 发送POST请求
     *
     * @param url     请求的URL
     * @param request 请求实体
     *
     * @return ResponseEntity 包含HTTP响应和响应体的实体
     */
    public ResponseEntity<String> sendPostRequest(String url, HttpEntity<String> request) {
        // 使用RestTemplate发送HTTP POST请求
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return response;
    }

    /**
     * 发送PUT请求
     *
     * @param url     请求的URL
     * @param request 请求实体
     *
     * @return ResponseEntity 包含HTTP响应和响应体的实体
     */
    public ResponseEntity<String> sendPutRequest(String url, HttpEntity<String> request) {
        // 使用RestTemplate发送HTTP PUT请求
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

        return response;
    }

    /**
     * 发送DELETE请求
     *
     * @param url 请求的URL
     *
     * @return ResponseEntity 包含HTTP响应和响应体的实体
     */
    public ResponseEntity<String> sendDeleteRequest(String url) {
        // 使用RestTemplate发送HTTP DELETE请求
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);

        return response;
    }

}
