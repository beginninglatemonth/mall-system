<!-- eslint-disable vue/multi-word-component-names -->
<!-- 用户修改密码 -->
<template>
  <el-card>
    <el-form
      ref="formRef"
      :model="form"
      label-width="120px"
      status-icon
      :rules="rules"
    >
      <el-form-item label="用户名：" prop="userName">
        <el-input v-model.trim="form.userName" type="text" disabled />
      </el-form-item>
      <el-form-item label="新密码：" prop="passWord">
        <el-input v-model.trim="form.passWord" type="password" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleRevisePassWord(formRef)">
          提交
        </el-button></el-form-item
      >
    </el-form>
  </el-card>
</template>

<script setup>
import { ref, reactive, onMounted } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
import { sendRequest } from "@/utils/request";
import { sessionStorageUtil } from "@/utils/StorageUtil";
import { ElementUtil } from "@/utils/ElementUtil";
import { useUserUtil } from "@/utils/useUserUtil";
// 处理用户登出
const handleLogout = () => {
  const { logout } = useUserUtil(router);
  logout();
};
// 在组件挂载后，从 sessionStorage 中获取用户信息填充用户名
onMounted(() => {
  const userInfo = sessionStorageUtil.get("userInfo");
  form.userName = userInfo.name;
});

// 表单
const form = reactive({
  userName: "",
  passWord: "",
});

// 表单数据校验
const rules = reactive({
  passWord: [{ required: true, message: "请输入密码", trigger: "blur" }],
});

const formRef = ref();
/**
 * 处理提交修改密码
 * @param {Ref} value - 表单引用
 */
const handleRevisePassWord = (value) => {
  value?.validate(async (validate) => {
    if (validate) {
      sendRequest("POST", "/admin/revisePassWord", form)
        .then((result) => {
          switch (result.code) {
            case 200:
              ElementUtil.ElNotification(null, "修改成功", "success");
              handleLogout();
              // 跳转
              router.push("/login");
              break;
            default:
              ElementUtil.ElNotification(null, result.message, "warning");
              break;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    }
  });
};
</script>

<style lang="scss" scoped>
.el-input {
  width: 300px;
}
</style>
