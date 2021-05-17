<template>
  <v-container>
    <v-row>
      <v-col>
        <ZalbeNaApotekeTable :zalbe="zalbePacNaA" :canAnswer="false"></ZalbeNaApotekeTable>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <ZalbeNaFarmaceuteTable :zalbe="zalbePacNaF" :canAnswer="false"></ZalbeNaFarmaceuteTable>

      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <ZalbeNaDermatologeTable :zalbe="zalbePacNaD" :canAnswer="false"></ZalbeNaDermatologeTable>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ZalbeNaApotekeTable from "../Admin/ZalbeNaApotekeTable"
import ZalbeNaFarmaceuteTable from "../Admin/ZalbeNaFarmaceuteTable"
import ZalbeNaDermatologeTable from "../Admin/ZalbeNaDermatologeTable"

import {mapGetters, mapActions} from 'vuex'

export default {
  components: {
    ZalbeNaApotekeTable,
    ZalbeNaFarmaceuteTable,
    ZalbeNaDermatologeTable
  },

  computed: {
    ...mapGetters({
      zalbePacNaA: "zalbe/getZalbePacNaA",
      zalbePacNaF: "zalbe/getZalbePacNaF",
      zalbePacNaD: "zalbe/getZalbePacNaD",
      user: "korisnici/getKorisnik"
    })
  },

  async mounted() {
    await this.getZalbePacNaAAction(this.user.id);
    await this.getZalbePacNaFAction(this.user.id);
    await this.getZalbePacNaDAction(this.user.id);
  },

  methods: {
    ...mapActions({
      getZalbePacNaAAction: "zalbe/getZalbePacNaAAction",
      getZalbePacNaFAction: "zalbe/getZalbePacNaFAction",
      getZalbePacNaDAction: "zalbe/getZalbePacNaDAction"
    })
  }

}
</script>

<style>

</style>