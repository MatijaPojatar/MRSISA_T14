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
	getVrste: state => () => state.vrste,
  getOblici: state => () => state.oblici
}

const actions = {
  getVrsteAction() {
    try{
      console.log("VRSTE ACTIONS")
      const { data: vrste } = await Vue.axios.get("/lekovi/vrste");
      commit("setVrste", vrste);
    }catch(error){
      alert("Greska vrste!")
    }
  },

  getObliciAction() {
    try{
      const { data : oblici } = await Vue.axios.get("/lekovi/oblici");
      commit("setOblici", oblici);
    }catch(error){
      alert("Greska oblici!");
    }
  }

},

const mutations = {
  resetStanje(state) {
    state = initStanje();
  },

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