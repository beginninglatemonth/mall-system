// pages/goods/category/index.js
// 网络请求
const app = getApp();
const network = app.require();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: '',
    mainTypeList: [],   //左侧菜单
    secondaryTypeList: [],    //右侧菜单
    currentIndex: 0,   //当前选中的菜单索引
    scroll: 0    //右侧滚动条位置
  },

  Cates: [],
  
  /**
 * 请求数据
 * @param {} index 
 */
  async initial(index) {
    // 
    try {
      // 在需要发送请求的函数中调用网络请求函数
      const response = await network.request(network.baseUrl + 'secondaryType', 'GET')
      // 处理成功响应数据
      this.Cates = response.data;
      const mainTypeList = this.Cates.map(v => v.name)
      const secondaryTypeList = this.Cates[index].secondaryTypeList
      this.setData({
        baseUrl: network.baseUrl,
        mainTypeList,
        secondaryTypeList,
        currentIndex: index,
        scroll: 0
      })
    } catch (error) {
      // 处理错误
      console.error(error);
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.initial(0);
  },

  // 左侧菜单点击
  handleMenuItemChange(e) {
    const index = e.currentTarget.dataset.index;
    const secondaryTypeList = this.Cates[index].secondaryTypeList
    this.setData({
      currentIndex: index,
      secondaryTypeList,
      scroll: 0
    })
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
    /**
     * 全局参数
     */
    const app = getApp();
    const index = app.globalData.index
    if (index != -1) {
      this.initial(index);
      app.globalData.index = -1;
    }

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

  }
})