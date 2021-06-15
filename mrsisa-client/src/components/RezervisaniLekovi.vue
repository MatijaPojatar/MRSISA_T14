<template>
  <v-container>
    <v-expansion-panels style="width: 600px">
      <v-expansion-panel v-for="r in rezervacije" :key="r.code" @click = "PanelSelected(r)">>
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
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import Vue from "vue";

export default {
  name: "RezervisaniLekovi",
  
  components: {},

  data: () => ({
        selektovan: "",
        apotekaId: "",
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
      this.selektovan= r.code;
      this.apotekaId=r.apotekaId;
      console.log(this.apotekaId);
    },

    otkazi(){
      Vue.axios.get(`/rezervacija/otkazi/${this.selektovan}`, {params:{apotekaId:this.apotekaId}}).then(response => {
                      console.log(response.data);
                      location.reload();
                      
                   });
      location.reload();
    }
  },
};
</script>

<style>
</style>