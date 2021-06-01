import Vue from "vue";

const initStanje = () => {
  return {
    ereceptiPacijenta: [],
    currentErecept: null,
    snabdeveneApoteke: [],
    // currentErecept: {
    //   id: 0,
    //   pacijentId: 0,
    //   datumIzdavanja: null,
    //   status: "ODBIJEN",
    //   lekoviERecepta: [{id : 0, lekId : 0, kolicina: 0, nazivLeka: ""}],
    //   pacijntIP: ""
    // },
    // snabdeveneApoteke: [{nazivApoteke: "", idApoteke: 0, cenaLekova: 0.0 }],
  }
}

const state = initStanje();

const getters = {
  getEreceptiPacijenta: state => state.ereceptiPacijenta,
  getCurrentErecept: state => state.currentErecept,
  getSnabdeveneApoteke: state => state.snabdeveneApoteke,
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
  },

  async getSnabdeveneApotekeAction({commit}, erecept){
    const response = await Vue.axios.post("/apoteke/snabdeveneApoteke", erecept);
    console.log(response.data);
    commit("setSnabdeveneApoteke", response.data);
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
  },
  setSnabdeveneApoteke(state, lista){
    state.snabdeveneApoteke = lista;
  }

}

export default { state, mutations, actions, getters, namespaced: true }