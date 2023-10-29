/**
 * 用于处理会话数据的存储和管理
 * @returns {object} - 操作函数的对象
 */
export default function useSessionSession() {
  /**
   * 从sessionStorage中获取保存的会话数据
   * @param {string} SESSION_KEY - 会话数据的键名
   * @returns {any} - 返回会话数据的值，如果不存在则返回null
   */
  const loadSession = (SESSION_KEY) => {
    const value = sessionStorage.getItem(SESSION_KEY);
    return value ? JSON.parse(value) : null;
  };

  /**
   * 将当前会话保存到sessionStorage中
   * @param {string} SESSION_KEY - 会话数据的键名
   * @param {any} value - 要保存的会话数据的值
   */
  const saveSession = (SESSION_KEY, value) => {
    sessionStorage.setItem(SESSION_KEY, JSON.stringify(value));
  };

  /**
   * 删除指定的会话数据
   * @param {string} SESSION_KEY - 会话数据的键名
   */
  const removeSession = (SESSION_KEY) => {
    sessionStorage.removeItem(SESSION_KEY);
  };

  /**
   * 从sessionStorage中删除全部会话数据
   */
  const removeAll = () => {
    sessionStorage.clear();
  };
  return {
    loadSession,
    saveSession,
    removeSession,
    removeAll,
  };
}
