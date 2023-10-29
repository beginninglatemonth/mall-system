/**
 * 封装操作localStorage本地存储的方法
 */
export const localStorageUtil = {
  /**
   * 存储数据到localStorage
   * @param {string} key - 存储的键名
   * @param {any} value - 存储的数据值
   */
  set(key, value) {
    try {
      localStorage.setItem(key, JSON.stringify(value));
    } catch (error) {
      console.error(`localStorage set error for key "${key}":`, error);
    }
  },

  /**
   * 从localStorage获取数据
   * @param {string} key - 要获取的键名
   * @returns {T | null} - 获取到的数据，如果不存在则返回null
   */
  get(key) {
    try {
      const value = localStorage.getItem(key);
      if (value !== null && value !== "undefined" && value !== "null") {
        return JSON.parse(value);
      }
    } catch (error) {
      console.error(`localStorage get error for key "${key}":`, error);
    }
    return null;
  },

  /**
   * 从localStorage删除数据
   * @param {string} key - 要删除的键名
   */
  remove(key) {
    try {
      localStorage.removeItem(key);
    } catch (error) {
      console.error(`localStorage remove error for key "${key}":`, error);
    }
  },

  /**
   * 从localStorage删除全部数据
   */
  removeAll() {
    localStorage.clear();
  },
};

/**
 * 封装操作sessionStorage本地存储的方法
 */
export const sessionStorageUtil = {
  /**
   * 存储数据到sessionStorage
   * @param {string} key - 存储的键名
   * @param {any} value - 存储的数据值
   */
  set(key, value) {
    try {
      sessionStorage.setItem(key, JSON.stringify(value));
    } catch (error) {
      console.error(`sessionStorage set error for key "${key}":`, error);
    }
  },

  /**
   * 从sessionStorage获取数据
   * @param {string} key - 要获取的键名
   * @returns {T | null} - 获取到的数据，如果不存在则返回null
   */
  get(key) {
    try {
      const value = sessionStorage.getItem(key);
      if (value !== null && value !== "undefined" && value !== "null") {
        return JSON.parse(value);
      }
    } catch (error) {
      console.error(`sessionStorage get error for key "${key}":`, error);
    }
    return null;
  },

  /**
   * 从sessionStorage删除数据
   * @param {string} key - 要删除的键名
   */
  remove(key) {
    try {
      sessionStorage.removeItem(key);
    } catch (error) {
      console.error(`sessionStorage remove error for key "${key}":`, error);
    }
  },

  /**
   * 从localStorage删除全部数据
   */
  removeAll() {
    sessionStorage.clear();
  },
};

/**
 * 
 * 使用说明
 * 
import { localStorageUtil, sessionStorageUtil } from '@/utils/StorageUtil'; // 请替换为你的实际文件路径

// 存储数据到localStorage
localStorageUtil.set('user', { name: 'John', age: 30 });

// 从localStorage获取数据
const userFromLocalStorage = localStorageUtil.get('user');
console.log('User from localStorage:', userFromLocalStorage);

// 从localStorage删除数据
localStorageUtil.remove('user');

// 存储数据到sessionStorage
sessionStorageUtil.set('token', 'your-auth-token');

// 从sessionStorage获取数据
const tokenFromSessionStorage = sessionStorageUtil.get('token');
console.log('Token from sessionStorage:', tokenFromSessionStorage);

// 从sessionStorage删除数据
sessionStorageUtil.remove('token');

 */
