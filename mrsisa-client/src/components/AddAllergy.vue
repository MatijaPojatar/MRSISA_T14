<template>
  <v-card>
    <v-card-title>Alergije na lekove i preparate: </v-card-title>
    <v-card-text>
      <v-form
        ref="forma"
        v-model="valid"
        lazy-validation
        v-for="(lek, index) in lekovi"
        :key="index"
      >
        <v-checkbox v-model="lek.alergican" :label="lek.naziv"/>
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-btn :disabled="!valid" class="mr-4">Sačuvaj izmene</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import axios from 'axios';

export default {
  name: "AddAllergy",

  props :{
    pacijentId: Number,
  },

  data: () => ({
    lekovi: [],
    true: true
  }),
  
  async mounted() {
    this.loadData();
  },

  methods: {
    async loadData() {
      const lekovi = [];
      await axios.get(`http://localhost:8080/lekovi/all`).then((response) => {
        response.data.forEach((element) => {
          lekovi.push({
            id: element.id,
            naziv: element.naziv + " - " + element.proizvodjac,
            alergican: false,
          });
          this.lekovi = lekovi;
        });
      });

      await axios.get(`http://localhost:8080/pacijent/alergije/${this.pacijentId}`).then((response) => {
        response.data.forEach((element) => {
          this.lekovi.find(x => x.id === element.id).alergican = true;
        });
      });
    },
  }
};
</script>

<style>
</style>