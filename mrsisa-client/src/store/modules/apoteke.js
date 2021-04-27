import Vue from "vue";

const initStanje = () => {
  return {
    apoteke: []
  }
}

const state = initStanje();

const getters = {
}

const actions = {

  async getApotekeAction({commit}) {
    try{
      const {data : apoteke} = await Vue.axios.get("/apoteke");
      commit("setApoteke", apoteke);
    }catch(error){
      alert("Greska u get apoteke");
    }
  },

  async addApotekaAction({commit}, apotekaInfo){
    try{
      const { data: apotekaDTO} = await Vue.axios.post("/apoteke", apotekaInfo);
      commit("addApoteka", apotekaDTO)
    }catch(error){
      alert("Greska pri dodavanju apoteke")
    }
  }

}

const mutations = {
  // eslint-disable-next-line no-unused-vars
  resetState(state){
    state= initStanje();
  },

  setApoteke(state, apoteke){
    state.apoteke = apoteke;
  },

  addApoteka(state, apoteka){
    state.apoteke.push(apoteka);
  }
}

export default { state, mutations, actions, getters, namespaced: true}