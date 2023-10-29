<!-- eslint-disable vue/multi-word-component-names -->
<!-- 用户管理 -->
<template>
  <el-card>
    <el-row class="header" gutter="20">
      <el-col :span="7"
        ><el-input
          placeholder="用户名称"
          v-model="queryForm.query"
          clearable
        ></el-input
      ></el-col>
      <el-button type="primary" :icon="Search" @click="handleSearchChange"
        >搜索</el-button
      >
    </el-row>
    <el-table :data="queryData" style="width: 100%">
      <el-table-column prop="id" label="序号" width="100">
        <template #default="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="nickName" label="名称" width="250" />
      <el-table-column prop="avatarUrl" label="头像" width="250">
        <template v-slot="scope">
          <img
            v-if="scope.row.avatarUrl != null"
            :src="scope.row.avatarUrl"
            alt=""
            width="50"
            height="50"
          />
        </template>
      </el-table-column>
      <el-table-column prop="openId" label="微信ID" width="400" />
      <el-table-column prop="createTime" label="创建时间" width="250" />
      <el-table-column prop="reviseTime" label="修改时间" width="250" />
      <el-table-column prop="isDeleted" label="删除标识" width="100">
        <template v-slot="scope">
          {{ scope.row.isDeleted === 0 ? "未删除" : "已删除" }}
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页器 -->
    <div class="demo-pagination-block">
      <el-pagination
        v-model:current-page="queryForm.current"
        v-model:page-size="queryForm.size"
        :page-sizes="[10, 20, 30, 40, 50]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="queryForm.totalCount"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </el-card>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { Search } from "@element-plus/icons-vue";
import { sendRequest } from "@/utils/request";
const queryData = ref();

const queryForm = ref({
  query: "",
  current: 1,
  size: 10,
  totalCount: 0,
});
// 处理每页显示条数变更
const handleSizeChange = (val) => {
  queryForm.value.current = 1;
  queryForm.value.size = val;
  initUserList();
};
// 处理当前页码变更
const handleCurrentChange = (val) => {
  queryForm.value.current = val;
  initUserList();
};

// 初始化用户列表
const initUserList = async () => {
  try {
    const res = await sendRequest(
      "get",
      "admin/user/" +
        queryForm.value.current +
        "/" +
        queryForm.value.size +
        "?name=" +
        queryForm.value.query
    );
    queryData.value = res.data.data;
    queryForm.value.totalCount = res.data.count;
  } catch (error) {
    console.log(error);
  }
};
// 处理搜索按钮点击事件
const handleSearchChange = () => {
  queryForm.value.current = 1;
  initUserList();
};
// 在组件挂载后，初始化用户列表
onMounted(() => {
  initUserList();
});
</script>

<style scoped>
.header {
  padding-bottom: 16px;
  box-sizing: border-box;
}
.el-pagination {
  margin-top: 15px;
  box-sizing: border-box;
}
.demo-pagination-block + .demo-pagination-block {
  margin-top: 10px;
}
.demo-pagination-block .demonstration {
  margin-bottom: 16px;
}
</style>
