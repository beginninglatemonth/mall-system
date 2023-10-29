// pages/goods/category/index.js

// 网络请求
const app = getApp();
const network = app.require();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: "", // 网络请求基础地址
    address: {}, // 收货地址
    order: [], // 订单信息
    totalPrice: 0, //订单总价格
    totaNum: 0, //订单总数量
  },

  /**
   * 数据初始化
   */
  initial() {
    // 从本地缓存获取订单数据，如果不存在则使用空数组
    let order = wx.getStorageSync("order") || [];

    //初始化总价格和总数量
    let totalPrice = 0;
    let totaNum = 0;

    // 过滤出被选中的订单项
    order = order.filter((v) => v.checked);

    // 计算总价格和总数量
    order.forEach((v) => {
      if (v.checked) {
        totalPrice += v.price * v.number;
        totaNum += v.number;
      }
    });

    // 保留总价格小数点后两位
    totalPrice = totalPrice.toFixed(2);

    this.setData({
      order,
      totalPrice,
      totaNum,
    });
  },

  /**
   * 处理订单支付
   */
  handleOrderPay() {
    // 检查本地存储中是否存在用户令牌
    const token = wx.getStorageSync("token");
    if (!token) {
      // 如果不存在令牌，获取登录数据和用户信息
      this.getLoginAndgetUserProfile();
    }

    //创建订单
    this.createOrder();

    // 从本地缓存中删除已支付的订单项
    let order = wx.getStorageSync("order");
    order = order.filter((v) => !v.checked);
    wx.setStorageSync("order", order);

    // 显示支付成功提示
    wx.showToast({
      title: "支付成功",
      icon: "none",
    });

    // 导航到支付成功页面
    wx.navigateTo({
      url: "/pages/order/paySuccess/index",
    });
  },

  /**
   * 获取登录数据和用户信息
   */
  async getLoginAndgetUserProfile() {
    // 从本地缓存中获取用户信息，如果不存在则为空数组
    let userInfo = wx.getStorageSync("userInfo");
    try {
      if (!userInfo) {
        // 如果用户信息不存在，则调用网络模块的登录和获取用户信息方法
        userInfo = await network.getLoginAndgetUserProfile();
      }

      // 发送用户信息到服务器进行登录
      const response = await network.request(
        network.baseUrl + "user",
        "POST",
        userInfo
      );

      if (response.code === 200) {
        // 如果登录成功，从服务器响应中获取令牌并保存到本地缓存中
        const token = response.data.token;
        wx.setStorageSync("token", token);
      }
    } catch (error) {
      console.error(error);
    }
  },

  /**
   * 创建订单
   */
  async createOrder() {
    // 构建订单详情数据
    // const orderDatail = []
    // this.data.order.forEach(element => {
    //   orderDatail.push({
    //     goodsId: element.id,
    //     name: element.name,
    //     price: element.price,
    //     number: element.number,
    //     image: element.image
    //   })
    // });
    // 构建订单详情数据
    const orderDatail = this.data.order.map((element) => ({
      goodsId: element.id,
      name: element.name,
      price: element.price,
      number: element.number,
      image: element.image,
    }));
    // 获取订单总价格和收货地址信息
    const totalPrice = this.data.totalPrice;
    const address =
      this.data.address.provinceName +
      this.data.address.cityName +
      this.data.address.countyName +
      this.data.address.detailInfo;
    const consignee = this.data.address.userName;
    const telNumber = this.data.address.telNumber;
    // 构建订单参数
    const orderParam = {
      totalPrice,
      address,
      consignee,
      telNumber,
      orderDatail,
    };
    try {
      // 发送订单数据到服务器
      const response = await network.request(
        network.baseUrl + "user/orders",
        "POST",
        orderParam
      );

      if (response.code === 11000) {
        // 如果未登录，重新获取登录信息并创建订单
        await this.getLoginAndgetUserProfile();
        await this.createOrder();
      }
    } catch (error) {
      console.error(error);
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
   
   },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() { },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    // 页面加载时，设置基础地址和从缓存中获取收货地址
    this.setData({
      baseUrl: network.baseUrl,
      address: wx.getStorageSync("address"),
    });
    this.initial();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() { },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() { },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() { },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() { },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() { },
});
