// pages/home/home.js

// 网络请求
const app = getApp();
const network = app.require();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: undefined,
    // 轮播图
    swiperList: [],
    mainTypeList1: [],
    mainTypeList2: [],
    goodsTopList: []

  },

  /**
   * 初始化
   */
  async initial() {
    // 
    try {
      // 在需要发送请求的函数中调用网络请求函数
      const response = await network.request(network.baseUrl + 'carousel', 'GET')
      // 处理成功响应数据

      this.setData({
        baseUrl: network.baseUrl,
        swiperList: response.data
      })
    } catch (error) {
      // 处理错误
      console.error(error);
    }
    // 
    try {
      const response = await network.request(network.baseUrl + 'mainType', 'GET')

      const mainTypeList1 = response.data.filter((item, index) => {
        return index < 5
      })
      const mainTypeList2 = response.data.filter((item, index) => {
        return index >= 5
      })
      this.setData({
        mainTypeList1,
        mainTypeList2
      })
    } catch (error) {
      console.error(error);
    }
    // 
    try {
      const response = await network.request(network.baseUrl + 'goodsTop', 'GET')

      this.setData({
        goodsTopList: response.data
      })
    } catch (error) {
      console.error(error);
    }

  },

  //点击跳转分类页 
  handleTypeJump(e) {
    const index = e.currentTarget.dataset.index;
    const app = getApp();
    app.globalData.index = index;
    wx.switchTab({
      url: '/pages/goods/category/index',
    })

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.initial();

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },

  /**
   * 搜索栏跳转
   */
  navToSearchPage() {
    wx.navigateTo({ url: '/pages/goods/search/index' });
  },
})