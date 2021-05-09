<template>
  <v-row>
    <v-expansion-panels>
      <v-expansion-panel
        v-for="zalba in zalbe"
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
      {dermatologIP: "Deermatolog gg", pacijentIP: "PAC PAC", id: 1, tekst: "BLABLA"},

    ],
    selektovanaZalba: {},
    selektovanaZalbaID: -1,
    odgovor: "",
  }),

  mounted() {
    this.loadZalbeNaDermatologe();
  },

  methods: {
    loadZalbeNaDermatologe(){

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