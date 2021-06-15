<template>
  <v-card>
    <v-card-title>Loyalty program:</v-card-title>
    <v-card-text> Poeni: 500 </v-card-text>
    <v-card-text> Kategorija: SILVER </v-card-text>
    <v-card-text>
      Pogodnosti: -15% na lekove, savetovanja i preglede
    </v-card-text>
    <br />
    <v-divider />
    <br />
    <v-card-title>Penali:</v-card-title>
    <v-card-text>Broj penala: {{ this.penali }} </v-card-text>
  </v-card>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "LoyaltyPenalty",

  data: () => ({
    penali: 0,
  }),

  computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik",
    }),
  },

  mounted() {
    this.setPenali();
  },

  methods: {
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