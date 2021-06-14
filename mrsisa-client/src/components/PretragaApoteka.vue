<template>
  <v-card class="mt-16 mx-auto" min-width="40%">
    <v-row align="center" justify="space-around">
      <v-col align="center"> Ocena: </v-col>
      <v-col>
        <v-checkbox v-model="ocena2" label="< 2" />
      </v-col>
      <v-col>
        <v-checkbox v-model="ocena24" label="2 - 4" />
      </v-col>
      <v-col>
        <v-checkbox v-model="ocena4" label="> 4" />
      </v-col>
    </v-row>
    <v-divider />
    <v-row align="center" justify="space-around">
      <v-col align="center"> Udaljenost: </v-col>
      <v-col>
        <v-checkbox v-model="udaljenost5" label="< 500" />
      </v-col>
      <v-col>
        <v-checkbox v-model="udaljenost510" label="500 - 1000" />
      </v-col>
      <v-col>
        <v-checkbox v-model="udaljenost10" label="> 1000" />
      </v-col>
    </v-row>
    <v-divider />
    <v-data-table :headers="headeri" :items="filtriraneApoteke" />
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
      { text: "Udaljenost", value: "udaljenost", sortable: false },
    ],
    apoteke: [],
    pretragaParams: {
      naziv: "",
      grad: "",
    },
    ocena2: true,
    ocena24: true,
    ocena4: true,
    udaljenost5: true,
    udaljenost510: true,
    udaljenost10: true,
  }),

  async mounted() {
    this.loadApoteke();
  },

  computed: {
    filtriraneApoteke() {
      var filter1 = [];
      var filter2 = [];

      if (this.ocena2)
        filter1 = filter1.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.ocena) < 2));
      if (this.ocena24)
        filter1 = filter1.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.ocena) >= 2 && parseFloat(apoteka.ocena) < 4));
      if (this.ocena4)
        filter1 = filter1.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.ocena) >= 4));
      if (this.udaljenost5)
        filter2 = filter2.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.udaljenost) < 500));
      if (this.udaljenost510)
        filter2 = filter2.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.udaljenost) >= 500 && parseFloat(apoteka.udaljenost) < 1000));
      if (this.udaljenost10)
        filter2 = filter2.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.udaljenost) >= 1000));

      return filter1.filter(value => filter2.includes(value));
    }
  },

  methods: {
    async loadApoteke() {
      const apoteke = [];
      await axios.get(`/apoteke/sve/`).then((response) => {
        response.data.forEach((element) => {
          apoteke.push({
            naziv: element.naziv,
            adresa: element.adresa,
            grad: element.grad,
            drzava: element.drzava,
            ocena: element.id,
            udaljenost: 500,
          });
          this.apoteke = apoteke;
        });
      });
    },

    pretrazi() {
      axios
        .post(`/apoteke/pretraga`, this.pretragaParams)
        .then((response) => {
          this.apoteke = response.data;
          this.apoteke.forEach(element => {
            element.ocena = element.id;
            element.udaljenost = 500;
          });
        });
    },
  },
};
</script>

<style>
</style>