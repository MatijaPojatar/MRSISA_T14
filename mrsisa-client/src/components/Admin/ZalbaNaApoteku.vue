<template>
  <v-card>
    <v-card-text>
      <h2>Biraj apoteku (id): DORADITI</h2>
      <v-text-field
      v-model="apotekaId"
      label="ID Apoteke"
      type="number"
      required
      />

      <v-textarea
      height="400"
      outlined
      v-model="tekst"
      label="Tekst žalbe"
      required
      ></v-textarea>
    </v-card-text>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn @click="cancel">Odustani</v-btn>
      <v-btn @click="onSubmit">Pošalji</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import {mapActions, mapGetters } from "vuex" 

export default {
  name: "ZalbaNaApoteku",

  //fetch ko je ulogovan kao pacijent da pise zalbu

  data: () => ({
    apotekaId: 0,
    tekst: "",
    pacijentId: 1,
  }),

  computed: {
    ...mapGetters({
      allZalbeNaApoteke: "zalbe/getZalbeNaApoteke",
    })
  },

  methods: {
    ...mapActions({
      getZalbeNaApotekeAction: "zalbe/getZalbeNaApotekeAction",
      addZalbaNaApotekuAction: "zalbe/addZalbaNaApotekuAction"
    }),

    async onSubmit() {
      try{
        const zalbaDTO = {
          tekst : this.tekst,
          obradjena: false,
          pacijentId: this.pacijentId,
          apotekaId: this.apotekaId
        }

        await this.addZalbaNaApotekuAction(zalbaDTO);
        alert("Uspesno dodata zalba")
      }catch(error){
        alert("Greska pri dodavanju zalbe");
      }
    },

    async cancel(){
      this.apotekaId = "";
      this.tekst =  "";
    }
  }
}
</script>

<style>

</style>