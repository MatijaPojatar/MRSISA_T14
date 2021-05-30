import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersist from 'vuex-persist'

import apoteke from './modules/apoteke'
import korisnici from './modules/korisnici'
import lekovi from './modules/lekovi'
import narudzbenice from './modules/narudzbenice'
import ponude from './modules/ponude'
import zalbe from './modules/zalbe'
import termini from './modules/termini'
import akcijePromocije from './modules/akcijePromocije'
import erecepti from './modules/erecepti'


Vue.use(Vuex)

const vuexPersist = new VuexPersist({
  key: 'my-app',
  storage: window.localStorage,
})

const modules ={
  apoteke,
  lekovi,
  korisnici,
  narudzbenice,
  ponude,
  zalbe,
  termini,
  akcijePromocije,
  erecepti
}

export default new Vuex.Store({
  plugins: [vuexPersist.plugin],
  modules,
  actions: {
    resetStore({commit}) {
      for (let module in modules){
        commit(`${module}/resetState`)
      }
    }
  }

})