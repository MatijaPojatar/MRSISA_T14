<template>
  <v-card>
    <v-card-text>
      <h2>Biraj dermatologa</h2> 

       <v-autocomplete
        v-model="izabraniDermatologId"
        :items="moguciDermatolozi"
        label="Dermatolog"
        item-text="ime"
        item-value="id"
        required>

        
          <template slot="selection" slot-scope="data">
            <!-- HTML that describe how select should render selected items -->
            {{ data.item.ime }} {{ data.item.prezime }}
          </template>

          <template slot="item" slot-scope="data">
            <!-- HTML that describe how select should render items when the select is open -->
            {{ data.item.ime }} {{ data.item.prezime }}
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
  name: "ZalbaNaDermatologa",

  data: () => ({
    izabraniDermatologId: null,
    tekst: "",
  }),

  async beforeMount(){
     await this.getMoguceDermatologeAction(this.korisnik.id);
  },

  computed: {
    ...mapGetters({
      korisnik : "korisnici/getKorisnik",
      moguciDermatolozi : "zalbe/getMoguciDermatolozi"
    })
  },

  methods: {
    ...mapActions({
      addZalbaNaDermatologaAction: "zalbe/addZalbaNaDermatologaAction",
      getMoguceDermatologeAction: "zalbe/getMoguceDermatologeAction"
    }),

    async onSubmit() {
      try{
        console.log("OVAJ JE ONLINE")
        console.log(this.korisnik.ime)
        const zalbaDTO = {
          tekst : this.tekst,
          obradjena: false,
          pacijentId: this.korisnik.id,
          dermatologId: this.izabraniDermatologId
        }

        await this.addZalbaNaDermatologaAction(zalbaDTO);
        alert("Uspesno dodata zalba")
        this.tekst = "";
      }catch(error){
        alert("Greska pri dodavanju zalbe");
      }
    },

    async cancel(){
      this.dermatologId = "";
      this.tekst =  "";
    }
  }
}
</script>

<style>

</style>