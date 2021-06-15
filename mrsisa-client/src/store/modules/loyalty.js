// import Vue from "vue";

import Vue from "vue";

const initStanje = () => {
  return {
    kategorije: [],
    bodPregled: 0,
    bodSavetovanja: 0,
  }
}
const state = initStanje();

const getters = {
  getAllKat: state => state.kategorije,
  getBodPregled: state => state.bodPregled,
  getBodSavetovanja: state => state.bodSavetovanja
}
const actions = {
  async getBodPregledAction({commit}){
    let tip = "PREGLED"

    const response = await Vue.axios.get("/adminSistema/bodovanje/"+ tip+ "/"+"0");
    console.log(response.data);
    commit("setBodPregled", response.data.brojPoena);
  },

  async getBodSavetovanjaAction({commit}){
    let tip = "SAVETOVANJE"
    
    const response = await Vue.axios.get("/adminSistema/bodovanje/"+ tip+ "/"+"0");
    console.log(response.data);
    commit("setbodSavetovanja", response.data.brojPoena);
  },

  // eslint-disable-next-line no-unused-vars
  async addKatAction({commit}, dto){
    const response = await Vue.axios.post("/adminSistema/kategorija",dto);
    console.log(response.data);

  },

  async getAllKatAction({commit}){
    const response = await Vue.axios.get("/adminSistema/kategorija");
    console.log(response.data);
    commit("setKats", response.data);
  },

    // eslint-disable-next-line no-unused-vars
  async delKatAction({commit}, id){
    const response = await Vue.axios.delete("/adminSistema/kategorija/"+ id);
    console.log(response.data);
  },

  // eslint-disable-next-line no-unused-vars
  async addStavkaAction({commit}, dto){
    const response = await Vue.axios.post("/adminSistema/stavkaBodovanja", dto);
    console.log(response.data);

  }

}
const mutations = {
  // eslint-disable-next-line no-unused-vars
  resetState(state){
    state= initStanje();
  },

  setKats(state, lista){
    state.kategorije = lista;
  },

  setBodPregled(state, br){
    state.bodPregled = br;
  },

  setbodSavetovanja(state, br){
    state.bodSavetovanja = br;
  }
  
}

export default { state, mutations, actions, getters, namespaced: true}