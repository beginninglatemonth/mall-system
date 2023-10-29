<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <el-dropdown>
    <el-avatar shape="square" :size="40" :src="squareUrl" />

    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click="handleLogout">退出</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script setup>
import { ref } from "vue";
const squareUrl = ref();

import { useRouter } from "vue-router";
const router = useRouter();
import { useUserUtil } from "@/utils/useUserUtil";
const handleLogout = () => {
  const { logout } = useUserUtil(router);
  logout();
};
</script>

<style scoped></style>
