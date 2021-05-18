import Vue from "vue";

const initStanje = () => {
  return {
    apoteke: []
  }
}

const state = initStanje();

const getters = {
  getApoteke: state => state.apoteke,
}

const actions = {
  async getApotekeAction({commit}){
    // const response = await Vue.axios.get("/apoteke");
    commit("setApoteke", [
      {
          "id": 1,
          "naziv": "Apoteka 1"
      },
      {
          "id": 2,
          "naziv": "Apoteka 2"
      },
      {
          "id": 3,
          "naziv": "Apoteka 3"
      }
  ]);
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
    state = initStanje();
  },

  setApoteke(state, lista){
    state.apoteke = lista;
  },

  addApoteka(state, apoteka){
    state.apoteke.push(apoteka);
  }
}

export default { state, mutations, actions, getters, namespaced: true }