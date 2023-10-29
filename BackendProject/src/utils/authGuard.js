
// 导入 Vue Router
import { useRouter } from 'vue-router';

// 创建路由守卫函数
export function createAuthGuard() {
  const router = useRouter();

  // 路由守卫
  router.beforeEach((to, from, next) => {
    // 在这里执行身份验证和权限检查逻辑,检查用户是否已登录或具有特定权限

    /* 检查用户是否已登录的逻辑 */
    const isAuthenticated = ''

    /* 检查用户是否具有权限的逻辑 */
    const hasPermission = () => {
      const token = sessionStorage.getItem("token");
      if (token) {
        return true
      }
      return false; // 登录失败
    };

    if (to.meta.requiresAuth && !isAuthenticated) {
      // 如果需要身份验证且用户未登录，则重定向到登录页面
      next('/login');
    } else if (to.meta.requiresAdmin && !hasPermission) {
      // 如果需要管理员权限且用户没有权限，则重定向到权限不足页面或其他页面
      next('/login');
    } else {
      // 如果通过身份验证和权限检查，则允许导航
      next();
    }
  });
}
