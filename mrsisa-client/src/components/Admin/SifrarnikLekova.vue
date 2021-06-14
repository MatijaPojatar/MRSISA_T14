<template>
  <div>
    <v-card >
      <v-card-title>Šifrarnik lekova</v-card-title>
      <v-card-text>
        
      <v-text-field
      v-model="search"
      append-icon="mdi-magnify"
      label="Search"
      single-line
      hide-details
    ></v-text-field>

        <v-data-table
      :search="search"
      :headers="headers"
      :items="lekovi"
    >
      <template v-slot:item.actions="{ item }">
        <v-btn
          @click="dostupnostLeka(item.id)"
          class="mx-2"
          small
          dark
          color="green"
        >
          Dostupnost
        </v-btn>
      </template>

      <template v-slot:item.actions2="{ item }">
        <v-btn
        @click="specifikacijaLeka(item)"
          class="mx-2"
          small
          dark
          color="indigo">
          Specifikacija
        </v-btn>
      </template>
    </v-data-table>
      </v-card-text>
    </v-card>

    <v-dialog
      v-model="specDialog"
      transition="dialog-top-transition"
      max-width="600"
      >
      <v-card>
        <v-card-title>Specifikacija leka</v-card-title>
        
            <v-card-text>
              <v-textarea
              solo
              readonly
              v-model="currSpec">
              </v-textarea>
              
            </v-card-text>
            <v-card-actions class="justify-end">
              <v-btn
                text
                @click="closeDialog"
              >Close</v-btn>
            </v-card-actions>
          </v-card>

      </v-dialog>

      <v-dialog
       v-model="showDostupnost"
      transition="dialog-top-transition"
      max-width="600">
        <v-card class="my-2">
        <v-card-title>Snabdevene apoteke:</v-card-title>

        <v-card-text v-if="!postojeApoteke"
          >Ne postoje apoteke koje nude lekove sa recepta
        </v-card-text>

        <v-data-table
          v-if="postojeApoteke"
          :headers="headersApoteke"
          :items="this.dostupnost"
        >
        </v-data-table>
      </v-card>
      </v-dialog>
    
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex';

export default {

  data: () => ({

    headersApoteke: [
      { text: "Apoteka", value: "nazivApoteke" },
      { text: "Cena", value: "cenaLekova" },
    ],

    search: "",

    currSpec: "",

    showDostupnost: false,

    specDialog: false,

    headers: [
      { text: "Naziv leka", value: "naziv"},
      { text: "Vrsta leka", value: "vrstaLeka"},
      { text: "Ocena leka", value: "ocenaLeka"},
      { text: "Dostupnost leka", value: "actions", sortable: false},
      { text: "Specifikacija leka", value: "actions2", sortable: false}

    ]
  }),

  computed: {
    ...mapGetters({
      lekovi: "lekovi/getLekoviSifrarnik",
      dostupnost: "lekovi/getDostupnostLeka"
    }),

    postojeApoteke() {

      return this.dostupnost.length != 0;
    },
  },

  mounted() {
    this.getLekoviSifrarnikAction();
  },

  methods: {
    ...mapActions({
      getLekoviSifrarnikAction: "lekovi/getLekoviSifrarnikAction",
      getDostupnostLekaAction: "lekovi/getDostupnostLekaAction"
    }),

    async dostupnostLeka(id){
      //pozvati axios
      await this.getDostupnostLekaAction(id); //napunio dostupnost
      //otvoriti dijalog
      this.showDostupnost = true;

    },

    closeDialog(){
      this.specDialog = false;
    },

    specifikacijaLeka(lek){

      let specifikacija="Lek: " + lek.naziv + "\nProizvođač: " + lek.proizvodjac +"\nSastav: " + lek.sastav +"\nNapomena: " + 
      lek.napomena + "\nZamenski lekovi:  ";
      for(const zamenski in lek.naziviZamenskih){
        specifikacija += zamenski +", ";
      }
      specifikacija = specifikacija.substring(0, specifikacija.length -2);

      this.currSpec = specifikacija;
      this.specDialog = true;
    }
  }
}
</script>

<style>

</style>