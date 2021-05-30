import Vue from "vue";

const initStanje = () => {
  return {
    ereceptiPacijenta: [],
  }
}

const state = initStanje();

const getters = {
  getEreceptiPacijenta: state => state.ereceptiPacijenta,

}

const actions = {
  async getEreceptiPacijentaAction({commit}, id){
    const response = await Vue.axios.get("/erecepti/pacijent/"+id);
    console.log(response.data);
    commit("setEreceptiPacijenta", response.data);

  }
}

const mutations = {
  // eslint-disable-next-line no-unused-vars
  resetState(state){
    state = initStanje();
  },
  setEreceptiPacijenta(state, lista){
    state.ereceptiPacijenta = lista;
  }

}

export default { state, mutations, actions, getters, namespaced: true }