<template>
  <v-card>
    <v-card-text>
      <h2>Biraj farmaceuta</h2> 
      <v-autocomplete
        v-model="izabraniFarmaceutId"
        :items="moguciFarmaceuti"
        label="Farmaceut"
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
  name: "ZalbaNaFarmaceuta",

  data: () => ({
    izabraniFarmaceutId: null,
    tekst: "",
  }),

  async beforeMount() {
    await this.getMoguceFarmaceuteAction(this.korisnik.id);
  },


  computed: {
    ...mapGetters({
      korisnik : "korisnici/getKorisnik",
      moguciFarmaceuti : "zalbe/getMoguciFarmaceuti"
    })
  },

  methods: {
    ...mapActions({
      addZalbaNaFarmaceutaAction: "zalbe/addZalbaNaFarmaceutaAction",
      getMoguceFarmaceuteAction: "zalbe/getMoguceFarmaceuteAction"
    }),


    async onSubmit() {
      console.log("IZABRANI")
      console.log(this.izabraniFarmaceutId)

      try{
        console.log("OVAJ JE ONLINE")
        console.log(this.korisnik.ime)
        const zalbaDTO = {
          tekst : this.tekst,
          obradjena: false,
          pacijentId: this.korisnik.id,
          farmaceutId: this.izabraniFarmaceutId
        }

        await this.addZalbaNaFarmaceutaAction(zalbaDTO);
        alert("Uspesno dodata zalba")
      }catch(error){
        alert("Greska pri dodavanju zalbe");
      }
    },

    async cancel(){
      this.izabraniFarmaceutId = null;
      this.tekst =  "";
    }
  }
}
</script>

<style>

</style>