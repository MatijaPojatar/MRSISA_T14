<template>
  <v-row>
    <div v-if="nemaZalbi">
      <v-card>
        <v-card-title>Trenutno nema žalbi na apoteke</v-card-title>
      </v-card>
    </div>
    <v-expansion-panels>
      <v-expansion-panel
        v-for="zalba in neobradjene"
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
          dark
          color="green"
          @click="odgovoriNaZalbu"
          >
            Odgovori
          </v-btn>

        </v-expansion-panel-content>


      </v-expansion-panel>
    </v-expansion-panels>

  </v-row>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';


export default {
  data: () => ({
    selektovanaZalba: {},
    selektovanaZalbaID: -1,
    odgovor: "",
  }),

  computed: {
    ...mapGetters({
      neobradjene: "zalbe/getNeobradjeneApoteka",
      nemaZalbi: "zalbe/getNemaZalbiZaApoteke"
    })
  },

   async mounted() {
    this.getNeobradjeneApotekaAction();
  },

  methods: {
    ...mapActions({
      getNeobradjeneApotekaAction: "zalbe/getNeobradjeneApotekaAction",
      sendOdgovorApotekaAction: "zalbe/sendOdgovorApotekaAction"
    }),

    selekcija(id){
      this.selektovanaZalbaID = id;
    },

    odgovoriNaZalbu(){
      this.sendOdgovorApotekaAction({id: this.selektovanaZalbaID, odg: this.odgovor});
      alert("Uspesan odgovor");
    },

  }

}
</script>

<style>

</style>