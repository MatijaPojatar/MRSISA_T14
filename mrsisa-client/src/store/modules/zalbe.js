import Vue from "vue";

const initStanje = () => {
  return {
    //zalbeNaApoteke: [],
    moguciFarmaceuti: [],
    moguciDermatolozi: [],
    moguceApoteke: [],
    neobradjeneApoteka: [],
    neobradjeneFarmaceut: [],
    neobradjeneDermatolog: []
  }
}

const state = initStanje();

const getters = {
  //getZalbeNaApoteke: state => state.zalbeNaApoteke,
  getMoguciFarmaceuti: state => state.moguciFarmaceuti,
  getMoguciDermatolozi: state => state.moguciDermatolozi,
  getMoguceApoteke: state => state.moguceApoteke,
  getNeobradjeneApoteka: state => state.neobradjeneApoteka,
  getNeobradjeneFarmaceut: state => state.neobradjeneFarmaceut,
  getNeobradjeneDermatolog: state => state.neobradjeneDermatolog,
  getNemaZalbiZaApoteke: state => state.neobradjeneApoteka.length == 0,
  getNemaZalbiZaFarmaceuta: state => state.neobradjeneFarmaceut.length == 0,
  getNemaZalbizaDermatologa: state => state.neobradjeneDermatolog.length == 0,

}

const actions = {

  // async getZalbeNaApotekeAction({commit}){
  //   try{
  //     const { data : zalbe } = await Vue.axios.get("/zalbe/apoteka");
  //     commit("setZalbeNaApoteke", zalbe);
  //   } catch(error){
  //     alert("Greska pri dobavljanju zalbi za Apoteke");
  //   }
  // },

  async addZalbaNaApotekuAction({commit}, zalbaDTO){
    try{
      const { data: zalba } = await Vue.axios.post("/zalbe/apoteka", zalbaDTO)
      commit("addZalbaNaApoteku", zalba);
    }catch(error){
      alert("Greska u dodavanju zalbe")
    }
  },

  async addZalbaNaFarmaceutaAction( {commit},zalbaDTO){
    try{
      const { data: zalba } = await Vue.axios.post("/zalbe/farmaceut", zalbaDTO)
      console.log(zalba)
      console.log(commit);
    }catch(error){
      alert("Greska u dodavanju zalbe")
    }
  },

  async addZalbaNaDermatologaAction( {commit},zalbaDTO){
    try{
      const { data: zalba } = await Vue.axios.post("/zalbe/dermatolog", zalbaDTO)
      console.log(zalba)
      console.log(commit);
    }catch(error){
      alert("Greska u dodavanju zalbe")
    }
  },

//METODE ZA NABAVLJANJE OPCIJA ZA NASEG PACIJENTA

  async getMoguceApotekeAction({commit} , pacijentId){
    let moguceApoteke = new Set();
    
    const response1 = await Vue.axios.get("/pregled/apotekePacijenta/" + pacijentId);
    const response2 = await Vue.axios.get("/savetovanje/apotekePacijenta/" + pacijentId);

    console.log("OVO SU MOGUCE APOTEKE")
    console.log(response1);
    console.log(response2);

    response1.data.forEach(item => {  
      moguceApoteke.add(item);
     })
     response2.data.forEach(item => {  
      moguceApoteke.add(item);
     })

    console.log(moguceApoteke);

    
    commit("setMoguceApoteke", Array.from(moguceApoteke));
  },

  async getMoguceDermatologeAction({commit} ,pacijentId){
   
    const response = await Vue.axios.get("/pregled/dermatoloziPacijenta/"+ pacijentId);
    console.log(response);

    commit("setMoguceDermatologe", response.data);
  },

  async getMoguceFarmaceuteAction({commit}, pacijentId){
    
    const response = await Vue.axios.get("/savetovanje/farmaceutiPacijenta/"+ pacijentId);
    console.log(response);

    commit("setMoguceFarmaceute", response.data);
  },
//METODE ZA GETOVANJE ZALBI ZA ADMINA
  async getNeobradjeneApotekaAction({commit}){
    const response = await Vue.axios.get("/zalbe/apoteka/neobradjene");

    console.log("NEOBRADJENE APOTEKA")
    console.log(response)
    commit("setNeobradjeneApoteka", response.data)
  },

  async getNeobradjeneFarmaceutAction({commit}){
    const response = await Vue.axios.get("/zalbe/farmaceut/neobradjene")

    commit("setNeobradjeneFarmaceut", response.data)
  },

  async getNeobradjeneDermatologAction({commit}){
    const response = await Vue.axios.get("/zalbe/dermatolog/neobradjene")

    commit("setNeobradjeneDermatolog", response.data)
  },


  async sendOdgovorApotekaAction({commit}, {id, odg}){
    const response = await Vue.axios.put("/zalbe/apoteka/"+ id, odg);


    console.log("ODGOVOR");
    console.log(response);
    commit("setObradjenaZaApoteku", id);
  },

  async sendOdgovorFarmaceutAction({commit}, {id, odg}){
    const response = await Vue.axios.put("zalbe/farmaceut/"+id, odg);

    console.log("ODGOVOR");
    console.log(response);
    commit("setObradjenaZaFarmaceuta", id);
  },

  async sendOdgovorDermatologAction({commit}, {id, odg}){
    const response = await Vue.axios.put("zalbe/dermatolog/"+id, odg);

    console.log("ODGOVOR");
    console.log(response);
    commit("setObradjenaZaDermatologa", id);
  },


}

const mutations = {
  // eslint-disable-next-line no-unused-vars
  resetState(state){
    state = initStanje();
  },

  setObradjenaZaApoteku(state, id){
    const index = state.neobradjeneApoteka.findIndex((l) => l.id === id);
    state.neobradjeneApoteka.splice(index,1);
    //za svaku zalbu, ako je id takav, njenbool je true
    // var i;
    // for (i = 0; i < state.neobradjeneApoteka.length; i++) {
    //   if(state.neobradjeneApoteka[i].id == id){
    //     state.neobradjeneApoteka[i].obradjena = true;           //ako bude trebao pregled svih zalbi, onda treba pri odgovaranju ovo odraditi
    //   }
    // }
  },

  setObradjenaZaFarmaceuta(state, id){
    const index = state.neobradjeneFarmaceut.findIndex((l) => l.id === id);
    state.neobradjeneFarmaceut.splice(index,1);
  },

  setObradjenaZaDermatologa(state, id){
    const index = state.neobradjeneDermatolog.findIndex((l) => l.id === id);
    state.neobradjeneDermatolog.splice(index,1);
  },

  setNeobradjeneApoteka(state, zalbe){
    state.neobradjeneApoteka = zalbe;
  },

  setNeobradjeneFarmaceut(state,zalbe ){
    state.neobradjeneFarmaceut = zalbe;
  },

  setNeobradjeneDermatolog(state, zalbe){
    state.neobradjeneDermatolog = zalbe;
  },

  setMoguceApoteke(state, apoteke){
    state.moguceApoteke = apoteke;
  },

  setMoguceFarmaceute(state, farmaceuti){
    state.moguciFarmaceuti = farmaceuti;
  },

  setMoguceDermatologe(state, dermatolozi){
    state.moguciDermatolozi = dermatolozi;
  },

  setZalbeNaApoteke(state, zalbe){
    state.zalbeNaApoteke = zalbe;
  },

  addZalbaNaApoteku(state, zalba){
    state.zalbeNaApoteke.push(zalba);
  }
}

export default { state, mutations, actions, getters, namespaced: true}