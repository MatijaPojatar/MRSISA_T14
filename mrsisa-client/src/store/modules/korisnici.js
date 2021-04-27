import Vue from "vue";
import jwt_decode from "jwt-decode"

const initStanje = () => {
  return {
    role: "", //todo dodati role u token
    roleNum: -1,
    mail: "",
    korisnik: null,
  }
}

const state = initStanje();

const getters = {
  getMail: state => state.mail,
  getRole: state => state.role,
  getRoleNum: state => state.roleNum,
  getKorisnik: state => state.korisnik
}

const actions = {
  async logInAction({dispatch}, credentials) {
    try{
      const response = await Vue.axios.post("/auth/login", credentials); //printovati todo

      let accessToken = response.data.accessToken;
      console.log(accessToken, "SMO DOBILI ZA TOKEN")

      localStorage.setItem("token", accessToken);

      dispatch('updateAxiosAuth');
      dispatch('updateState', accessToken);
    }catch(error){
      alert("Greska log in- u");
    }
  },

  async updateState({ commit }, token){
    console.log("USLI VAMO")
    var jwt_data = jwt_decode(token);
    console.log(jwt_data)
    console.log(jwt_data.ime)
    
    commit("SET_MAIL", jwt_data.sub);
    commit("SET_KORISNIK", jwt_data);
    commit("SET_ROLE", jwt_data.roles[0].name);
    commit("SET_ROLE_NUM", jwt_data.roles[0].id);

  },

  updateAxiosAuth() {
    console.log("U AUTH ZA AXIOS")
    Vue.axios.defaults.headers['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
  }



}

const mutations = {
  SET_ROLE(state, role){ 
    state.role = role;
  },

  SET_KORISNIK(state, korisnik){
    state.korisnik = korisnik;
  },
  
  SET_MAIL(state, mail){
    state.mail = mail;
  },

  SET_ROLE_NUM(state, roleNum){
    state.roleNum = roleNum;
  }
}

export default { state, mutations, actions, getters, namespaced: true}