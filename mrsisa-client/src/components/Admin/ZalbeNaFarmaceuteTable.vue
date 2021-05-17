<template>
  <v-row>
    <div v-if="zalbe.length==0">
      <v-card
      flat
      width="500">
        <v-card-title>Trenutno nema žalbi na farmaceute</v-card-title>
      </v-card>
    </div>
    <v-expansion-panels>
      <v-expansion-panel
        v-for="zalba in zalbe"
        :key="zalba.id"
        @click="selekcija(zalba.id)"
      >

        <v-expansion-panel-header>
          Žalba na farmaceuta: {{zalba.farmaceutIP}}
        </v-expansion-panel-header>


        <v-expansion-panel-content>
          <div>
            Farmaceut: {{zalba.farmaceutIP}}
          </div>
          <v-divider/>
          <div>
            Autor žalbe: {{zalba.pacijentIP}}
          </div>
          <v-divider/>

          <div>
            Tekst: {{zalba.tekst}}
          </div>
          <v-divider/>

          <v-textarea
          outlined
          v-model="odgovor"
          label="Odgovor">
          </v-textarea>

          <br/>
            <v-btn
            v-if="canAnswer"
            dark
            color="green"
            @click="odgovoriNaZalbu"
            >
              Odgovori
            </v-btn>

        </v-expansion-panel-content>


      </v-expansion-panel>
    </v-expansion-panels>
    <v-dialog
      v-model="potvrda"
      persistent
      max-width="300"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>Uspešno ste odgovorili na žalbu.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endPotvrda"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>

  </v-row>
</template>

<script>
import { mapActions } from 'vuex';


export default {
  data: () => ({
    selektovanaZalba: {},
    selektovanaZalbaID: -1,
    odgovor: "",
    potvrda: false,
  }),

  props: [
    "zalbe",
    "canAnswer"
  ],


  methods: {
    ...mapActions({
      sendOdgovorFarmaceutAction: "zalbe/sendOdgovorFarmaceutAction"
    }),

    selekcija(id){
      this.selektovanaZalbaID = id;
    },

    odgovoriNaZalbu(){
      this.sendOdgovorFarmaceutAction({id: this.selektovanaZalbaID, odg: this.odgovor});
      this.potvrda = true;
    },

    endPotvrda(){
      this.potvrda = false;
    }

  }

}
</script>

<style>

</style>