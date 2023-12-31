// components/Tabs/Tabs.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    tabs: Array,
    value: []
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    handleItemTap(e) {
      const index = e.currentTarget.dataset.index
      this.triggerEvent("tabsItemChange", { index })
    }
  }
})
