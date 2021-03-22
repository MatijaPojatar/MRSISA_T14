<template>
  <div>
    <h2>LISTA TESTOVA:</h2>
    <table>
        <thead>
          <tr>
            <th>Id</th>
            <th>Text</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="test in tests" v-bind:key="test.id">
            <td>{{test.id}}</td>
            <td>{{test.text}}</td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script>
// import { mapActions, mapState } from "vuex"

import TestService from '../services/TestService'

export default {
  name: "ListTests",

  data() {
    return {
      tests: [],
      INSTRUCTOR: "tests"
    }
  },

  methods: {
    refreshTests() {
      TestService.retrieveAllTests(this.INSTRUCTOR) //HARDCODED
        .then(response => {
          this.tests = response.data;
        });
    }
  },

  // computed: {
  //   ...mapState({ allTests: state => state.tests})
  // },

  created() {
    // alert("Created");
    // this.getTests();
    this.refreshTests();
  }
}
</script>

<style>

</style>