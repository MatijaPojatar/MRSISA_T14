<template>
   <v-container>
    <v-row>
      <v-col>
        <ZalbeNaApotekeTable :zalbe="neobradjeneApoteka" :canAnswer="true"></ZalbeNaApotekeTable>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <ZalbeNaFarmaceuteTable :zalbe="neobradjeneFarmaceut" :canAnswer="true"></ZalbeNaFarmaceuteTable>

      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <ZalbeNaDermatologeTable :zalbe="neobradjeneDermatolog" :canAnswer="true"></ZalbeNaDermatologeTable>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ZalbeNaApotekeTable from "./ZalbeNaApotekeTable"
import ZalbeNaFarmaceuteTable from "./ZalbeNaFarmaceuteTable"
import ZalbeNaDermatologeTable from "./ZalbeNaDermatologeTable"

import {mapGetters, mapActions} from 'vuex';

export default {
  components: {
    ZalbeNaApotekeTable,
    ZalbeNaFarmaceuteTable,
    ZalbeNaDermatologeTable
  },

  computed: {
    ...mapGetters({
      neobradjeneApoteka: "zalbe/getNeobradjeneApoteka",
      neobradjeneFarmaceut: "zalbe/getNeobradjeneFarmaceut",
      neobradjeneDermatolog: "zalbe/getNeobradjeneDermatolog"
    })
  },

   async mounted() {
    await this.getNeobradjeneApotekaAction();
    await this.getNeobradjeneFarmaceutAction();
    await this.getNeobradjeneDermatologAction();
  },

  methods: {
    ...mapActions({
      getNeobradjeneApotekaAction: "zalbe/getNeobradjeneApotekaAction",
      getNeobradjeneFarmaceutAction: "zalbe/getNeobradjeneFarmaceutAction",
      getNeobradjeneDermatologAction: "zalbe/getNeobradjeneDermatologAction"
    }),
  }

}
</script>

<style>

</style>