<template>
  <div>
    <ZalbeNaApotekeTable></ZalbeNaApotekeTable>

    <ZalbeNaFarmaceuteTable></ZalbeNaFarmaceuteTable>

    <ZalbeNaDermatologeTable></ZalbeNaDermatologeTable>
  </div>
</template>

<script>
import ZalbeNaApotekeTable from "./ZalbeNaApotekeTable"
import ZalbeNaFarmaceuteTable from "./ZalbeNaFarmaceuteTable"
import ZalbeNaDermatologeTable from "./ZalbeNaDermatologeTable"

export default {
  components: {
    ZalbeNaApotekeTable,
    ZalbeNaFarmaceuteTable,
    ZalbeNaDermatologeTable
  }

}
</script>

<style>

</style>