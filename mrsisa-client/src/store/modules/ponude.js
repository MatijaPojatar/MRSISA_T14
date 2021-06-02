import Vue from "vue";
const initStanje = () => {
  return {
    ponudeDobavljaca : [],
    izmenaMoguca: false,
    narudzbenicaMoguca: false,
  }
}

const state = initStanje();

const getters = {
  getPonudeDobavljaca: state => state.ponudeDobavljaca,
  getIzmenaMoguca: state => state.izmenaMoguca,
  getNarudzbenicaMoguca: state => state.narudzbenicaMoguca,
}
const actions = {
  async kreirajPonuduAction({commit}, dto){
    try{
      const response = await Vue.axios.post("/ponuda", dto);
      console.log(response.data);
      console.log(commit);
    }catch(error){
      console.log(error);
      alert("Uspešno kreirana ponuda"); //popraviti
    }
    
  },

  async getMoguceKreiratiPonuduAction({commit}, {idDob, idNar}){
    try{
      const response = await Vue.axios.get("/dobavljaci/"+ idDob +"/mogucaNarudzbenica/"+ idNar);
      console.log(response.data);
      commit("setNarudzbenicaMoguca", response.data);
    }catch(error){
      console.log(error);
      alert("Greska pri proveri da li dobavljac ima lek na stanju");
    }
  },

  async getIzmenaMogucaAction({commit}, id){
    try{
      const response = await Vue.axios.get("/ponuda/izmenaMoguca/" + id);
      console.log(response.data);
      commit("setIzmenaMoguca", response.data);
    }catch(error){
      console.log(error);
      alert("Greska pri proveravanja da li je izmena moguca");
    }
  },

  async izmeniPonuduAction({commit}, {id, dto}){
    try{
      const response = Vue.axios.put("/ponuda/"+ id, dto);
      console.log(response.data);
      console.log(commit);
    }catch(error){
      console.log(error);
      alert("Greska pri izmeni ponude");
    }
  },

  async getPonudeDobavljacaAction({commit}, id){
    try{
      const response = await Vue.axios.get("/ponuda/dobavljac/" + id);
      console.log(response.data);
      commit("setPonudeDobavljaca", response.data);
    }catch(error){
      console.log(error);
      alert("Greska pri dobavljanju ponuda");
    }
  }
}
const mutations = {
  // eslint-disable-next-line no-unused-vars
  resetState(state){
    state= initStanje();
  },
  setPonudeDobavljaca(state, lista){
    state.ponudeDobavljaca = lista;
  },
  setIzmenaMoguca(state, odg){
    state.izmenaMoguca = odg;
  },
  setNarudzbenicaMoguca(state, odg){
    state.narudzbenicaMoguca = odg;
  }

}

export default { state, mutations, actions, getters, namespaced: true}