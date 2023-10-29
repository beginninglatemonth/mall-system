import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/login",
    name: "login",
    component: () => import("../views/login/index.vue"),
  },
  {
    path: "/",
    name: "首页",
    component: () => import("../views/layout/index.vue"),
    redirect: "/home",
    children: [
      {
        path: "/home",
        name: "首页",
        component: () => import("../views/home/index.vue"),
      },
      {
        path: "/user",
        name: "用户管理",
        component: () => import("../views/user/index.vue"),
      },
      {
        path: "/mainType",
        name: "商品一级类别管理",
        component: () => import("../views/mainType/index.vue"),
      },
      {
        path: "/secondaryType",
        name: "商品二级类别管理",
        component: () => import("../views/secondaryType/index.vue"),
      },
      {
        path: "/goodsManage",
        name: "商品管理",
        component: () => import("../views/goodsManage/index.vue"),
      },
      {
        path: "/orderManage",
        name: "订单管理",
        component: () => import("../views/orderManage/index.vue"),
      },
      {
        path: "/revisePassWord",
        name: "修改密码",
        component: () => import("../views/revisePassWord/index.vue"),
      },
    ],
    meta: {
      requiresAdmin: true, // 需要管理员权限
    },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

// 路由守卫
router.beforeEach((to, from, next) => {
  // 在这里执行身份验证和权限检查逻辑,检查用户是否已登录或具有特定权限

  /* 检查用户是否已登录的逻辑 */
  const isAuthenticated = "";

  /* 检查用户是否具有权限的逻辑 */
  const hasPermission = () => {
    const token = sessionStorage.getItem("token");
    if (token) {
      return true;
    }
    return false; // 登录失败
  };

  if (to.meta.requiresAuth && !isAuthenticated) {
    // 如果需要身份验证且用户未登录，则重定向到登录页面
    next("/login");
  } else if (to.meta.requiresAdmin && !hasPermission()) {
    // 如果需要管理员权限且用户没有权限，则重定向到权限不足页面或其他页面
    next("/login");
  } else {
    // 如果通过身份验证和权限检查，则允许导航
    next();
  }
});

export default router;
