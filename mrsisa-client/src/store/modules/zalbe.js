import Vue from "vue";

const initStanje = () => {
  return {
    zalbeNaApoteke: []
  }
}

const state = initStanje();

const getters = {
  getZalbeNaApoteke: state => state.zalbeNaApoteke,
}

const actions = {
  async getZalbeNaApotekeAction({commit}){
    try{
      const { data : zalbe } = await Vue.axios.get("/zalbe/apoteka");
      commit("setZalbeNaApoteke", zalbe);
    } catch(error){
      alert("Greska pri dobavljanju zalbi za Apoteke");
    }
  },

  async addZalbaNaApotekuAction({commit}, zalbaDTO){
    try{
      const { data: zalba } = await Vue.axios.post("/zalbe/apoteka", zalbaDTO)
      commit("addZalbaNaApoteku", zalba);
    }catch(error){
      alert("Greska u dodavanju zalbe")
    }
  }
  
}

const mutations = {
  setZalbeNaApoteke(state, zalbe){
    state.zalbeNaApoteke = zalbe;
  },

  addZalbaNaApoteku(state, zalba){
    state.zalbeNaApoteke.push(zalba);
  }
}

export default { state, mutations, actions, getters, namespaced: true}