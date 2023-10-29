// pages/goods/search/search.js

// 网络请求
const app = getApp();
const network = app.require();


Page({

  /**
   * 页面的初始数据
   */
  data: {
    inputValue: "", //输入框
    isFocus: false, //按钮显示
    goodsList: [], //商品
  },

  /**
   * 搜索
   * @param {*} name 
   */
  async search(name) {
    const response = await network.request(network.baseUrl + 'goods/search?name=' + name, 'GET')
    const goodsList = response.data
    this.setData({
      goodsList
    })
  },

  TimeoutId: -1,
  /**
   * 搜索框
   * @param {*} e 
   */
  handleInput(e) {
    const name = e.detail.value.trim()
    if (!name) {
      clearInterval(this.TimeoutId)
      this.setData({
        isFocus: false,
        goodsList: []
      })
      return
    }

    this.setData({
      isFocus: true
    })
    clearInterval(this.TimeoutId)
    this.TimeoutId = setTimeout(() => {
      this.search(name)
    }, 1000)
  },
  /**
   * 清除按钮
   */
  handlePurge() {
    this.setData({
      inputValue: "",
      isFocus: false,
      goodsList: [],
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