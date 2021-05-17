<template>
  <div>
    <ZalbeNaApotekeTable :zalbe="neobradjeneApoteka" :canAnswer="true"></ZalbeNaApotekeTable>

    <ZalbeNaFarmaceuteTable :zalbe="neobradjeneFarmaceut" :canAnswer="true"></ZalbeNaFarmaceuteTable>

    <ZalbeNaDermatologeTable :zalbe="neobradjeneDermatolog" :canAnswer="true"></ZalbeNaDermatologeTable>
  </div>
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