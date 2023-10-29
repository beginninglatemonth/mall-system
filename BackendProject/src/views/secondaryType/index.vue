<!-- eslint-disable vue/multi-word-component-names -->
<!-- 商品二类 -->

<template>
  <el-card>
    <el-row class="header" gutter="20">
      <el-col :span="5">
        <el-input
          placeholder="二级类别名称"
          v-model="queryForm.query"
          clearable
        />
      </el-col>
      <div>
        <el-button type="primary" :icon="Search" @click="handleSearchChange">
          搜索
        </el-button>
        <el-button type="primary" @click="handleAddChange">
          添加分类
        </el-button>
      </div>
    </el-row>
    <el-table :data="queryData" style="width: 100%">
      <el-table-column prop="id" label="序号" width="100" fixed>
        <template #default="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="name" label="名称" width="300" />
      <el-table-column prop="type" label="所属分类" width="300" />
      <el-table-column prop="remark" label="描述" />
      <el-table-column prop="isDeleted" label="删除标识" width="100">
        <template #default="{ row }">
          {{ row.isDeleted === 0 ? "未删除" : "已删除" }}
        </template>
      </el-table-column>
      <el-table-column prop="isDeleted" label="操作" width="350" fixed="right">
        <template #default="{ row }">
          <el-button type="primary" @click="handleReviseChange(row)"
            >修改</el-button
          >
          <el-button
            type="danger"
            :icon="Delete"
            @click="handleDeleteChange(row.id)"
          />
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
  <!-- 添加/修改二级类别的弹窗 -->
  <el-dialog v-model="dialogTableVisible" title="二级类别" width="25%" center>
    <el-form
      ref="formRef"
      :model="form"
      label-width="100"
      status-icon
      :rules="rules"
    >
      <el-form-item label="名称：" prop="name">
        <el-input v-model.trim="form.name" type="text" clearable />
      </el-form-item>
      <el-form-item label="所属分类：">
        <el-select v-model="form.mainTypeId">
          <el-option
            v-for="item in mainType"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="描述：" prop="remark">
        <el-input
          v-model.trim="form.remark"
          type="textarea"
          clearable
          :rows="2"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSave()" v-if="form.id == null">
          添加
        </el-button>
        <el-button
          type="primary"
          @click="handleRevise()"
          v-if="form.id != null"
        >
          修改
        </el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script setup>
import { Search, Delete, Plus } from "@element-plus/icons-vue";
import { ref, reactive, onMounted } from "vue";

import { baseURL, sendRequest, PostImage } from "@/utils/request";
import { ElementUtil } from "@/utils/ElementUtil";
// 在组件挂载后，初始化列表数据
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
const formRef = ref();
const mainType = ref();
// 表单
const form = reactive({
  name: "",
  remark: "",
  mainTypeId: "",
});

// 表单数据校验
const rules = reactive({
  name: [{ required: true, message: "请输入名称", trigger: "blur" }],
  remark: [{ required: true, message: "请输入描述", trigger: "blur" }],
});
// 处理每页显示条数变更
const handleSizeChange = (val) => {
  queryForm.value.current = 1;
  queryForm.value.size = val;
  initList();
};
// 处理当前页码变更
const handleCurrentChange = (val) => {
  queryForm.value.current = val;
  initList();
};
// 初始化二级类别列表
const initList = async () => {
  try {
    const res = await sendRequest(
      "get",
      "admin/secondaryType/" +
        queryForm.value.current +
        "/" +
        queryForm.value.size +
        "?name=" +
        queryForm.value.query.trim()
    );
    queryData.value = res.data.data;
    queryForm.value.totalCount = res.data.count;
  } catch (error) {
    console.log(error);
  }
};
// 初始化主分类列表
const initMainTypeList = async () => {
  try {
    const res = await sendRequest("get", "mainType/list");
    mainType.value = res.data;
  } catch (error) {
    console.log(error);
  }
};
// 处理搜索按钮点击事件
const handleSearchChange = () => {
  queryForm.value.current = 1;
  initList();
};
// 处理添加分类按钮点击事件
const handleAddChange = async () => {
  if (form.id != null) formRef.value.resetFields();
  form.id = null;
  initMainTypeList();
  dialogTableVisible.value = true;
};
// 处理保存按钮点击事件
const handleSave = async () => {
  try {
    const result = await sendRequest("POST", "admin/secondaryType", form);
    switch (result.code) {
      case 200:
        ElementUtil.ElNotification(null, "添加完成", "success");
        formRef.value.resetFields();
        initList();
        dialogTableVisible.value = false;
        break;
      default:
        ElementUtil.ElNotification(null, result.message, "warning");
        break;
    }
  } catch (error) {
    console.log(error);
  }
};
// 处理修改按钮点击事件
const handleReviseChange = async (value) => {
  console.log(value);
  initMainTypeList();
  form.id = value.id;
  form.name = value.name;
  form.remark = value.remark;
  form.mainTypeId = value.mainTypeId;
  dialogTableVisible.value = true;
};
// 处理修改按钮点击事件
const handleRevise = async () => {
  console.log(form);
  try {
    const result = await sendRequest(
      "PUT",
      "admin/secondaryType/" + form.id,
      form
    );
    switch (result.code) {
      case 200:
        ElementUtil.ElNotification(null, "修改完成", "success");
        initList();
        dialogTableVisible.value = false;
        break;
      default:
        ElementUtil.ElNotification(null, result.message, "warning");
        break;
    }
  } catch (error) {
    console.log(error);
  }
};
// 处理删除按钮点击事件
const handleDeleteChange = async (id) => {
  try {
    const result = await sendRequest("delete", "admin/secondaryType/" + id);
    switch (result.code) {
      case 200:
        ElementUtil.ElNotification(null, "删除完成", "success");
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
</script>

<style lang="scss" scoped>
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

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
