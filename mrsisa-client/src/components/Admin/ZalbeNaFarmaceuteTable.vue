<template>
  <v-row>
    <v-expansion-panels>
      <v-expansion-panel
        v-for="zalba in neobradjene"
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
      neobradjene: "zalbe/getNeobradjeneFarmaceut"
    })
  },

  async mounted() {
    this.getNeobradjeneFarmaceutAction();
  },

  methods: {
    ...mapActions({
      getNeobradjeneFarmaceutAction: "zalbe/getNeobradjeneFarmaceutAction",
      sendOdgovorFarmaceutAction: "zalbe/sendOdgovorFarmaceutAction"
    }),

    selekcija(id){
      this.selektovanaZalbaID = id;
    },

    odgovoriNaZalbu(){
      this.sendOdgovorFarmaceutAction({id: this.selektovanaZalbaID, odg: this.odgovor});
      alert("Uspesan odgovor");
    },

  }

}
</script>

<style>

</style>