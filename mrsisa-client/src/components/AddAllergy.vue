<template>
  <v-card>
    <v-card-title>Alergije na lekove i preparate: </v-card-title>
    <v-card-text>
      <v-form
        ref="forma"
        lazy-validation
        v-for="(lek, index) in lekovi"
        :key="index"
      >
        <v-checkbox v-model="lek.alergican" :label="lek.naziv"/>
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-btn class="mr-4" @click=dodaj>Sačuvaj izmene</v-btn>
    </v-card-actions>
    <v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>Izmene su uspešno sačuvane.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>
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
    true: true,
    dialog: false
  }),
  
  async mounted() {
    this.loadData();
  },

  methods: {
    async loadData() {
      const lekovi = [];
      await axios.get(`/lekovi/all`).then((response) => {
        response.data.forEach((element) => {
          lekovi.push({
            id: element.id,
            naziv: element.naziv,
            sastav: element.sastav,
            proizvodjac: element.proizvodjac,
            napomena: element.napomena,
            rezimIzdavanja: element.rezimIzdavanja,
            oblikLeka: element.oblikLeka,
            vrstaLeka: element.vrstaLeka,
            alergican: false
          });
          this.lekovi = lekovi;
        });
      });

      await axios.get(`/pacijent/alergije/${this.pacijentId}`).then((response) => {
        response.data.forEach((element) => {
          this.lekovi.find(x => x.id === element.id).alergican = true;
        });
      });
    },

    dodaj(){
      const alergije = []
      this.lekovi.forEach(element => {
        if(element.alergican) {
          alergije.push(element)
        }
      });
      axios.put(`/pacijent/dodaj/alergije/2`, alergije);
      this.dialog = true;
    },

     endDialog(){
        this.dialog = false;
    },
  }
};
</script>

<style>
</style>