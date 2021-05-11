<template>
  <v-row>
    <div v-if="nemaZalbi">
      <v-card>
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
      alert("Uspesan odgovor");
    },

  }

}
</script>

<style>

</style>