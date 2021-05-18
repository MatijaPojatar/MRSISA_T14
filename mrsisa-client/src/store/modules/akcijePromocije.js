import Vue from "vue";

const initStanje = () => {
  return {
    akcijePromocije: [],
  }
}

const state = initStanje();

const getters = {
  getAkcijePromocije: state => state.akcijePromocije
}

const actions = {
  async getAkcijePromocijeAction({commit}, pacijentId){
    const response = await Vue.axios.get("akcijaPromocija/pacijent/"+ pacijentId);
    commit("setAkcijePromocije", response.data);
  },
}

const mutations = {
  setAkcijePromocije(state, lista){
    state.akcijePromocije = lista;
  },
}

export default {state, mutations, actions, getters, namespaced: true}