<template>
  <v-container>
    <v-expansion-panels style="width: 600px">
      <v-expansion-panel v-for="p in pregledi" :key="p.id" @click="selektujP(p)">
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
          <v-btn text @click="otkaziPregled">Otkaži</v-btn>
        </v-card-actions>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
    <br/>
    <v-expansion-panels style="width: 600px">
      <v-expansion-panel v-for="s in savetovanja" :key="s.id" @click="selektujS(s)">
        <v-expansion-panel-header>
          {{ s.name }}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <div>Početak: {{ s.start }}</div>
          <v-divider />
          <div>Kraj: {{ s.end }}</div>
          <v-divider />
        <v-card-actions class="justify-center">
          <v-btn text @click="otkaziSavetovanje">Otkaži</v-btn>
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
        <v-card-text>Nemoguće je otkazati termin koji počinje za manje od 24h.</v-card-text>
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
  name: "ZakazanePosete",
  
  components: {},

  data: () => ({
    pregledi: [],
    savetovanja: [],
    selektovanP: {},
    selektovanoS: {},
    dialog: false
  }),

  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
    }),
  },

  mounted() {
    this.loadPregledi();
    this.loadSavetovanja();
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

    loadSavetovanja() {
      axios.get(`/savetovanje/pacijent/${this.korisnik.id}`).then(response => {
        const savetovanja = []
        response.data.forEach(element => {
        savetovanja.push({
          id: element.id,
          name: element.name,
          start: element.start[0].toString()+"-"+element.start[1].toString()+"-"+element.start[2].toString()+" "+element.start[3].toString()+":"+element.start[4].toString(),
          end: element.end[0].toString()+"-"+element.end[1].toString()+"-"+element.end[2].toString()+" "+element.end[3].toString()+":"+element.end[4].toString(),
          cena: element.cena,
        })
        this.savetovanja = savetovanja
        });
      });
    },

    selektujP(p){
      this.selektovanP = p;
    },

    selektujS(s){
      this.selektovanoS = s;
    },

    otkaziPregled(){
      var oneDay = new Date().getTime() + (24 * 60 * 60 * 1000)
      if (oneDay > Date.parse(this.selektovanP.start)) {
      this.dialog = true
      } else {
        axios.put(`/pregled/otkazi/${this.selektovanP.id}`);
        location.reload();
      }
    },

    otkaziSavetovanje(){
      var oneDay = new Date().getTime() + (24 * 60 * 60 * 1000)
      if (oneDay > Date.parse(this.selektovanoS.start)) {
      this.dialog = true
      } else {
        axios.delete(`/savetovanje/otkazi/${this.selektovanoS.id}`);
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