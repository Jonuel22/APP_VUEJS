import Vue from 'nativescript-vue';
import Vuex from 'vuex';
import { AsyncStorage } from '@nativescript-community/async-storage';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    user: null,
    images: [],
    offlineData: [],
    isLoggedIn: false,
  },
  mutations: {
    login(state, user) {
      state.user = user;
      state.isLoggedIn = true;
    },
    logout(state) {
      state.user = null;
      state.isLoggedIn = false;
    },
    setImages(state, images) {
      state.images = images;
    },
    setOfflineData(state, data) {
      state.offlineData = data;
    },
  },
  actions: {
    login({ commit }, user) {
      // Lógica de autenticación
      commit('login', user);
    },
    logout({ commit }) {
      commit('logout');
    },
    saveImage({ commit }, image) {
      commit('setImages', image);
    },

methods: {
  async saveOffline(data) {
    await AsyncStorage.setItem('offlineData', JSON.stringify(data));
  },
  async syncData() {
    const data = await AsyncStorage.getItem('offlineData');
    if (data) {
      await axios.post('https://tu-api-rest-quarkus.com/sync', JSON.parse(data));
      await AsyncStorage.removeItem('offlineData');
    }
  }
}

  }
});

export default store;
