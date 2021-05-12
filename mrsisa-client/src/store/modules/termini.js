import Vue from "vue";

const initStanje = () => {
    return {
      savetovanjaPacijenta : [],
      preglediPacijenta: [],
      terminiPacijenta: [],
    }
  }
  
  const state = initStanje();
  
  const getters = {
    getTerminiPacijenta: state => state.terminiPacijenta,
  
  }
  const actions = {
    getSavetovanjaPacijentaAction({commit},id ){
        try{
          const response = Vue.axios.get("/savetovanje/pacijent/" + id);

          const events = []
          response.data.forEach(element => {
            events.push({
              name:element.name,
              pacijent:element.pacijentId,
              apoteka: element.apotekaId,
              start: new Date(element.start[0].toString()+"-"+element.start[1].toString()+"-"+element.start[2].toString()+" "+element.start[3].toString()+":"+element.start[4].toString()),
              end: new Date(element.end[0].toString()+"-"+element.end[1].toString()+"-"+element.end[2].toString()+" "+element.end[3].toString()+":"+element.end[4].toString()),
              izvrsen: element.izvrsen,
              izvestaj: element.izvestaj,
              id: element.id,
              started: false,
              timed: true,
            })
          });
        
          commit("setSavetovanjaPacijenta", events);
  
          //
        }catch(error){
            alert("Greska u dobavljanju savetovanja pacijenta")
        }
      },

    getPreglediPacijentaAction({commit},id ){
        try{
          const response = Vue.axios.get("/pregled/pacijent/" + id);

          const events = []
          response.data.forEach(element => {
            events.push({
              name:element.name,
              pacijent:element.pacijentId,
              apoteka: element.apotekaId,
              start: new Date(element.start[0].toString()+"-"+element.start[1].toString()+"-"+element.start[2].toString()+" "+element.start[3].toString()+":"+element.start[4].toString()),
              end: new Date(element.end[0].toString()+"-"+element.end[1].toString()+"-"+element.end[2].toString()+" "+element.end[3].toString()+":"+element.end[4].toString()),
              izvrsen: element.izvrsen,
              izvestaj: element.izvestaj,
              id: element.id,
              started: false,
              timed: true,
            })
          });

          commit("setPreglediPacijenta", events);
  
          //
        }catch(error){
            alert("Greska u dobavljanju pregleda pacijenta")
        }
      },


      async getTerminiPacijentaAction({commit, dispatch}, id){
        dispatch('getSavetovanjaPacijentaAction', id);
        dispatch('getPreglediPacijentaAction', id);

        commit("setTerminiPacijenta");
      }
  }
  const mutations = {
    // eslint-disable-next-line no-unused-vars
    resetState(state){
      state= initStanje();
    },
  
    setSavetovanjaPacijenta(state, lista){
        state.savetovanjaPacijenta = lista;
    },

    setPreglediPacijenta(state, lista) {
        state.preglediPacijenta = lista;
    },

    setTerminiPacijenta(state){
        state.terminiPacijenta = state.savetovanjaPacijenta;
        state.terminiPacijenta.concat(state.preglediPacijenta);
    }
   
  
  }
  
  export default { state, mutations, actions, getters, namespaced: true}