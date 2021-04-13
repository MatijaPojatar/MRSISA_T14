import Vue from 'vue'
import Vuex from 'vuex'

import apoteke from './modules/apoteke'
import korisnici from './modules/korisnici'
import lekovi from './modules/lekovi'
import narudzbenice from './modules/narudzbenice'
import ocene from './modules/ocene'
import ponude from './modules/ponude'
import zalbe from './modules/zalbe'



Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    apoteke,
    lekovi,
    korisnici,
    narudzbenice,
    ocene,
    ponude,
    zalbe
  }

})