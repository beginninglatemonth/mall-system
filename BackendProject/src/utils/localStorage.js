/**
 * 用于处理会话数据的存储和管理
 * @returns {object} - 操作函数的对象
 */
export default function useLocalStorage() {
  // const expirationTime = Date.now() + 24 * 60 * 60 * 1000 // 过期时间为一天后

  /**
   * 从localStorage中获取保存的会话数据
   * @param {string} SESSION_KEY - 会话数据的键名
   * @returns {any} - 返回会话数据的值，如果不存在则返回null
   */
  const loadSession = (SESSION_KEY) => {
    const value = localStorage.getItem(SESSION_KEY);
    return value ? JSON.parse(value) : null;
  };

  /**
   * 将当前会话保存到localStorage中
   * @param {string} SESSION_KEY - 会话数据的键名
   * @param {any} value - 要保存的会话数据的值
   */
  const saveSession = (SESSION_KEY, value) => {
    localStorage.setItem(SESSION_KEY, JSON.stringify(value));
  };

  /**
   * 删除指定的会话数据
   * @param {string} SESSION_KEY - 会话数据的键名
   */
  const removeSession = (SESSION_KEY) => {
    localStorage.removeItem(SESSION_KEY);
  };

  /**
   * 从localStorage中删除全部会话数据
   */
  const removeAll = () => {
    localStorage.clear();
  };
  return {
    loadSession,
    saveSession,
    removeSession,
    removeAll,
  };
}
