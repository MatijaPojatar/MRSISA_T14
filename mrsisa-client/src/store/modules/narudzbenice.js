import Vue from "vue";

const initStanje = () => {
  return {
    moguceNarudzbenice : [],
  }
}
const state = initStanje();

const getters = {
  getMoguceNarudzbenice: state => state.moguceNarudzbenice,
}
const actions = {
  async getMoguceNarudzbeniceAction({commit}, idDobavljaca){
    console.log(idDobavljaca);

    const response = await Vue.axios.get("/narudzbenice/aktivne/" + idDobavljaca);
    console.log(response.data);
    commit("setMoguceNarudzbenice", response.data);

        // let n = {
    //   id: 1,
    //   nazivApoteke: "bla",
    //   rok: new Date(),
    //   lekovi: [{naziv: "Naziv leka", lekId: 3, kolicina: 3.0}]
    // }
    // commit ("setMoguceNarudzbenice", [n]);
  }
}
const mutations = {
  // eslint-disable-next-line no-unused-vars
  resetState(state){
    state= initStanje();
  },
  setMoguceNarudzbenice(state, lista){
    state.moguceNarudzbenice = lista;
  }
}

export default { state, mutations, actions, getters, namespaced: true}