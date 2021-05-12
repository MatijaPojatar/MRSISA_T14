<template>
  <v-row>
    <div v-if="nemaZalbi">
      <v-card
      flat
      width="500">
        <v-card-title>Trenutno nema žalbi na dermatologe</v-card-title>
      </v-card>
    </div>
    <v-expansion-panels>
      <v-expansion-panel
        v-for="zalba in neobradjene"
        :key="zalba.id"
        @click="selekcija(zalba.id)"
      >

        <v-expansion-panel-header>
          Žalba na dermatologa: {{zalba.dermatologIP}}
        </v-expansion-panel-header>


        <v-expansion-panel-content>
          <div>
            Dermatolog: {{zalba.dermatologIP}}
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
          label="Odgovor"
          v-model="odgovor">
          </v-textarea>

          <br/>
            <v-btn
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
import { mapActions, mapGetters } from 'vuex';

export default {
  data: () => ({
    selektovanaZalba: {},
    selektovanaZalbaID: -1,
    odgovor: "",
    potvrda: false,
  }),

  computed: {
    ...mapGetters({
      neobradjene: "zalbe/getNeobradjeneDermatolog",
      nemaZalbi: "zalbe/getNemaZalbizaDermatologa"
    }),
  },

  async mounted() {
    this.getNeobradjeneDermatologAction();
  },

  methods: {
    ...mapActions({
      getNeobradjeneDermatologAction: "zalbe/getNeobradjeneDermatologAction",
      sendOdgovorDermatologAction: "zalbe/sendOdgovorDermatologAction"
    }),

    selekcija(id){
      this.selektovanaZalbaID = id;
    },

    odgovoriNaZalbu(){
      this.sendOdgovorDermatologAction({id: this.selektovanaZalbaID, odg: this.odgovor});
      this.potvrda=true;
    },

    endPotvrda(){
      this.potvrda = false;
    },

  }

}
</script>

<style>

</style>