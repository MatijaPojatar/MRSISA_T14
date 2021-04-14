<template>
  <v-card>
    <v-card-text>
      <h2>Biraj apoteku (id): DORADITI</h2>
      <v-text-field
      v-model="apotekaId"
      :rules="[rules.required]"
      label="ID Apoteke"
      />

      <v-textarea
      height="400"
      outlined
      v-model="tekst"
      label="Tekst žalbe"
      ></v-textarea>
    </v-card-text>

    <v-card-actions>
      <v-btn>Pošalji</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {

}
</script>

<style>

</style>