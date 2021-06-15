// import Vue from "vue";

import Vue from "vue";

const initStanje = () => {
  return {
    kategorije: []
  }
}
const state = initStanje();

const getters = {
  getAllKat: state => state.kategorije,
}
const actions = {
  // eslint-disable-next-line no-unused-vars
  async addKatAction({commit}, dto){
    const response = await Vue.axios.post("/adminSistema/kategorija",dto);
    console.log(response.data);

  },

  async getAllKatAction({commit}){
    const response = await Vue.axios.get("/adminSistema/kategorija");
    console.log(response.data);
    commit("setKats", response.data);
  }
  
}
const mutations = {
  // eslint-disable-next-line no-unused-vars
  resetState(state){
    state= initStanje();
  },

  setKats(state, lista){
    state.kategorije = lista;
  }
  
}

export default { state, mutations, actions, getters, namespaced: true}