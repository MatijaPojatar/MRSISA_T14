<template>
  <v-container>
    <v-expansion-panels style="width: 600px">
      <v-expansion-panel v-for="ap in akcijePromocije" :key="ap.id">
        <v-expansion-panel-header>
          {{ ap.apotekaNaziv }} - Akcija i promocija {{ ap.id }}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <div>Opis: {{ ap.opis }}</div>
          <v-divider />
          <div>Naziv apoteke: {{ ap.apoteka }}</div>
          <v-divider />
          <div>Početak važenja: {{ ap.pocetak[0].toString() + "-" + ap.pocetak[1].toString() + "-" + ap.pocetak[2].toString() }}</div>
          <v-divider />
          <div>Kraj važenja: {{ ap.kraj[0].toString() + "-" + ap.kraj[1].toString() + "-" + ap.kraj[2].toString() }}</div>
          <v-divider />
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  components: {},

  computed: {
    ...mapGetters({
      akcijePromocije: "akcijePromocije/getAkcijePromocije",
      user: "korisnici/getKorisnik",
    }),
  },

  async mounted() {
    await this.getAkcijePromocijeAction(this.user.id);
  },

  methods: {
    ...mapActions({
      getAkcijePromocijeAction: "akcijePromocije/getAkcijePromocijeAction",
    }),
  },
};
</script>

<style>
</style>