<template>
  <v-card>
    <v-card-title>Loyalty program:</v-card-title>
    <v-card-text> Poeni: {{bodovi}} </v-card-text>
    <v-card-text> Kategorija: {{kategorija.naziv}} </v-card-text>
    <v-card-text>
      Pogodnosti: -{{kategorija.procenat}}% na lekove, savetovanja i preglede
    </v-card-text>
    <br />
    <v-divider />
    <br />
    <v-card-title>Penali:</v-card-title>
    <v-card-text>Broj penala: {{ this.penali }} </v-card-text>
  </v-card>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import axios from "axios";

export default {
  name: "LoyaltyPenalty",

  data: () => ({
    penali: 0,
    
  }),

  computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik",
      kategorija: "loyalty/getPacijentKategorija",
      bodovi: "loyalty/getPacijentBodovi"
    }),
  },

  mounted() {
    this.setPenali();
    this.getPacijentKategorijaAction(this.user.id);
    this.getPacijentBodoviAction(this.user.id);
  },

  methods: {
    ...mapActions({
      getPacijentKategorijaAction: "loyalty/getPacijentKategorijaAction",
      getPacijentBodoviAction: "loyalty/getPacijentBodoviAction"
    }),


    setPenali() {
      axios.get(`/pacijent/penali/${this.user.id}`).then((response) => {
        this.penali = response.data;
      });
    },
  },
};
</script>

<style>
</style>