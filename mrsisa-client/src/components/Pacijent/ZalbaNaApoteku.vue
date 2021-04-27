<template>
  <v-card>
    <v-card-text>
      <h2>Biraj apoteku</h2> 

       <v-autocomplete
        v-model="izabranaApotekaId"
        :items="moguceApoteke"
        label="Apoteka"
        item-text="naziv"
        item-value="id"
        required>

        
          <template slot="selection" slot-scope="data">
            <!-- HTML that describe how select should render selected items -->
            {{ data.item.naziv }} 
          </template>

          <template slot="item" slot-scope="data">
            <!-- HTML that describe how select should render items when the select is open -->
            {{ data.item.naziv }}
          </template>

      </v-autocomplete>

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
    izabranaApotekaId: 0,
    tekst: "",
  }),

  computed: {
    ...mapGetters({
      allZalbeNaApoteke: "zalbe/getZalbeNaApoteke",
      korisnik : "korisnici/getKorisnik",
      moguceApoteke : "zalbe/getMoguceApoteke"
    })
  },

  async beforeMount(){
    await this.getMoguceApotekeAction(this.korisnik.id);
  },

  methods: {
    ...mapActions({
      getZalbeNaApotekeAction: "zalbe/getZalbeNaApotekeAction",
      addZalbaNaApotekuAction: "zalbe/addZalbaNaApotekuAction",
      getMoguceApotekeAction: "zalbe/getMoguceApotekeAction"
    }),

    async onSubmit() {
      try{
        const zalbaDTO = {
          tekst : this.tekst,
          obradjena: false,
          pacijentId: this.korisnik.id,
          apotekaId: this.izabranaApotekaId
        }

        await this.addZalbaNaApotekuAction(zalbaDTO);
        alert("Uspesno dodata zalba")
      }catch(error){
        alert("Greska pri dodavanju zalbe");
      }
    },

    async cancel(){
      this.izabranaApotekaId = "";
      this.tekst =  "";
    }
  }
}
</script>

<style>

</style>