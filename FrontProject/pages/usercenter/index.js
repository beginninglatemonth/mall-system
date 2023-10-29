// pages/usercenter/index.js

// 网络请求
const app = getApp();
const network = app.require();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    userInfo: [],
  },

  /**
   * 数据初始化
   */
  async initial() {
    const userInfo = wx.getStorageSync("userInfo");

    if (!userInfo) {
      wx.showModal({
        title: "友情提示",
        content: "微信授权登录后进入个人中心",
        complete: (res) => {
          if (res.cancel) {
          }

          if (res.confirm) {
            this.getLoginAndgetUserProfile();
          }
        },
      });
    }
    this.setData({
      userInfo,
    });
  },
  /**
   * 获取登录数据和用户信息
   */
  async getLoginAndgetUserProfile() {
    let userInfo = wx.getStorageSync("userInfo");
    if (!userInfo) {
      userInfo = await network.getLoginAndgetUserProfile();
    }
    this.setData({
      userInfo,
    });

    try {
      const response = await network.request(
        network.baseUrl + "user",
        "POST",
        userInfo
      );
      if (response.code === 200) {
        const token = response.data.token;
        wx.setStorageSync("token", token);
      }
    } catch (error) {
      console.error(error);
    }
  },

  /**
   * 收货地址
   */
  handleEditAddress() {
    // 获取收货地址保存到缓存
    wx.chooseAddress({
      success: (res) => {
        wx.setStorageSync("address", res);
      },
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {},

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    this.initial();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {},

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {},

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {},

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {},

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {},
});
