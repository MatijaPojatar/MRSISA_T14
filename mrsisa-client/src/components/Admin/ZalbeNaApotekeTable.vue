<template>
  <v-row>
    <div v-if="zalbe.length==0">
      <v-card
      flat
      width="500">
        <v-card-title>Trenutno nema žalbi na apoteke</v-card-title>
      </v-card>
    </div>
    <v-expansion-panels>
      <v-expansion-panel
        v-for="zalba in zalbe"
        :key="zalba.id"
        @click="selekcija(zalba.id)"
      >

        <v-expansion-panel-header>
          Žalba na apoteku: {{zalba.apotekaNaziv}}
        </v-expansion-panel-header>


        <v-expansion-panel-content>
          <div>
            Naziv apoteke: {{zalba.apotekaNaziv}}
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

          <v-btn
          v-if="canAnswer"
          dark
          color="green"
          @click="odgovoriNaZalbu(zalba)"
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

  props: [
    "zalbe",
    "canAnswer"
  ],

  computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik"
    })
  },
    
  methods: {
    ...mapActions({
      sendOdgovorApotekaAction: "zalbe/sendOdgovorApotekaAction"
    }),

    selekcija(id){
      this.selektovanaZalbaID = id;
    },

    odgovoriNaZalbu(zalbaUObradi){//{zalba, idAdmina, odg}
      this.sendOdgovorApotekaAction({zalba: zalbaUObradi, idAdmina: this.user.id, odg: this.odgovor});
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