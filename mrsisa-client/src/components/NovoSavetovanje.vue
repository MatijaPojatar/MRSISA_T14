<template>
  <v-card class="mb-12" scrollable>
    <!-- <v-row justify="center">
      <v-date-picker v-model="picker" :min="trenutniDatum + 1"></v-date-picker>
    </v-row>
    <v-row justify="space-around" align="center">
      <v-col style="width: 350px; flex: 0 1 auto">
        <h2>Početak:</h2>
        <v-time-picker
          v-model="start"
          :max="end"
          :min="pocetakRadnogVremena"
          scrollable
        ></v-time-picker>
      </v-col>
      <v-col style="width: 350px; flex: 0 1 auto">
        <h2>Kraj:</h2>
        <v-time-picker
          v-model="end"
          :min="start"
          :max="krajRadnogVremena"
          scrollable
        ></v-time-picker>
      </v-col>
    </v-row>
    <v-spacer />
    <v-row justify="space-around" align="center">
      <v-btn color="green darken-1" text @click="kreirajTermin">
        Kreiraj termin
      </v-btn>
    </v-row> -->
  </v-card>
</template>

<script>
export default {
  name: "NovoSavetovanje",

  components: {},

  mounted() {},

  methods: {},
};
</script>

<style>
</style>