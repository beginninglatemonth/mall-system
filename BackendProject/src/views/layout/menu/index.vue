<!-- eslint-disable vue/multi-word-component-names -->
<!-- 使用 Element Plus 的垂直菜单组件 el-menu -->
<template>
  <el-menu default-active="1" class="el-menu-vertical-demo" router>
    <!-- 首页菜单项 -->
    <el-menu-item index="home">
      <el-icon> <House /> </el-icon>
      <span>首页</span>
    </el-menu-item>
    <!-- 用户管理菜单项 -->
    <el-menu-item index="user">
      <el-icon> <User /> </el-icon>
      <span>用户管理</span>
    </el-menu-item>
    <!-- 商品类别管理子菜单项 -->
    <el-sub-menu>
      <template #title>
        <el-icon><icon-menu /></el-icon>
        <span>商品类别管理</span>
      </template>
      <!-- 商品一级类别管理菜单项 -->
      <el-menu-item index="mainType">
        <el-icon><icon-menu /></el-icon>
        <span>商品一级类别管理</span>
      </el-menu-item>
      <!-- 商品二级类别管理菜单项 -->
      <el-menu-item index="secondaryType">
        <el-icon><icon-menu /></el-icon>
        <span>商品二级类别管理</span>
      </el-menu-item>
    </el-sub-menu>
    <!-- 商品管理菜单项 -->
    <el-menu-item index="goodsManage">
      <el-icon> <Goods /> </el-icon>
      <span>商品管理</span>
    </el-menu-item>
    <!-- 订单管理菜单项 -->
    <el-menu-item index="orderManage">
      <el-icon> <document /> </el-icon>
      <span>订单管理</span>
    </el-menu-item>
    <!-- 系统管理子菜单项 -->
    <el-sub-menu>
      <template #title>
        <el-icon> <setting /> </el-icon>
        <span>系统管理</span>
      </template>
      <!-- 修改密码菜单项 -->
      <el-menu-item index="revisePassWord">
        <el-icon> <setting /> </el-icon>
        <span>修改密码</span>
      </el-menu-item>
      <!-- 退出登录菜单项，使用 @click 事件处理退出动作 -->
      <el-menu-item>
        <el-icon> <setting /> </el-icon>
        <span @click="handleLogout">退出登录</span>
      </el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script setup>
import {
  House,
  User,
  Menu as IconMenu,
  Goods,
  Document,
  Setting,
} from "@element-plus/icons-vue";

import { useRouter } from "vue-router";
import { useUserUtil } from "@/utils/useUserUtil";
const router = useRouter();
const { logout } = useUserUtil(router);
// 处理退出登录事件
const handleLogout = () => {
  logout();
};
</script>

<style scoped></style>
