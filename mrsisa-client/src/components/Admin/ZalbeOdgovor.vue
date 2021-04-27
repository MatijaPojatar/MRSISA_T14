<template>
  <div>
    <h3>Zalbe na Apoteke</h3>
    <ZalbeNaApotekeTable></ZalbeNaApotekeTable>

    <h3>Zalbe na Farmaceute</h3>
    <ZalbeNaFarmaceuteTable></ZalbeNaFarmaceuteTable>

    <h3>Zalbe na Dermatologe</h3>
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