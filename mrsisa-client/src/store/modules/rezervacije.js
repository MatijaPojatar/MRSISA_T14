import Vue from "vue";

const initStanje = () => {
  return {
    rezervacije: [],
  }
}

const state = initStanje();

const getters = {
  getRezervacije: state => state.rezervacije
}

const actions = {
  async getRezervacijeAction({commit}, pacijentId){
    const response = await Vue.axios.get("rezervacija/pacijent/"+ pacijentId);
    commit("setRezervacije", response.data);
  },
}

const mutations = {
  // eslint-disable-next-line no-unused-vars
  resetState(state){
    state = initStanje();
  },
  
  setRezervacije(state, lista){
    state.rezervacije = lista;
  },
}

export default {state, mutations, actions, getters, namespaced: true}