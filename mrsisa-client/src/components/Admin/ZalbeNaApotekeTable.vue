<template>
  <v-row>
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
      {apotekaNaziv: "Apotekk", pacijentIP: "PAC PAC", id: 1, tekst: "BLABLA"},

    ],
    selektovanaZalba: {},
    selektovanaZalbaID: -1,
    odgovor: "",
  }),

  mounted() {
    this.loadZalbeNaApoteke();
  },

  methods: {
    loadZalbeNaApoteke(){

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