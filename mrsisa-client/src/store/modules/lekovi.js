import Vue from "vue";

const initStanje = () => {
  return {
    lekovi: [],
    vrste: [],
    oblici: []
  }
}

const state = initStanje();

const getters = {
	getVrste: state => state.vrste,
  getOblici: state => state.oblici
}

const actions = {
  async getVrsteAction({ commit }) {
    try{
      console.log("VRSTE ACTIONS")
      const { data: vrste } = await Vue.axios.get("/lekovi/vrste");
      commit("setVrste", vrste);
    }catch(error){
      alert("Greska vrste!")
    }
  },

  async getObliciAction({ commit }) {
    try{
      console.log("OBLICI ACTIONS")
      const { data : oblici } = await Vue.axios.get("/lekovi/oblici");
      console.log(oblici, "OBLICI");
      commit("setOblici", oblici);
    }catch(error){
      alert("Greska oblici!");
    }
  },

  async addLekAction({ commit }, lekInfo) {
    try{
      const { data : lekDTO} = await Vue.axios.post("/lekovi", lekInfo);
      commit("addLek", lekDTO)
    }catch(error){
      alert("Greska pri dodavanju leka")
    }
  },

}

const mutations = {
  setVrste(state, vrste) {
    console.log(vrste, "VRSTE")
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