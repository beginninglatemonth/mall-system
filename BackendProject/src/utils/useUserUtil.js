/**
 * 用户工具类
 * @returns {object} 用户函数的对象
 */
export function useUserUtil(router) {
  /**
   * 用户注销函数
   */
  const logout = () => {
    // 清除本地存储的 token
    localStorage.removeItem("token");
    localStorage.removeItem("userInfo");
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("userInfo");

    router.replace("/login");
  };
  return { logout };
}
