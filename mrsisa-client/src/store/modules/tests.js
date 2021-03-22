import { testService } from "../../services/TestService"

const state = {
  tests: [],
}

const getters = {
  allTests: state => () => state.tests,

}

const actions = {

  getAllTests({commit}){
    alert("Tests.js")
    const tests = await testService.getAll();

    commit("SET_ALL", tests);
  }
}


const mutations = {
  SET_ALL(state, tests){
    state.tests = tests;
  }
}

export default{state, mutations, actions, getters, namespaced: true}