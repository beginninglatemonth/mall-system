<!-- eslint-disable vue/multi-word-component-names -->
  <!-- 订单管理 -->
<template>
  <el-card>
      <!-- 搜索栏 -->
    <el-row class="header" gutter="20">
      <el-col :span="5">
        <el-input placeholder="订单号" v-model.trim="queryForm.query" clearable>
        </el-input>
      </el-col>
      <el-button type="primary" :icon="Search" @click="handleSearchChange">
        搜索
      </el-button>
    </el-row>
     <!-- 订单列表 -->
    <el-table :data="queryData" style="width: 100%">
      <el-table-column prop="id" label="序号" width="100" fixed>
        <template #default="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="orderNo" label="订单号" width="200" />
      <el-table-column prop="userId" label="用户ID" width="400" />
      <el-table-column prop="totalPrice" label="订单总价" width="200" />
      <el-table-column prop="status" label="订单状态" width="200">
        <template #default="{ row }">
          {{ row.status == 1 ? "待支付" : "" }}
          {{ row.status == 2 ? "待发货" : "" }}
          {{ row.status == 3 ? "已发货" : "" }}
          {{ row.status == 4 ? "退款/退货" : "" }}
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="订单创建日期" width="200" />
      <el-table-column prop="payTime" label="订单支付日期" width="200" />
      <el-table-column prop="consignee" label="收货人" width="200" />
      <el-table-column prop="telNumber" label="联系电话" width="200" />
      <el-table-column prop="address" label="收货地址" width="200" />
      <el-table-column prop="isDeleted" label="删除标识" width="100">
        <template #default="{ row }">
          {{ row.isDeleted === 0 ? "未删除" : "已删除" }}
        </template>
      </el-table-column>
      <el-table-column prop="isDeleted" label="操作" width="350" fixed="right">
        <template #default="{ row }">
          <el-button type="success" @click="handleDetailValue(row.id)"
            >详情</el-button
          >
          <el-button type="primary" @click="handleStatusChange(row.id, '3')"
            >发货</el-button
          >
          <el-button type="primary" @click="handleStatusChange(row.id, '4')"
            >退款/退货</el-button
          >
          <el-button
            type="danger"
            :icon="Delete"
            @click="handleStatusChange(row.id, '删除')"
          />
        </template>
      </el-table-column>
    </el-table>
     <!-- 分页组件 -->
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
  <!-- 订单详情对话框 -->
  <el-dialog
    v-model="dialogTableVisible"
    title="订单详细信息"
    width="40%"
    center
  >
    <el-table :data="detailData">
      <el-table-column prop="id" label="序号" width="150" fixed>
        <template #default="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column property="image" label="商品图片" width="150">
        <template v-slot="scope">
          <img :src="baseURL + scope.row.image" alt="" width="50" height="50" />
        </template>
      </el-table-column>
      <el-table-column property="name" label="商品名称" width="150" />
      <el-table-column property="price" label="商品价格" width="150" />
      <el-table-column property="number" label="商品数量" width="150" />
    </el-table>
  </el-dialog>
</template>

<script setup>
import { Search, Delete } from "@element-plus/icons-vue";
import { ref, reactive, onMounted } from "vue";

import { baseURL, sendRequest } from "@/utils/request";
import { ElementUtil } from "@/utils/ElementUtil";

onMounted(() => {
  initList();
});

const queryData = ref();
const queryForm = ref({
  query: "",
  current: 1,
  size: 10,
  totalCount: 0,
});
const dialogTableVisible = ref(false);
const detailData = ref();
// 处理分页大小变化
const handleSizeChange = (val) => {
  queryForm.value.current = 1;
  queryForm.value.size = val;
  initList();
};
// 处理当前页变化
const handleCurrentChange = (val) => {
  queryForm.value.current = val;
  initList();
};
// 初始化订单列表
const initList = async () => {
  try {
    const res = await sendRequest(
      "get",
      "admin/order/" +
        queryForm.value.current +
        "/" +
        queryForm.value.size +
        "?orderNo=" +
        queryForm.value.query.trim()
    );
    queryData.value = res.data.data;
    queryForm.value.totalCount = res.data.count;
  } catch (error) {
    console.log(error);
  }
};
// 处理搜索按钮点击
const handleSearchChange = () => {
  queryForm.value.current = 1;
  initList();
};
// 处理订单状态变化
const handleStatusChange = async (id, status) => {
  try {
    const result = await sendRequest("put", "admin/order/" + id, {
      status: status,
    });
    switch (result.code) {
      case 200:
        ElementUtil.ElNotification(null, "操作完成", "success");
        initList();
        break;
      default:
        ElementUtil.ElNotification(null, result.message, "warning");
        break;
    }
  } catch (error) {
    console.log(error);
  }
};
// 处理订单详情按钮点击
const handleDetailValue = async (id) => {
  dialogTableVisible.value = true;
  try {
    const result = await sendRequest("get", "admin/orderDetail/" + id);
    switch (result.code) {
      case 200:
        detailData.value = result.data;
        break;
      default:
        ElementUtil.ElNotification(null, result.message, "warning");
        break;
    }
  } catch (error) {
    console.log(error);
  }
};
</script>

<style lang="scss" scoped>
.el-input {
  width: 300px;
}

.el-pagination {
  margin-top: 15px;
  box-sizing: border-box;
}
</style>
