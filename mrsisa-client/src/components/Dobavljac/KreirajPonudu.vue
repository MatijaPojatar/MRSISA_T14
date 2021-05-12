<template>
  <v-card
  width = "500"
  center>
    <v-card-title>Kreiraj Ponudu</v-card-title>
    <v-form>
      <v-text-field
      v-model="cena"
      label="cena"
      ></v-text-field>

      <v-text-field
      v-model="datum"
      label="datum"
      >
      </v-text-field>
      <v-text-field
      v-model="vreme"
      label="Vreme isporuke"
      ></v-text-field> 
      <!-- dodati date picker, time picker mozda -->

      <v-text-field
      v-model="narudzbenica"
      label="Narudzbenica">

      </v-text-field>
    </v-form>
    <v-card-actions>
      <v-btn @click="posalji">Pošalji</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    cena: 300,
    datum: "03/06/2021",
    vreme: "17:30",
    narudzbenica: "35"
  }),
  methods: {
    posalji(){
      alert("Ponuda je poslata")
    }
  }
}
</script>

<style>

</style>