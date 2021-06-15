<template>
  <div>
    <v-card>
      <v-card-title>Kreiranje kategorije:</v-card-title>

      <v-text-field
      required
      label="Naziv"
      v-model="naziv"
      ></v-text-field>

      <v-text-field
      required
      type="number"
      label="Potreban broj poena"
      v-model="brPoena"
      ></v-text-field>

      <v-text-field
      required
      label="Procenat popusta"
      v-model="procenat"
      >
      </v-text-field>

      <v-card-actions>
        <v-btn
        color="blue"
        dark
        @click="dodajKat"
        >Dodaj</v-btn>
      </v-card-actions>

    </v-card>
    <v-card>
      <v-card-title>Kategorije</v-card-title>
      <v-data-table
      :headers="headers"
      :items="kategorije"
      sort-by="brojPoenaStart"
      />

    </v-card>

  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {

  data: () => ({
    brPoena: 0,
    naziv: "",
    procenat: 0.0,
    headers: [
      { text: 'Naziv', value: 'naziv'},
      { text: 'Broj poena', value: 'brojPoenaStart'},
      { text: '% Popusta', value: 'procenat'}
    ]

  }),

  computed: {
    ...mapGetters({
      kategorije: "loyalty/getAllKat",
    })
  },

  mounted(){
    this.getAllKatAction();
  },

  methods: {
    ...mapActions({
      addKatAction: "loyalty/addKatAction",
      getAllKatAction: "loyalty/getAllKatAction",
    }),



    dodajKat(){
      let dto = {
        brojPoenaStart: this.brPoena,
        naziv: this.naziv,
        procenat: this.procenat
      }

      try{
        this.addKatAction(dto);
        this.getAllKatAction();
        alert("Uspesno dodavanje")
        this.brojPoenaStart = 0;
        this.naziv = "";
        this.procenat = 0;
      }catch(err){
        console.log(err);
        alert("Neuspesno dodavanje")
      }
    }
  }
}
</script>

<style>

</style>