// 后端域名
const baseUrl = "http://localhost:8080/"
// const baseUrl = "http://192.168.1.6:8080/"

/**
 * 请求后端数据
 * @param {请求的URL地址} url 
 * @param {请求的方法，如GET或POST} method 
 * @param {请求发送的数据} data 
 */
function request(url, method, data) {
  if (method == "GET") {
    /**模拟网络延迟加载 */
    var start = new Date().getTime();
    wx.showLoading({
      title: '加载中...',
      mask: true
    })
    while (true) {
      if (new Date().getTime() - start > 1 * 1000) break;
    }
  }


  let header = {}
  if (url.includes("/user/")) {
    header["token"] = wx.getStorageSync('token')  // 携带token
  }
  header["Content-Type"] = 'application/json'// 根据需求设置合适的请求头
  return new Promise((resolve, reject) => {
    wx.request({
      url: url,
      method: method,
      data: data,
      header,
      success: res => {
        resolve(res.data);
      },
      fail: err => {
        reject(err);
      },
      complete: () => {
        wx.hideLoading()
      }
    });
  });
}

/**
 * getLogin登录
 */
function getLogin() {
  return new Promise((resolve, reject) => {
    wx.login({
      timeout: 5000,
      success: res => {
        resolve(res);
      },
      fail: err => {
        reject(err);
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  })
}
/**
 * getUserProfile登录
 */
function getUserProfile() {
  return new Promise((resolve, reject) => {
    wx.getUserProfile({
      lang: 'zh_CN',
      desc: '用户登录',
      success: (res) => {
        resolve(res);
      },
      // 失败回调
      fail: () => {
        reject(err);
      }
    })
  })
}

/**
 * 获得登录数据
 */
function getLoginAndgetUserProfile() {
  return new Promise((resolve, reject) => {
    Promise.all([getLogin(), getUserProfile()])
      .then((res) => {
        let userInfo = res[1].userInfo
        userInfo.code = res[0].code
        wx.setStorageSync('userInfo', userInfo)
        // resolve(res)
        resolve(userInfo)
      })
  })
}


module.exports = {
  request: request,
  baseUrl,
  getLoginAndgetUserProfile
};