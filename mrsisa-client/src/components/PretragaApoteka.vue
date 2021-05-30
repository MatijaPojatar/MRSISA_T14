<template>
  <v-card class="mt-16 mx-auto" min-width="40%">
    <v-data-table :headers="headeri" :items="apoteke" />
    <v-divider />
    <v-row no-gutters justify="space-around">
      <v-col md="4">
        <v-text-field
          v-model="pretragaParams.naziv"
          label="Naziv"
        ></v-text-field>
      </v-col>
      <v-col md="4">
        <v-text-field v-model="pretragaParams.grad" label="Grad">
        </v-text-field>
      </v-col>
    </v-row>
    <v-card-actions class="justify-center">
      <v-btn @click="pretrazi"> Pretraži </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
//import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "PretragaApoteka",

  components: {},

  data: () => ({
    headeri: [
      { text: "Naziv", value: "naziv", sortable: false },
      { text: "Adresa", value: "adresa", sortable: false },
      { text: "Grad", value: "grad", sortable: false },
      { text: "Država", value: "drzava", sortable: false },
      { text: "Ocena", value: "ocena", sortable: false },
    ],
    apoteke: [],
    pretragaParams: {
      naziv: "",
      grad: "",
    },
  }),

  async mounted() {
    this.loadApoteke();
  },

  /*  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
    }),
  },*/

  methods: {
    async loadApoteke() {
      const apoteke = [];
      await axios.get(`http://localhost:8080/apoteke/sve/`).then((response) => {
        response.data.forEach((element) => {
          apoteke.push({
            naziv: element.naziv,
            adresa: element.adresa,
            grad: element.grad,
            drzava: element.drzava,
            ocena: 5,
          });
          this.apoteke = apoteke;
        });
      });
    },

    pretrazi() {
      axios
        .post(`http://localhost:8080/apoteke/pretraga`, this.pretragaParams)
        .then((response) => {
          this.apoteke = response.data;
        });
    },
  },
};
</script>

<style>
</style>