import Vue from 'vue'
import App from './App.vue'
import router from './routes'
import vuetify from './plugins/vuetify';
import VueTextareaAutosize from 'vue-textarea-autosize';
import VueDarkMode from "@growthbunker/vuedarkmode";
import axios from 'axios';
import VueAxios from 'vue-axios'

Vue.use(VueDarkMode);

Vue.use(VueTextareaAutosize)

Vue.config.productionTip = false

Vue.use(VueAxios, axios)

Vue.axios.defaults.headers['Authorization'] = localStorage.getItem('token');
Vue.axios.defaults.baseURL = 'https://localhost:8080';

new Vue({
  router,
  vuetify,
  render: h => h(App),
  created () {
  },
}).$mount('#app')



