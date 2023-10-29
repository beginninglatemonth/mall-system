import { ElMessage, ElNotification, ElLoading } from "element-plus";

export const ElementUtil = {
  /**
   * 显示消息框
   * @param {string} message - 消息内容
   * @param {string} type - 消息类型 ('success' | 'info' | 'warning' | 'error')
   */
  showMessage(message, type = "success") {
    ElMessage({
      message,
      type,
    });
  },

  /**
   * 显示 Notification 通知
   * @param {string} title - 通知标题
   * @param {string} message - 通知消息
   * @param {'success' | 'error' | 'warning' | 'info'} type - 通知类型
   */
  ElNotification(title = "提示", message, type) {
    ElNotification({
      title,
      message,
      type,
    });
  },

  /**
   * 显示加载中
   * @param {string} text - 加载文本
   */
  showLoading(text = "加载中...") {
    return ElLoading.service({
      lock: true,
      text,
      spinner: "el-icon-loading",
      background: "rgba(0, 0, 0, 0.7)",
    });
  },

  /**
   * 关闭加载中
   * @param {object} loadingInstance - 加载实例
   */
  closeLoading(loadingInstance) {
    if (loadingInstance) {
      loadingInstance.close();
    }
  },
};
