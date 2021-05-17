<template>
<v-container>
    <v-row>
      <v-col>
        <ZalbeNaApotekeTable :zalbe="zalbeAdminNaA" :canAnswer="false"></ZalbeNaApotekeTable>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <ZalbeNaFarmaceuteTable :zalbe="zalbeAdminNaF" :canAnswer="false"></ZalbeNaFarmaceuteTable>

      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <ZalbeNaDermatologeTable :zalbe="zalbeAdminNaD" :canAnswer="false"></ZalbeNaDermatologeTable>

      </v-col>
    </v-row>
  </v-container>
  
</template>

<script>
import ZalbeNaApotekeTable from "./ZalbeNaApotekeTable"
import ZalbeNaFarmaceuteTable from "./ZalbeNaFarmaceuteTable"
import ZalbeNaDermatologeTable from "./ZalbeNaDermatologeTable"

import {mapGetters, mapActions} from 'vuex'

export default {
  components: {
    ZalbeNaApotekeTable,
    ZalbeNaFarmaceuteTable,
    ZalbeNaDermatologeTable
  },

  computed: {
    ...mapGetters({
      zalbeAdminNaA: "zalbe/getZalbeAdminNaA",
      zalbeAdminNaF: "zalbe/getZalbeAdminNaF",
      zalbeAdminNaD: "zalbe/getZalbeAdminNaD",
      user: "korisnici/getKorisnik"
    })
  },

  async mounted() {
    await this.getZalbeAdminNaAAction(this.user.id);
    await this.getZalbeAdminNaFAction(this.user.id);
    await this.getZalbeAdminNaDAction(this.user.id);
  },

  methods: {
    ...mapActions({
      getZalbeAdminNaAAction: "zalbe/getZalbeAdminNaAAction",
      getZalbeAdminNaFAction: "zalbe/getZalbeAdminNaFAction",
      getZalbeAdminNaDAction: "zalbe/getZalbeAdminNaDAction"
    })
  }
}
</script>

<style>

</style>