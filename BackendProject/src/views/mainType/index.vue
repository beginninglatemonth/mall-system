<!-- eslint-disable vue/multi-word-component-names -->
<!-- 商品一类 -->
<template>
  <el-card>
    <el-row class="header" gutter="20">
      <el-col :span="5">
        <!-- 搜索栏 -->
        <el-input
          placeholder="一级类别名称"
          v-model="queryForm.query"
          clearable
        />
      </el-col>
      <el-button type="primary" :icon="Search" @click="handleSearchChange">
        搜索
      </el-button>
      <el-button type="primary" @click="handleAddChange"> 添加分类 </el-button>
    </el-row>
    <!-- 商品一级类别列表 -->
    <el-table :data="queryData" style="width: 100%">
      <el-table-column prop="id" label="序号" width="100" fixed>
        <template #default="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="name" label="名称" width="300" />
      <el-table-column prop="image" label="图片" width="300">
        <template v-slot="scope">
          <img
            v-if="scope.row.image != null"
            :src="baseURL + scope.row.image"
            alt=""
            width="50"
            height="50"
          />
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="描述" />
      <el-table-column prop="isDeleted" label="删除标识" width="100">
        <template #default="{ row }">
          {{ row.isDeleted === 0 ? "未删除" : "已删除" }}
        </template>
      </el-table-column>
      <el-table-column prop="isDeleted" label="操作" width="350" fixed="right">
        <template #default="{ row }">
          <el-button type="success" @click="handleImageValue(row)"
            >更换图片</el-button
          >
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
  <!-- 添加/修改商品一级类别对话框 -->
  <el-dialog v-model="dialogTableVisible" title="一级类别" width="20%" center>
    <el-form
      ref="formRef"
      :model="form"
      label-width="70"
      status-icon
      :rules="rules"
    >
      <el-form-item label="名称：" prop="name">
        <el-input v-model.trim="form.name" type="text" clearable />
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
  <!-- 更换商品一级类别图片对话框 -->
  <el-dialog v-model="dialogImageVisible" title="一级类别" width="20%" center>
    <div style="text-align: center">
      <el-upload
        class="avatar-uploader"
        :show-file-list="false"
        :before-upload="beforeAvatarUpload"
        ref="uploadRef"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>
      <el-button type="primary" @click="handleReviseImage()">
        修改图片
      </el-button>
    </div>
  </el-dialog>
</template>

<script setup>
import { Search, Delete, Plus } from "@element-plus/icons-vue";
import { ref, reactive, onMounted } from "vue";
import { baseURL, sendRequest, PostImage } from "@/utils/request";
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
const dialogImageVisible = ref(false);
const formRef = ref();
// 表单
const form = reactive({
  name: "",
  remark: "",
});
// 图片相关
const imageUrl = ref();
const uploadRef = ref();
// 表单数据校验
const rules = reactive({
  name: [{ required: true, message: "请输入名称", trigger: "blur" }],
  remark: [{ required: true, message: "请输入描述", trigger: "blur" }],
});

// 初始化商品一级类别列表
const initList = async () => {
  try {
    const res = await sendRequest(
      "get",
      "admin/mainType/" +
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
// 处理搜索按钮点击
const handleSearchChange = () => {
  queryForm.value.current = 1;
  initList();
};
// 处理添加按钮点击
const handleAddChange = async () => {
  if (form.id != null) formRef.value.resetFields();
  form.id = null;
  dialogTableVisible.value = true;
};
// 处理保存按钮点击
const handleSave = async () => {
  try {
    const result = await sendRequest("POST", "admin/mainType", form);
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
// 处理更换图片按钮点击
const handleImageValue = async (value) => {
  form.id = value.id;
  imageUrl.value = value.image ? baseURL + value.image : null;
  dialogImageVisible.value = true;
};
// 图片上传前的校验
const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== "image/jpeg" && rawFile.type !== "image/png") {
    ElementUtil.showMessage("类型错误", "warning");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElementUtil.showMessage("文件大小超过2MB", "warning");
    return false;
  }
  imageUrl.value = URL.createObjectURL(rawFile);
  form.image = rawFile;
  return false;
};
// 处理修改图片按钮点击
const handleReviseImage = async () => {
  try {
    const result = await PostImage("admin/mainType/image/" + form.id, {
      file: form.image,
    });
    switch (result.data.code) {
      case 200:
        ElementUtil.ElNotification(null, "修改完成", "success");
        dialogImageVisible.value = false;
        initList();
        break;
      default:
        ElementUtil.ElNotification(null, result.data.message, "warning");
        break;
    }
  } catch (error) {
    console.log(error);
  }
};
// 处理修改按钮点击
const handleReviseChange = async (value) => {
  form.id = value.id;
  dialogTableVisible.value = true;
  form.name = value.name;
  form.remark = value.remark;
};
// 处理修改按钮点击
const handleRevise = async () => {
  try {
    const result = await sendRequest("PUT", "admin/mainType/" + form.id, form);
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
// 处理删除按钮点击
const handleDeleteChange = async (id) => {
  try {
    const result = await sendRequest("delete", "admin/mainType/" + id);
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
