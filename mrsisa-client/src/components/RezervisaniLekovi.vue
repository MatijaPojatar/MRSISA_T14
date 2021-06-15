<template>
  <v-container>
    <v-expansion-panels style="width: 600px">
      <v-expansion-panel v-for="r in rezervacije" :key="r.code" @click="PanelSelected(r)">
        <v-expansion-panel-header>
          Rezervacija - {{ r.code }}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <div>Naziv leka: {{ r.lekNaziv }}</div>
          <v-divider />
          <div>Količina: {{ r.kolicina }}</div>
          <v-divider />
          <div>Naziv apoteke: {{ r.apotekaNaziv }}</div>
          <v-divider />
          <div>Rok preuzimanja: {{ r.datum[0].toString() + "-" + r.datum[1].toString() + "-" + r.datum[2].toString() }}</div>
          <v-divider />
        <v-card-actions class="justify-center">
          <v-btn text @click="otkazi">Otkaži</v-btn>
        </v-card-actions>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
    <v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Greška
        </v-card-title>
        <v-card-text>Nemoguće je otkazati rezervaciju leka čiji rok preuzimanja je za manje od 24h.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import axios from "axios";

export default {
  name: "RezervisaniLekovi",
  
  components: {},

  data: () => ({
    selektovan: {},
    apotekaId: "",
    dialog: false
  }),

  computed: {
    ...mapGetters({
      rezervacije: "rezervacije/getRezervacije",
      korisnik: "korisnici/getKorisnik",
    }),
  },

  async mounted() {
    await this.getRezervacijeAction(this.korisnik.id);
  },

  methods: {
    ...mapActions({
      getRezervacijeAction: "rezervacije/getRezervacijeAction",
    }),

    PanelSelected(r){
      this.selektovan = r;
      this.apotekaId = r.apotekaId;
    },

    otkazi(){
      var oneDay = new Date().getTime() + (24 * 60 * 60 * 1000)
      if (oneDay > Date.parse(this.selektovan.datum)) {
        this.dialog = true
      } else {
        axios.get(`/rezervacija/otkazi/${this.selektovan.code}`, {params:{apotekaId:this.apotekaId}});
        location.reload();
      }
    },

    endDialog(){
      this.dialog = false;
    },
  },
};
</script>

<style>
</style>