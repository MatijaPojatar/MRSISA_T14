
const initStanje = () => {
  return {
    ponude : [],
  }
}

const state = initStanje();

const getters = {
  

}
const actions = {
}
const mutations = {
  // eslint-disable-next-line no-unused-vars
  resetState(state){
    state= initStanje();
  },

}

export default { state, mutations, actions, getters, namespaced: true}