import Vue from 'vue'
import App from './App.vue'
import router from './routes'
import vuetify from './plugins/vuetify';
import VueTextareaAutosize from 'vue-textarea-autosize';
import VueDarkMode from "@growthbunker/vuedarkmode";

Vue.use(VueDarkMode);

Vue.use(VueTextareaAutosize)

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  render: h => h(App),
  created () {
  },
}).$mount('#app')



