import { createStore } from 'vuex'

export default createStore({
  state: {
    seatNumber: "",
    uid: "",
    data: [{
      cookLabel: "",
      cookName: "",
      cookPicture: "",
      id: 0,
      isGood: "",
      isSell: "",
      price: 0,
      purchaseQuantity: 0,
      uid: 0,
      buy: 0,
    }],
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
