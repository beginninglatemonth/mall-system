<!-- eslint-disable vue/multi-word-component-names -->
<!-- 使用 Element Plus 的面包屑组件 -->
<template>
  <el-breadcrumb separator="/">
    <!-- 遍历 breadcrumbList 数组生成面包屑项 -->
    <el-breadcrumb-item
      v-for="(item, index) in breadcrumbList"
      v-bind:key="index"
    >
      <!-- 最后一项不需要链接 -->
      <span v-if="index == breadcrumbList.length - 1">{{ item.name }} </span>
      <!-- 其他项使用 router-link 进行导航 -->
      <router-link v-else :to="{ path: item.path }"
        >{{ item.name }}
      </router-link>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup>
import { ref, watch } from "vue";
import { useRoute } from "vue-router";
const route = useRoute();
// 使用 ref 声明 breadcrumbList 变量，保存面包屑数据
const breadcrumbList = ref(route.matched);
// 初始化 breadcrumbList 数据
const initBreadcrumbList = () => {
  breadcrumbList.value = route.matched;
};
// 使用 watch 监听路由变化，当 route 变化时，调用 initBreadcrumbList 更新 breadcrumbList
watch(
  route,
  () => {
    initBreadcrumbList();
  },
  { deep: true, immediate: true }
);
</script>

<style scoped></style>
