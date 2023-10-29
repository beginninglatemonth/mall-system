// pages/goods/detail/index.js

// 网络请求
const app = getApp();
const network = app.require();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: "",
    productObj: {},
    activeIndex: 0
  },

  async initial(id) {
     /**
     * 获取商品详情
     */
    try {
      // 在需要发送请求的函数中调用网络请求函数
      const response = await network.request(network.baseUrl + 'goodsDetail/' + id, 'GET')
      // 处理成功响应数据
      this.setData({
        baseUrl: network.baseUrl,
        productObj: response.data
      })
    } catch (error) {
      // 处理错误
      console.error(error);
    }
  },

  // 商品介绍与规格参数切换
  handleItemTap(e) {
    const index = e.currentTarget.dataset.index;

    this.setData({
      activeIndex: index
    })
  },

  /**
   * 加入购物车按钮事件
   */
  handleOrderAdd() {

    this.setOrderAdd()

    wx.showToast({
      title: '加入成功',
      icon: 'success',
      mask: true,
    })
  },
  /**
   * 加入购物车
   */
  setOrderAdd() {
    let product = this.data.productObj;
    let order = wx.getStorageSync('order') || [];
    let index = order.findIndex(v => v.id === product.id);
    if (index === -1) {
      // 不存在
      product.number = 1;
      product.checked=true;
      order.push(product)
    } else {
      // 存在
      order[index].number++;
   
    }
    //把商品信息添加到缓存
    wx.setStorageSync('order', order);
  },

  handleBuy(){
    this.setOrderAdd()
    wx.switchTab({
      url: '/pages/order/shoppingCart/index',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.initial(options.id);
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

  }
})