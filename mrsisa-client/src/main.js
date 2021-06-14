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
import Geocoder from "@pderas/vue2-geocoder";

Vue.use(Geocoder, {
    defaultCountryCode: null, // e.g. 'CA'
    defaultLanguage:    null, // e.g. 'en'
    defaultMode:        'address', // or 'lat-lng'
    googleMapsApiKey:   'AIzaSyABqBTYCgHhadT5MpeM61rsQJTe8LMANEE'
});

Vue.config.productionTip = false
Vue.use(VueGeolocation)
Vue.use(require('vue-moment'));

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
// Vue.axios.defaults.baseURL = 'http://localhost:8080';
Vue.axios.defaults.baseURL = 'https://mrsisa-t14-deployment.herokuapp.com/';

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App),
  created () {
  },
}).$mount('#app')



