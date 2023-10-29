import axios from "axios";
import { useUserUtil } from "@/utils/useUserUtil";

// 路由
import router from "@/router";

export const baseURL = "http://localhost:8080/";

const request = axios.create({
  // baseURL: '',
  baseURL: baseURL, // 后端接口前缀，后端进行跨域配置
  timeout: 5000,
});

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(
  (config) => {
    // 从本地存储获取 token，将其添加到请求头
    let token = sessionStorage.getItem("token");
    if (token) {
      config.headers["token"] = JSON.parse(token);
    }

    config.headers["Content-Type"] = "application/json;charset=utf-8";
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
  (response) => {
    let res = response.data;

    // 处理登录失效情况
    if (res.code == 11000) {
      const { logout } = useUserUtil(router);
      logout();
      router.push("/login");
    }

    // 处理被拦截器拦截的情况
    if (response.headers["content-length"] == "0") {
      res = { code: 0, message: "登录失效了，请重新登录" };
      router.push("/login");
    }

    // 如果是返回的文件
    if (response.config.responseType === "blob") {
      return res;
    }

    return res;
  },
  (error) => {
    return Promise.reject(error);
  }
);

/**
 * 发送请求
 * @param {string} method - 请求方法 ('get', 'post', 'put', 'delete')
 * @param {string} url - 请求的地址
 * @param {object} data - 请求数据 (仅用于 POST 和 PUT 请求)
 * @returns {Promise} - 返回一个包含请求结果的 Promise
 */
export async function sendRequest(method, url, data = null) {
  switch (method.toLowerCase()) {
    case "get":
      return await request.get(url);
    case "post":
      return await request.post(url, data);
    case "put":
      return await request.put(url, data);
    case "delete":
      return await request.delete(url);
    default:
      throw new Error("Invalid HTTP method");
  }
}

/**
 * 上传图片和添加数据
 * @param {string} url - 请求的地址
 * @param {object} data - 添加的数据
 * @returns {Promise} - 返回一个包含请求结果的 Promise
 */
export async function PostImage(url, data) {
  const headers = {
    "Content-Type": "multipart/form-data", // 设置请求头为 multipart/form-data
  };
  const token = sessionStorage.getItem("token");
  if (token) {
    headers["token"] = JSON.parse(token);
  }
  const result = await axios.post(baseURL + url, data, { headers });
  return result;
}
