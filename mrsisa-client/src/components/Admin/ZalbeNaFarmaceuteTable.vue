<template>
  <v-row>
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


export default {
  data: () => ({
    zalbe: [
      {farmaceutIP: "FARM DARM", pacijentIP: "PAC PAC", id: 1, tekst: "BLABLA"},

    ],
    selektovanaZalba: {},
    selektovanaZalbaID: -1,
    odgovor: "",
  }),

  mounted() {
    this.loadZalbeNaFarmaceute();
  },

  methods: {
    loadZalbeNaFarmaceute(){

    },

    selekcija(id){
      this.selektovanaZalbaID = id;
      alert("SELEKTOVAN"+id);
    },

    odgovoriNaZalbu(){
      alert("ODGOVARAM");
    },

  }

}
</script>

<style>

</style>