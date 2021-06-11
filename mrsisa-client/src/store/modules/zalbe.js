import Vue from "vue";

const initStanje = () => {
  return {
    //zalbeNaApoteke: [],
    moguciFarmaceuti: [],
    moguciDermatolozi: [],
    moguceApoteke: [],
    neobradjeneApoteka: [],
    neobradjeneFarmaceut: [],
    neobradjeneDermatolog: [],
    zalbePacNaA: [],
    zalbePacNaF: [],
    zalbePacNaD: [],
    zalbeAdminNaA: [],
    zalbeAdminNaF: [],
    zalbeAdminNaD: [],
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

  getZalbePacNaA: state => state.zalbePacNaA,
  getZalbePacNaF: state => state.zalbePacNaF,
  getZalbePacNaD: state => state.zalbePacNaD,

  getZalbeAdminNaA: state => state.zalbeAdminNaA,
  getZalbeAdminNaF: state => state.zalbeAdminNaF,
  getZalbeAdminNaD: state => state.zalbeAdminNaD
}

const actions = {

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
    const response3 = await Vue.axios.get("/rezervacija/apotekePacijenta/" + pacijentId);
    const response4 = await Vue.axios.get("/erecepti/apotekePacijenta/" + pacijentId);

    console.log("OVO SU MOGUCE APOTEKE")
    console.log(response1.data);
    console.log(response2.data);
    console.log(response3.data);
    console.log(response4.data);

    response1.data.forEach(item => {  
      moguceApoteke.add(item);
     })
     response2.data.forEach(item => {  
      moguceApoteke.add(item);
     })

     response3.data.forEach(item => {
       moguceApoteke.add(item);
     })

     response4.data.forEach(item => {
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

  async getZalbePacNaAAction({commit}, pacijentId){
    const response = await Vue.axios.get("zalbe/apoteka/pacijent/"+ pacijentId);

    commit("setZalbePacNaA", response.data);
  },

  async getZalbePacNaFAction({commit}, pacijentId){
    const response = await Vue.axios.get("zalbe/farmaceut/pacijent/" + pacijentId);

    commit("setZalbePacNaF", response.data);
  },

  async getZalbePacNaDAction({commit}, pacijentId){
    const response = await Vue.axios.get("zalbe/dermatolog/pacijent/" + pacijentId);

    commit("setZalbePacNaD", response.data);
  },

  async getZalbeAdminNaAAction({commit}, adminId){
    const response = await Vue.axios.get("zalbe/apoteka/obradjene/" + adminId);

    commit("setZalbeAdminNaA", response.data);
  },

  async getZalbeAdminNaFAction({commit}, adminId){
    const response = await Vue.axios.get("zalbe/farmaceut/obradjene/" + adminId);

    commit("setZalbeAdminNaF", response.data);
  },

  async getZalbeAdminNaDAction({commit}, adminId){
    const response = await Vue.axios.get("zalbe/dermatolog/obradjene/"+ adminId);

    commit("setZalbeAdminNaD", response.data);
  },

  async sendOdgovorApotekaAction({commit}, {zalba, idAdmina, odg}){
    zalba.administratorId = idAdmina;
    zalba.odgovor = odg;

    const response = await Vue.axios.put("/zalbe/apoteka/"+ zalba.id, zalba); 


    console.log("ODGOVOR");
    console.log(response);
    commit("setObradjenaZaApoteku", zalba.id);
  },

  async sendOdgovorFarmaceutAction({commit}, {zalba, idAdmina, odg}){
    zalba.administratorId = idAdmina;
    zalba.odgovor = odg;

    const response = await Vue.axios.put("zalbe/farmaceut/"+zalba.id, zalba);

    console.log("ODGOVOR");
    console.log(response);
    commit("setObradjenaZaFarmaceuta", zalba.id);
  },

  async sendOdgovorDermatologAction({commit},{zalba, idAdmina, odg}){
    zalba.administratorId = idAdmina;
    zalba.odgovor = odg;

    const response = await Vue.axios.put("zalbe/dermatolog/"+zalba.id, zalba);

    console.log("ODGOVOR");
    console.log(response);
    commit("setObradjenaZaDermatologa", zalba.id);
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
  },

  setZalbePacNaA(state, lista){
    state.zalbePacNaA = lista;
  },

  setZalbePacNaF(state, lista){
    state.zalbePacNaF = lista;
  },

  setZalbePacNaD(state, lista){
    state.zalbePacNaD = lista;
  },

  setZalbeAdminNaA(state, lista){
    state.zalbeAdminNaA = lista;
  },

  setZalbeAdminNaF(state, lista){
    state.zalbeAdminNaF = lista;
  },

  setZalbeAdminNaD(state, lista){
    state.zalbeAdminNaD = lista;
  },

}

export default { state, mutations, actions, getters, namespaced: true}