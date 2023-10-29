// app.js
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
  },

  /**
   * 引入伪绝对路径
   * 网络请求封装文件
   */
  require: function () {
    return require('utils/request')
  },

  /**
   * 全局参数
   */
  globalData: {
    userInfo: null,
    index: -1
  }
})
