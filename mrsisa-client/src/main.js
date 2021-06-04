import Vue from 'vue'
import App from './App.vue'
import router from './routes'
import vuetify from './plugins/vuetify';
import VueTextareaAutosize from 'vue-textarea-autosize';
import VueDarkMode from "@growthbunker/vuedarkmode";
import axios from 'axios';
import VueAxios from 'vue-axios'
import store from './store/store';
import VueGeolocation from 'vue-browser-geolocation'

Vue.config.productionTip = false
Vue.use(VueGeolocation)

import * as VueGoogleMaps from 'vue2-google-maps'
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyABqBTYCgHhadT5MpeM61rsQJTe8LMANEE'
  }
})

Vue.use(VueDarkMode);

Vue.use(VueTextareaAutosize)

Vue.config.productionTip = false

Vue.use(VueAxios, axios)

Vue.axios.defaults.headers['Authorization'] = "Bearer " + localStorage.getItem('token');
// this.axios.defaults.headers['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
Vue.axios.defaults.baseURL = 'http://localhost:8080';

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App),
  created () {
  },
}).$mount('#app')



