<template>
  <v-card>
    <v-card-title>Dodavanje alergije: </v-card-title>
    <v-card-text>
      <v-form
        ref="forma"
        v-model="valid"
        lazy-validation
        v-for="(lek, index) in lekovi"
        :key="index"
      >
        <v-checkbox v-model="lek1" label="Test 1 - Test 1 - Test 1" />
        <v-checkbox v-model="lek2" label="Test 2 - Test 2 - Test 2" />
        <v-checkbox v-model="lek3" label="Test 3 - Test 3 - Test 3" />
        <v-checkbox v-model="lek4" label="Test 4 - Test 4 - Test 4" />
        <v-checkbox v-model="lek5" label="Test 5 - Test 5 - Test 5" />
        <v-checkbox v-model="lek6" label="Test 6 - Test 6 - Test 6" />
        <v-checkbox v-model="lek7" label="Test 7 - Test 7 - Test 7" />
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-btn :disabled="!valid" class="mr-4">Sačuvaj</v-btn>
      <v-btn color="error" class="mr-4" @click="reset">Odustani</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  name: "AddAllergy",

  props: {
    id: Number,
  },

  data: () => ({
    lekovi: [],
    lek1: false,
    lek2: false,
    lek3: false,
    lek4: false,
    lek5: true,
    lek6: false,
    lek7: false,
  }),

  mounted() {
    axios.get(`http://localhost:8080/lekovi/all`).then((response) => {
      const events = [];
      response.data.forEach((element) => {
        events.push({
          naziv: element.name,
        });
        this.lekovi = ["Test 1"];
      });
    });
  },

  methods: {
      reset(){
        this.lek1=false
        this.lek2=false
        this.lek3=false
        this.lek4=false
        this.lek5=true
        this.lek6=false
        this.lek7=false
      },
    },
};
</script>

<style>
</style>