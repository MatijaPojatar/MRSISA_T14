import Vue from "vue";

const initStanje = () => {
  return {
    lekovi: [],
    vrste: [],
    oblici: [],
    rezimiIzdavanja: [],
    currentLek: null
  }
}

const state = initStanje();

const getters = {
  getLekovi: state => state.lekovi,
	getVrste: state => state.vrste,
  getOblici: state => state.oblici,
  getRezimiIzdavanja: state => state.rezimiIzdavanja,
  getCurrentLek: state => state.currentLek
}

const actions = {

  async getLekoviAction({ commit }) {
    try{
      const { data : lekovi} = await Vue.axios.get("/lekovi");
      commit("setLekovi", lekovi);
    } catch(error){
      alert("Greska u pribavljanju svih lekova")
    }
  },

  async getRezimiIzdavanjaAction({ commit }) {
    try {
      const { data : rezimi } = await Vue.axios.get("/lekovi/rezimi");
      commit("setRezimi", rezimi);
    } catch (error) {
      alert("Greska u rezzz izzz")
    }
  },


  async getVrsteAction({ commit }) {
    try{
      const { data: vrste } = await Vue.axios.get("/lekovi/vrste");
      commit("setVrste", vrste);
    }catch(error){
      alert("Greska vrste!")
    }
  },

  async getObliciAction({ commit }) {
    try{
      const { data : oblici } = await Vue.axios.get("/lekovi/oblici");
      commit("setOblici", oblici);
    }catch(error){
      alert("Greska oblici!");
    }
  },

  async addLekAction({ commit }, lekInfo) {
    try{
      const { data : lekDTO} = await Vue.axios.post("/lekovi", lekInfo);
      commit("addLek", lekDTO)
      commit("setCurrentLek", lekDTO)
      return;
    }catch(error){
      alert("Greska pri dodavanju leka")
      return;
    }
  },

  async removeLekAction({ commit }, id){
    try{
      await Vue.axios.delete("/lekovi/"+ id, id);
      commit("deleteLek", id);
    } catch(error){
      alert("Greska pri brisanju leka")
      return;
    }
  },

  async addZamenskeLekoveAction( {commit}, {id, zamenski}){
    try{
      console.log(commit)
      await Vue.axios.put("/lekovi/zamenski/"+ id, zamenski);
    }catch(error){
      alert("Greska pri dodavanju zamenskih lekova")
      return;
    }
  }

}

const mutations = {
  
  // eslint-disable-next-line no-unused-vars
  resetState(state){
    state= initStanje();
  },

  setRezimi(state, rezimi) {
    state.rezimiIzdavanja = rezimi;
  },

  setCurrentLek(state, lek){
    state.currentLek = lek;
  },

  setVrste(state, vrste) {
    state.vrste = vrste;
  },

  setOblici(state, oblici) {
    state.oblici = oblici;
  },

  setLekovi(state, lekovi){
    state.lekovi = lekovi;
  },

  addLek(state, lek){
    state.lekovi.push(lek);
  },

  updateLek(state, izmenjen){
    const index = state.lekovi.findIndex((lek) => lek.id === izmenjen.id);
    Object.assign(state.lekovi[index], izmenjen);
  },

  deleteLek(state, id) {
    const index = state.lekovi.findIndex((l) => l.id === id);
    state.lekovi.splice(index,1);
  }
}

export default { state, mutations, actions, getters, namespaced: true}