<template>
  <v-container>
    <v-expansion-panels style="width: 600px">
      <v-expansion-panel v-for="p in pregledi" :key="p.id" @click="PanelSelected(p)">
        <v-expansion-panel-header>
          {{ p.name }}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <div>Početak: {{ p.start }}</div>
          <v-divider />
          <div>Kraj: {{ p.end }}</div>
          <v-divider />
          <div>Cena: {{ p.cena }}</div>
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
import axios from "axios";

export default {
  name: "ZakazanePosete",
  
  components: {},

  data: () => ({
    pregledi: [],
    selektovan: "",
    apotekaId: "",
  }),

  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
    }),
  },

  mounted() {
    this.loadPregledi();
  },

  methods: {
    ...mapActions({
    }),

    loadPregledi() {
      axios.get(`/pregled/pacijent/${this.korisnik.id}`).then(response => {
        const pregledi = []
        response.data.forEach(element => {
        pregledi.push({
          id: element.id,
          name: element.name,
          start: element.start[0].toString()+"-"+element.start[1].toString()+"-"+element.start[2].toString()+" "+element.start[3].toString()+":"+element.start[4].toString(),
          end: element.end[0].toString()+"-"+element.end[1].toString()+"-"+element.end[2].toString()+" "+element.end[3].toString()+":"+element.end[4].toString(),
          cena: element.cena,
        })
        this.pregledi = pregledi
        });
      });
    },

    PanelSelected(p){
      this.selektovanId = p.id;
      this.apotekaId = p.apotekaId;
      console.log(this.apotekaId);
    },

    otkazi(){
      axios.get(`/pregled/otkazi/${this.selektovanId}`, {params:{apotekaId:this.apotekaId}});
      location.reload();
    }
  },
};
</script>

<style>
</style>