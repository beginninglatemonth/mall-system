// pages/goods/category/index.js

// 网络请求
const app = getApp();
const network = app.require();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: "",
    address: {},
    order: [],
    allChecked: false,// 全选按钮
    totalPrice: 0,//总价格
    totaNum: 0,//总数量
  },

  /**
   * 数据初始化
   */
  async initial() {

    // 从缓存获取数据
    const order = wx.getStorageSync('order') || []

    // 全选按钮
    let allChecked;
    let i = 0;
    //总价格
    let totalPrice = 0;
    //总数量
    let totaNum = 0;

    order.forEach(v => {
      if (v.checked) {
        i++;
        totalPrice += (v.price * v.number)
    
        totaNum += v.number
      }
    })
    totalPrice = totalPrice.toFixed(2)//两位小数
    allChecked = i === order.length ? true : false;

    this.setData({
      baseUrl: network.baseUrl,
      order,
      allChecked,
      totalPrice,
      totaNum,
    })

  },

  /**
   * 获取收货地址保存到缓存
   */
  handleChooseAddress() {
    wx.chooseAddress({
      success: res => {
        wx.setStorageSync('address', res)
      },
    })
  },

  // 增加减少数量
  handleItemNumEdit(e) {
    const id = e.currentTarget.dataset.id
    const operation = e.currentTarget.dataset.operation
    let order = wx.getStorageSync('order') || []
    let index = order.findIndex(v => v.id == id)
    const number = order[index].number
    if (0 === number + operation) {
      wx.showModal({
        title: '系统提示',
        content: '您是否要删除',
        complete: (res) => {
          if (res.cancel) {

          }

          if (res.confirm) {
            order.splice(index, 1)
            wx.setStorageSync('order', order)
            this.initial()
          }
        }
      })
    } else {
      order[index].number += operation
    }

    wx.setStorageSync('order', order)
    this.initial()
  },

  // 单选框事件
  handleIntemChange(e) {
    const id = e.currentTarget.dataset.id
    let order = wx.getStorageSync('order') || []
    let index = order.findIndex(v => v.id == id)
    order[index].checked = !order[index].checked
    wx.setStorageSync('order', order)
    this.initial()
  },

  // 全选
  handleIntemAllChange(e) {

    let order = wx.getStorageSync('order') || []

    // 方式一
    const value = "true" == e.detail.value ? true : false
    order.forEach(v => v.checked = value)
    // 方式二
    // let allChecked = !this.data.allChecked
    // order.forEach(v => v.checked = allChecked)

    wx.setStorageSync('order', order)
    this.initial()
  },

  /**
   * 结算
   */
  handlePay() {
    const address = this.data.address
    const totaNum = this.data.totaNum
    if (!address) {
      wx.showToast({
        title: '选择收货地址',
        icon: 'none'
      })
      return
    }
    if (totaNum === 0) {
      wx.showToast({
        title: '选择商品',
        icon: 'none'
      })
      return
    }
    wx.navigateTo({
      url: '/pages/order/confirmOrder/index',
    })


  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
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
    this.setData({
      address: wx.getStorageSync('address'),
    })
    this.initial()

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