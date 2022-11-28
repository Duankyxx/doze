import { createStore } from 'vuex'

export default createStore({
  state: {
    //用户数据
    user: {
      id: 0,
      username: "",
      password: "",
      level: "",
      enable: "",
    },
    /**
     *    响应式:
     *    sm:w>1080
     */
    sm: false,
    refresh: false
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
