import Vue from "vue";
import jwt_decode from "jwt-decode"

const initStanje = () => {
  return {
    role: "", //todo dodati role u token
    mail: "",

    loginState : {
      mail : "",
      password : ""
    }

  }
}

const state = initStanje();

const getters = {
  getMail: state => state.mail
}

const actions = {
  async logInAction({dispatch}, credentials) {
    try{
      const response = await Vue.axios.post("/auth/login", credentials); //printovati todo

      let accessToken = response.data.accessToken;
      console.log(accessToken, "SMO DOBILI ZA TOKEN")

      localStorage.setItem("token", accessToken);

      dispatch('updateAxiosAuth');
      dispatch('attempt', accessToken);
    }catch(error){
      alert("Greska log in- u");
    }
  },

  async attempt({ commit }, token){
    console.log("USLI VAMO")
    var jwt_data = jwt_decode(token);
    console.log("SAd jwt data: ");

    commit("SET_MAIL", jwt_data.sub); //mozda jwt_data.mail

    try {
      const response = await Vue.axios.get("/pacijent/mail/"+jwt_data.sub)
      alert("Korisnik ulogovan:"+ response.data.ime);
    }catch(error){
      alert("Greska pri dobavljanju podataka korisnika")
    }
  },

  updateAxiosAuth() {
    console.log("U AUTH ZA AXIOS")
    Vue.axios.defaults.headers['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
  }



}

const mutations = {
  // SET_ROLE(state, role){  //VELIKI TODO, napraviti da token salje role isto!

  // }
  SET_MAIL(state, mail){
    state.mail = mail;
  }
}

export default { state, mutations, actions, getters, namespaced: true}