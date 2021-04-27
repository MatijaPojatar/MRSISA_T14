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
        <v-checkbox v-model="lek1" label="Bromazepam - Hemofarm" />
        <v-checkbox v-model="lek2" label="Fervex - UPSA SAS" />
        <v-checkbox v-model="lek3" label="Bactrim - Galenika" />
        <v-checkbox v-model="lek4" label="Konjska mast - Krauterhof" />
        <v-checkbox v-model="lek5" label="Caffetin - Alkaloid" />
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
    lek3: true,
    lek4: false,
    lek5: false
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
        this.lek3=true
        this.lek4=false
        this.lek5=false
      },
    },
};
</script>

<style>
</style>