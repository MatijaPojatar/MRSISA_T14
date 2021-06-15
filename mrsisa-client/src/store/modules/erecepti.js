import Vue from "vue";

const initStanje = () => {
  return {
    ereceptiPacijenta: [],
    // currentErecept: null,
    // snabdeveneApoteke: [],
    currentErecept: {
      id: 0,
      pacijentId: 0,
      datumIzdavanja: null,
      status: "ODBIJEN",
      lekoviERecepta: [{id : 0, lekId : 0, kolicina: 0, nazivLeka: ""}],
      pacijntIP: ""
    },
    snabdeveneApoteke: [{nazivApoteke: "", idApoteke: 0, cenaLekova: 0.0 }],
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

  async getSnabdeveneApotekeAction({commit}, {erecept, idPac}){
    const response = await Vue.axios.post("/apoteke/snabdeveneApoteke/"+ idPac, erecept);
    console.log(response.data);
    commit("setSnabdeveneApoteke", response.data);
  },

  async kupiLekoveAction({commit}, {id, erecept}){
    await Vue.axios.put("/apoteke/kupiLekove/" + id, erecept);
    commit("clearErecept");
    commit("clearSnabdeveneApoteke");

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
  clearErecept(state){
    state.currentErecept = {
      id: 0,
      pacijentId: 0,
      datumIzdavanja: null,
      status: "NOV",
      lekoviERecepta: [{id : 0, lekId : 0, kolicina: 0, nazivLeka: ""}],
      pacijntIP: ""
    };
  },
  setSnabdeveneApoteke(state, lista){
    state.snabdeveneApoteke = lista;
  },
  clearSnabdeveneApoteke(state){
    state.snabdeveneApoteke = [{nazivApoteke: "", idApoteke: 0, cenaLekova: 0.0 }];
  }

}

export default { state, mutations, actions, getters, namespaced: true }