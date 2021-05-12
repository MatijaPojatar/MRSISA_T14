<template>
  <v-row>
    <v-expansion-panels>
      <v-expansion-panel
      v-for="ponuda in ponude"
      :key="ponuda.id"
      @click="selekcija(ponuda.id)">

      <v-expansion-panel-header>
        Ponuda za narudžbenicu: {{ponuda.narudzbenicaId}}
      </v-expansion-panel-header>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-row>
</template>

<script>
export default {
  data: () => ({
    ponude: [{id : 1, narudzbenicaId: 500}, {id: 2, narudzbenicaId: 505}]
  })
}
</script>

<style>

</style>