import Vue from "vue";

const initStanje = () => {
  return {
    ereceptiPacijenta: [],
    currentErecept: null
  }
}

const state = initStanje();

const getters = {
  getEreceptiPacijenta: state => state.ereceptiPacijenta,
  getCurrentErecept: state => state.currentErecept,
}

const actions = {
  async getEreceptiPacijentaAction({commit}, id){
    const response = await Vue.axios.get("/erecepti/pacijent/"+id);
    console.log(response.data);
    commit("setEreceptiPacijenta", response.data);

  },

  async sendFileAction({commit} , {id, formData}){
    const response = await Vue.axios.post("/erecepti/upload/" + id,formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
    let erecept = response.data;
    console.log(erecept);
    commit("setCurrentErecept",erecept); 
  }
}

const mutations = {
  // eslint-disable-next-line no-unused-vars
  resetState(state){
    state = initStanje();
  },
  setEreceptiPacijenta(state, lista){
    state.ereceptiPacijenta = lista;
  },
  setCurrentErecept(state, recept){
    state.currentErecept = recept;
  }

}

export default { state, mutations, actions, getters, namespaced: true }