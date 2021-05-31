<template>
  <v-container>
    <v-expansion-panels style="width: 600px">
      <v-expansion-panel v-for="r in rezervacije" :key="r.code">
        <v-expansion-panel-header>
          Rezervacija - {{ r.code }}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <div>Naziv leka: {{ r.lekNaziv }}</div>
          <v-divider />
          <div>Količina: {{ r.kolicina }}</div>
          <v-divider />
          <div>Naziv apoteke: {{ r.apotekaNaziv }}</div>
          <v-divider />
          <div>Rok preuzimanja: {{ r.datum[0].toString() + "-" + r.datum[1].toString() + "-" + r.datum[2].toString() }}</div>
          <v-divider />
        <v-card-actions class="justify-center">
          <v-btn text>Otkaži</v-btn>
        </v-card-actions>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "RezervisaniLekovi",
  
  components: {},

  computed: {
    ...mapGetters({
      rezervacije: "rezervacije/getRezervacije",
      korisnik: "korisnici/getKorisnik",
    }),
  },

  async mounted() {
    await this.getRezervacijeAction(this.korisnik.id);
  },

  methods: {
    ...mapActions({
      getRezervacijeAction: "rezervacije/getRezervacijeAction",
    }),
  },
};
</script>

<style>
</style>