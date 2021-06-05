import Vue from "vue";

const initStanje = () => {
  return {
    apoteke: [],
    apoteka: {
      naziv: "",
      opis: "",
      adresa: "",
      grad: "",
      drzava: "",
      ocena: "",
    },
    apotekaLat: 0,
    apotekaLng: 0,
  }
}

const state = initStanje();

const getters = {
  getApoteke: state => state.apoteke,
  getApoteka: state => state.apoteka,
  getApotekaLat: state => state.apotekaLat,
  getApotekaLng: state => state.apotekaLng,
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
  async getApotekaAction({commit}, apotekaId){
    await Vue.axios.get(`http://localhost:8080/apoteke/getOne/${apotekaId}`).then(response => {
            console.log(response.data)
            commit("setApoteka", response.data)
            
            
        });
  },

  async getApotekaCoordinatesAction({commit}, addressObj){
    await Vue.$geocoder.send(addressObj, response => { 
      // self.apotekaLat = response.results[0].geometry.location.lat 
      // self.apotekaLng = response.results[0].geometry.location.lng
      commit("setApotekaLat",  response.results[0].geometry.location.lat)
      commit("setApotekaLng",  response.results[0].geometry.location.lng)
      });
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
  },

  setApoteka(state, apoteka){
    state.apoteka = apoteka;
  },
  setApotekaLat(state, apotekaLat){
state.apotekaLat = apotekaLat;
  },
  setApotekaLng(state, apotekaLng){
    state.apotekaLng = apotekaLng;
  },
}

export default { state, mutations, actions, getters, namespaced: true }