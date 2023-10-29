<!-- eslint-disable vue/multi-word-component-names -->
<!-- 整体布局容器 -->
<template>
  <div class="wrapper">
    <!-- Element Plus 容器组件 el-container，实现页面布局 -->
    <el-container>
      <!-- 侧边栏容器，设置宽度为 200px -->
      <el-aside width="200px" class="container">
        <!-- 导航菜单组件 -->
        <Menu />
      </el-aside>
      <!-- 主内容区容器 -->
      <el-container>
        <!-- 顶部导航栏容器 -->
        <el-header>
          <div class="navbar">
            <Header />
            <!-- 导航栏右侧内容 -->
            <div class="navbar-right">
              <Avatar />
            </div>
          </div>
        </el-header>
        <!-- 主要内容区域，使用 Vue Router 的路由视图 -->
        <el-main> <router-view /></el-main>
        <!-- 页脚组件 -->
        <el-footer><Footer /></el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
// 导入需要的组件
import Menu from "@/views/layout/menu/index";
import Header from "@/views/layout/header/index";
import Avatar from "@/views/layout/header/avatar.vue";
import Footer from "@/views/layout/footer/index";
</script>

<style scoped>
.wrapper {
  position: relative;
  width: 100%;
  height: 100%;
}

.container {
  background-color: #fff;
  border-right: solid 1px #dcdfe6;
  height: 100%;
}

.el-container {
  height: 100%;
}

.el-menu {
  border-right: 0;
}

.navbar {
  width: 100%;
  height: 60px;
  overflow: hidden;
  background-color: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  padding: 0 16px;
  display: flex;
  align-items: center;
  box-sizing: border-box;
  position: relative;
}

.el-header {
  padding: 0;
}
.navbar-right {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
</style>
