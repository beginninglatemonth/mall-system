<!-- eslint-disable vue/multi-word-component-names -->
<!-- 商品管理页面 -->
<template>
  <el-card>
    <!-- 搜索栏 -->
    <el-row class="header" gutter="20">
      <el-col :span="5">
        <!-- 商品名称输入框 -->
        <el-input placeholder="商品名称" v-model="queryForm.query" clearable />
      </el-col>
      <div>
        <!-- 搜索按钮 -->
        <el-button type="primary" :icon="Search" @click="handleSearchChange">
          搜索
        </el-button>
        <!-- 添加商品按钮 -->
        <el-button type="primary" @click="handleAddChange">
          添加商品
        </el-button>
      </div>
    </el-row>
    <!-- 商品列表 -->
    <el-table :data="queryData" style="width: 100%">
      <el-table-column prop="id" label="序号" width="100" fixed>
        <template #default="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="name" label="名称" width="200" />
      <el-table-column prop="price" label="价格" width="200" />
      <el-table-column prop="stock" label="库存" width="200" />
      <el-table-column prop="image" label="图片" width="200">
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
      <el-table-column prop="isTopRecommendations" label="热门推荐" width="200">
        <template v-slot="{ row }">
          <el-switch
            v-model="row.isTopRecommendations"
            inline-prompt
            active-text="推荐"
            inactive-text="不推荐"
            @change="handleIsTopRecommendationsChange(row)"
          />
        </template>
      </el-table-column>
      <el-table-column prop="hotDateTime" label="热门推荐日期" width="200" />
      <el-table-column
        prop="isCarouselImages"
        label="首页轮播商品图片推荐"
        width="200"
      >
        <template v-slot="{ row }">
          <el-switch
            v-model="row.isCarouselImages"
            inline-prompt
            active-text="推荐"
            inactive-text="不推荐"
            @change="handleIsCarouselImagesChange(row)"
          />
        </template>
      </el-table-column>
      <el-table-column prop="carouselImages" label="轮播图片" width="200">
        <template v-slot="scope">
          <img
            v-if="scope.row.carouselImages != null"
            :src="baseURL + scope.row.carouselImages"
            alt=""
            width="50"
            height="50"
          />
        </template>
      </el-table-column>
      <el-table-column prop="carouselOrdering" label="轮播排序" width="200" />
      <el-table-column prop="introductory" label="商品介绍" width="200" />
      <el-table-column prop="specifications" label="商品规格参数" width="200" />

      <el-table-column prop="description" label="描述" />
      <el-table-column prop="type" label="商品类型" width="200" />
      <el-table-column prop="reviseTime" label="修改时间" width="200" />
      <el-table-column prop="isDeleted" label="删除标识" width="100">
        <template #default="{ row }">
          {{ row.isDeleted === 0 ? "未删除" : "已删除" }}
        </template>
      </el-table-column>
      <!-- 操作列 -->
      <el-table-column prop="isDeleted" label="操作" width="500" fixed="right">
        <template #default="{ row }">
          <el-button type="success" @click="handleImageValue(row)"
            >更换图片</el-button
          >
          <el-button type="primary" @click="handleCarouselImageValue(row)"
            >幻灯设置</el-button
          >
          <el-button type="primary" @click="handleReviseChange(row)"
            >修改</el-button
          >
          <el-button
            type="danger"
            :icon="Delete"
            @click="handleDeleteChange(row.id)"
          />
          <el-button type="primary" @click="handleReviseDetailImage(row.id)"
            >轮播设置</el-button
          >
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
  <!-- 商品管理弹窗 -->
  <el-dialog v-model="dialogTableVisible" title="商品管理" width="50%" center>
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
      <el-form-item label="价格：" prop="price">
        <el-input v-model.trim="form.price" type="text" clearable />
      </el-form-item>
      <el-form-item label="库存：" prop="stock">
        <el-input v-model.trim="form.stock" type="text" clearable />
      </el-form-item>
      <el-form-item label="所属分类：">
        <el-select
          v-model="form.mainTypeId"
          @change="handleReviseMainTypeChange"
        >
          <el-option
            v-for="item in mainType"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
        <el-select v-model="form.type">
          <el-option
            v-for="item in secondaryType"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="描述：" prop="description">
        <el-input
          v-model.trim="form.description"
          type="textarea"
          clearable
          :rows="2"
        />
      </el-form-item>
      <el-form-item label="商品介绍：" prop="introductory"></el-form-item>
      <QuillEditor
        ref="myQuillEditorIntroductory"
        v-model:content="form.introductory"
        theme="snow"
        toolbar="full"
        contentType="text"
      />

      <el-form-item label="商品参数："></el-form-item>
      <QuillEditor
        ref="myQuillEditorSpecifications"
        v-model:content="form.specifications"
        theme="snow"
        toolbar="full"
        contentType="text"
      />
      <!-- contentType="html" -->
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
  <!-- 商品图片上传弹窗 -->
  <el-dialog v-model="dialogImageVisible" title="商品图片" width="20%" center>
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

  <!-- 商品首页轮播图片设置弹窗 -->
  <el-dialog
    v-model="dialogCarouselImageVisible"
    title="商品首页轮播图片设置"
    width="20%"
    center
  >
    <div style="text-align: center">
      <el-form
        ref="formRef"
        :model="form"
        label-width="120px"
        status-icon
        :rules="rules"
      >
        <el-form-item label="排列序号：" prop="carouselOrdering">
          <el-input-number v-model="form.carouselOrdering" :min="1" />
        </el-form-item>
      </el-form>
      <el-upload
        class="avatar-uploader"
        :show-file-list="false"
        :before-upload="beforeAvatarUpload"
        ref="uploadRef"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>
      <el-button type="primary" @click="handleReviseCarouselImage()">
        修改图片
      </el-button>
    </div>
  </el-dialog>

  <!-- 商品详细页轮播图片设置弹窗 -->
  <el-dialog
    v-model="dialogDetailImageVisible"
    title="商品详细页轮播图片设置"
    width="30%"
    center
  >
    <div style="text-align: center">
      <el-form
        ref="formRef"
        :model="form"
        label-width="100px"
        status-icon
        :rules="rules"
      >
        <el-form-item label="排列序号：" prop="sort">
          <el-input-number v-model="form.sort" :min="1" />
        </el-form-item>
      </el-form>
      <el-upload
        class="avatar-uploader"
        :show-file-list="false"
        :before-upload="beforeAvatarUpload"
        ref="uploadRef"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>
      <el-button type="primary" @click="handleAddDetailImage()">
        添加
      </el-button>
    </div>
    <el-table :data="DetailData" style="width: 100%">
      <el-table-column prop="id" label="序号" width="100">
        <template #default="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="image" label="图片">
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
      <el-table-column prop="sort" label="排列序号" width="100" />
      <el-table-column prop="isDeleted" label="删除标识" width="100">
        <template #default="{ row }">
          {{ row.isDeleted === 0 ? "未删除" : "已删除" }}
        </template>
      </el-table-column>

      <el-table-column prop="isDeleted" label="操作" width="100">
        <template #default="{ row }">
          <el-button
            type="danger"
            :icon="Delete"
            @click="handleDetailDeleteChange(row.id)"
          />
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
</template>

<script setup>
// 引入需要的库和模块
import { Search, Delete, Plus } from "@element-plus/icons-vue";
import { ref, reactive, onMounted } from "vue";
import { baseURL, sendRequest, PostImage } from "@/utils/request";
import { ElementUtil } from "@/utils/ElementUtil";
// 引入了 Quill 编辑器QuillEditor 组件，并引入相应的 CSS 样式
import { QuillEditor } from "@vueup/vue-quill";
import "@vueup/vue-quill/dist/vue-quill.snow.css";

// 页面数据的初始化
onMounted(() => {
  initList();
});
// 表单相关数据
const formRef = ref();
const form = reactive({});
// 查询相关数据
const queryData = ref();
const queryForm = ref({
  query: "",
  current: 1,
  size: 10,
  totalCount: 0,
});
// 对话框相关数据
const dialogTableVisible = ref(false);
const dialogImageVisible = ref(false);
const dialogCarouselImageVisible = ref(false);
const dialogDetailImageVisible = ref(false);
// 商品主分类和次分类数据
const mainType = ref();
const secondaryType = ref();
// 商品详情图片数据
const DetailData = ref();

// 图片相关数据
const imageUrl = ref(); // 图片预览 URL
const uploadRef = ref(); // 上传组件引用

// 表单数据校验规则
const rules = reactive({
  name: [{ required: true, message: "请输入名称", trigger: "blur" }],
  price: [{ required: true, message: "请输入价格", trigger: "blur" }],
  stock: [{ required: true, message: "请输入库存", trigger: "blur" }],
  description: [{ required: true, message: "请输入描述", trigger: "blur" }],
  type: [{ required: true, message: "请选择分类", trigger: "blur" }],
  carouselOrdering: [
    { required: true, message: "请输入排列序号", trigger: "blur" },
  ],
});
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

// 富文本框
const myQuillEditorIntroductory = ref(null);
const myQuillEditorSpecifications = ref(null);

// 初始化商品列表
const initList = async () => {
  try {
    const res = await sendRequest(
      "get",
      "admin/goods/" +
        queryForm.value.current +
        "/" +
        queryForm.value.size +
        "?name=" +
        queryForm.value.query
    );
    console.log(res.data.data);
    queryData.value = res.data.data;
    queryForm.value.totalCount = res.data.count;
  } catch (error) {
    console.log(error);
  }
};
// 初始化商品主分类列表
const initMainTypeList = async () => {
  try {
    const res = await sendRequest("get", "mainType/list");
    mainType.value = res.data;
  } catch (error) {
    console.log(error);
  }
};
// 初始化商品次分类列表
const initSecondaryType = async (value) => {
  try {
    const res = await sendRequest("get", "secondaryType/" + value);
    secondaryType.value = res.data;
  } catch (error) {
    console.log(error);
  }
};
// 初始化商品次分类列表（根据主分类）
const initSecondaryTypeList = async (value) => {
  try {
    const res = await sendRequest("get", "secondaryType/mainType/" + value);
    secondaryType.value = res.data;
  } catch (error) {
    console.log(error);
  }
};
// 重置表单数据
function newForm() {
  Object.keys(form).forEach((key) => {
    delete form[key];
  });
}
// 处理查询按钮点击事件
const handleSearchChange = () => {
  queryForm.value.current = 1;
  initList();
};

// 处理添加商品按钮点击事件
const handleAddChange = async () => {
  if (form.id != null) {
    newForm();
    myQuillEditorIntroductory.value.setHTML("");
    myQuillEditorSpecifications.value.setHTML("");
  }

  initMainTypeList();
  dialogTableVisible.value = true;
};
// 处理修改主分类事件
const handleReviseMainTypeChange = async (value) => {
  form.type = "";
  initSecondaryTypeList(value);
};
// 处理保存按钮点击事件
const handleSave = async () => {
  console.log(form);
  try {
    const result = await sendRequest("POST", "admin/goods", form);
    switch (result.code) {
      case 200:
        ElementUtil.ElNotification(null, "添加完成", "success");
        newForm();
        myQuillEditorIntroductory.value.setHTML("");
        myQuillEditorSpecifications.value.setHTML("");
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
// 处理是否置顶推荐切换事件
const handleIsTopRecommendationsChange = async (value) => {
  try {
    const result = await sendRequest(
      "PUT",
      "admin/goods/isTopRecommendations",
      value
    );
    switch (result.code) {
      case 200:
        ElementUtil.showMessage("修改完成", "success");
        initList();
        break;
      default:
        ElementUtil.showMessage(result.message, "warning");
        break;
    }
  } catch (error) {
    console.log(error);
  }
};
// 处理是否轮播图片切换事件
const handleIsCarouselImagesChange = async (value) => {
  try {
    const result = await sendRequest(
      "PUT",
      "admin/goods/isCarouselImages",
      value
    );
    switch (result.code) {
      case 200:
        ElementUtil.showMessage("修改完成", "success");
        initList();
        break;
      default:
        ElementUtil.showMessage(result.message, "warning");
        break;
    }
  } catch (error) {
    console.log(error);
  }
};
// 处理商品图片点击事件
const handleImageValue = async (value) => {
  form.id = value.id;
  imageUrl.value = value.image ? baseURL + value.image : null;
  dialogImageVisible.value = true;
};
// 上传图片前的校验
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
// 处理修改图片事件
const handleReviseImage = async () => {
  try {
    const result = await PostImage("admin/goods/image/" + form.id, {
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
// 处理轮播图片点击事件
const handleCarouselImageValue = async (value) => {
  form.id = value.id;
  form.carouselOrdering = value.carouselOrdering;

  imageUrl.value = value.carouselImages ? baseURL + value.carouselImages : null;
  dialogCarouselImageVisible.value = true;
};
// 处理修改轮播图片事件
const handleReviseCarouselImage = async () => {
  try {
    const result = await PostImage("admin/goods/carouselImage/" + form.id, {
      file: form.image,
      carouselOrdering: form.carouselOrdering,
    });
    switch (result.data.code) {
      case 200:
        ElementUtil.ElNotification(null, "修改完成", "success");
        dialogCarouselImageVisible.value = false;
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
// 处理修改商品事件
const handleReviseChange = async (value) => {
  newForm();
  initMainTypeList();
  await initSecondaryType(value.type);
  form.id = value.id;
  form.name = value.name;
  form.price = value.price;
  form.stock = value.stock;
  form.description = value.description;
  form.mainTypeId = secondaryType.value.mainTypeId;
  form.type = value.type;
  form.introductory = value.introductory;
  form.specifications = value.specifications;

  await initSecondaryTypeList(form.mainTypeId);
  dialogTableVisible.value = true;
};
// 处理商品修改保存事件
const handleRevise = async () => {
  try {
    const result = await sendRequest("PUT", "admin/goods", form);
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
// 处理删除商品事件
const handleDeleteChange = async (id) => {
  try {
    const result = await sendRequest("delete", "admin/goods/" + id);
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
// 初始化商品详情图片列表
const initDetailList = async () => {
  try {
    const res = await sendRequest(
      "get",
      "admin/goodsDetail/goodsId/" + form.goodsId
    );
    DetailData.value = res.data;
  } catch (error) {
    console.log(error);
  }
};
// 处理修改商品详情图片事件
const handleReviseDetailImage = async (value) => {
  newForm();
  form.goodsId = value;
  imageUrl.value = null;
  initDetailList();
  dialogDetailImageVisible.value = true;
};
// 处理添加商品详情图片事件
const handleAddDetailImage = async () => {
  try {
    const result = await PostImage("admin/goodsDetail", {
      file: form.image,
      sort: form.sort,
      goodsId: form.goodsId,
    });
    switch (result.data.code) {
      case 200:
        ElementUtil.ElNotification(null, "添加完成", "success");
        newForm();
        imageUrl.value = null;
        initDetailList();
        break;
      default:
        ElementUtil.ElNotification(null, result.data.message, "warning");
        break;
    }
  } catch (error) {
    console.log(error);
  }
};
// 处理删除商品详情图片事件
const handleDetailDeleteChange = async (id) => {
  try {
    const result = await sendRequest("delete", "admin/goodsDetail/" + id);
    switch (result.code) {
      case 200:
        ElementUtil.ElNotification(null, "删除完成", "success");
        initDetailList();
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
