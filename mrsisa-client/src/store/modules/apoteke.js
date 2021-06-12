import Vue from "vue";

const initStanje = () => {
  return {
    apoteke: [],
    minimalApoteke: [],
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
    apotekaIdForAdminApoteke: "",
  }
}

const state = initStanje();

const getters = {
  getApoteke: state => state.apoteke,
  getAllApotekeMinimal: state => state.minimalApoteke,
  getApoteka: state => state.apoteka,
  getApotekaLat: state => state.apotekaLat,
  getApotekaLng: state => state.apotekaLng,
  getApotekaIdForAdminApoteke: state => state.apotekaIdForAdminApoteke,
}

const actions = {

  async getAllApotekeMinimalAction({commit}){
    const response = await Vue.axios.get("/apoteke/minimalne");
    console.log(response.data);
    commit("setMinimalne", response.data);
  },

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

  async getApotekaIdForAdminApotekeAction({commit}, userId){
    await Vue.axios.get(`http://localhost:8080/adminApoteke/${userId}`).then(response => {
            console.log(response.data)
            commit("setApotekaIdForAdminApoteke", response.data.apotekaId)
            
            
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

  setMinimalne(state, lista){
    state.minimalApoteke = lista;
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
  setApotekaIdForAdminApoteke(state, apotekaId){
    state.apotekaIdForAdminApoteke = apotekaId;
  }


}

export default { state, mutations, actions, getters, namespaced: true }