<!-- eslint-disable vue/multi-word-component-names -->
<!-- 登录 -->
<template>
  <div class="container">
    <!-- 登录表单 -->
    <el-form
      class="login-form"
      ref="formRef"
      :model="form"
      label-width="120px"
      status-icon
      :rules="rules"
    >
      <div class="title-container">
        <h3 class="title">管理员登录</h3>
      </div>
      <el-form-item label="用户名：" prop="userName">
        <el-input
          v-model.trim="form.userName"
          type="text"
          clearable
          @input="(v: string) => (form.userName = v.replace(/[^0-9a-zA-Z@.]/g, ''))"
        />
      </el-form-item>
      <el-form-item label="密码：" prop="passWord">
        <el-input
          v-model="form.passWord"
          type="password"
          clearable
          @input="(v: string) => (form.passWord = v.replace(/[^0-9a-zA-Z.]/g, ''))"
        />
      </el-form-item>
      <el-form-item class="login-form-button">
        <el-button
          class="login-button"
          type="primary"
          @click="handleLogin(formRef)"
          >提交</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue";
import { FormInstance, FormRules, ElNotification } from "element-plus";
// 路由
import { useRouter } from "vue-router";
const router = useRouter();
import { sendRequest } from "@/utils/request";
import { sessionStorageUtil } from "@/utils/StorageUtil";
// 表单数据
const form = reactive({
  userName: "",
  passWord: "",
});

// 表单数据校验规则
const rules = reactive<FormRules<typeof form>>({
  userName: [{ required: true, message: "请输入用户名", trigger: "blur" }],
  passWord: [{ required: true, message: "请输入密码", trigger: "blur" }],
});

const formRef = ref<FormInstance>();

// 处理登录逻辑
const handleLogin = (value: {
  validate: (arg0: (validate: never) => Promise<void>) => void;
}) => {
  value?.validate(async (validate: unknown) => {
    if (validate) {
      // 发送登录请求
      sendRequest("POST", "/admin/login", form)
        .then((result: { code: unknown; data: unknown; message: never }) => {
          console.log(result);
          switch (result.code) {
            // 登录成功
            case 200:
              ElNotification({
                message: "登录成功",
                type: "success",
              });
              // Session保存
              sessionStorageUtil.set("token", result.data.token);
              sessionStorageUtil.set("userInfo", { name: form.userName });
              // 跳转到首页
              router.push("/");
              break;
            // 登录失败
            default:
              ElNotification({
                message: result.message,
                type: "warning",
              });
              break;
          }
        })
        .catch((err: unknown) => {
          console.log(err);
        });
    }
  });
};
</script>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;
$cursor: #fff;

.container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 35px;
    margin: 160px auto;
    overflow: hidden;
    background-color: #fff;

    ::v-deep .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }

    ::v-deep .login-form-button {
      background: #fff;
    }

    ::v-deep .el-form-item__container {
      color: #454545;
      background: rgba(0, 0, 0, 0.1);
    }

    ::v-deep .el-input__wrapper {
      background-color: #fff;
      box-shadow: none;
    }

    .title {
      display: flex;
      justify-content: center;
      align-items: center;
      margin-bottom: 30px;
      font-size: 35px;
    }

    input {
      background: transparent;
      border: 0px;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;
    }
  }

  .login-button {
    width: 100%;
    box-sizing: border-box;
  }
}
</style>
