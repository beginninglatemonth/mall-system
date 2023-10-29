// pages/order/paySuccess/index.js

const app = getApp();
const network = app.require();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    tabs: [
      {
        id: 0,
        value: "全部订单",
        isActive: true,
      },
      {
        id: 1,
        value: "待付款",
        isActive: false,
      },
      {
        id: 2,
        value: "待收货",
        isActive: false,
      },
      {
        id: 3,
        value: "退款/退货",
        isActive: false,
      },
    ],
    order: [],
  },
  type: 0, //标题
  page: {
    current: 1, //当前页码
    size: 10, //当前页数量
    totalPages: 0, //总页码
    count: 0, //总数量
  },

  /**
   * 获得订单
   * @param {订单类型} status
   */
  async getOrders(status) {
    const response = await network.request(
      network.baseUrl +
        "user/orders/" +
        status +
        "/" +
        this.page.current +
        "/" +
        this.page.size,
      "GET"
    );
    const order = this.data.order.concat(response.data.data); //拼接对象
    this.page.current = response.data.current;
    this.page.totalPages = response.data.totalPages;
    this.page.count = response.data.count;
    this.setData({
      order,
    });
  },

  /**
   * 切换标题选项
   * @param {*} e
   */
  handleTabsItemChange(e) {
    this.type = e.detail.index;

    this.toggleTitles(this.type);
  },

  /**
   * 切换标题
   * @param {选项} type
   */
  toggleTitles(type) {
    const tabs = this.data.tabs;
    tabs.forEach((v, i) =>
      i == type ? (v.isActive = true) : (v.isActive = false)
    );
    this.setData({
      tabs,
    });
    this.page.current = 1;
    this.data.order = [];
    this.getOrders(this.type);
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if (!options.type) {
      this.toggleTitles(this.type);
    } else {
      this.type = options.type;
      this.toggleTitles(options.type);
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
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
  onReachBottom() {
    if (this.page.current >= this.page.totalPages) {
      wx.showToast({
        title: "没有下一页",
      });
    } else {
      this.page.current++;
      this.getOrders(this.type);
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {},
});
