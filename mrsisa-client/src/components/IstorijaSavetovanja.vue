<template>
  <v-card>
    <v-data-table
      :headers="headers"
      :items="savetovanja"
      :sort-desc="[false, true]"
      multi-sort
      class="elevation-1"
    >
      <template v-slot:item.datum="{ item }">
        <span>{{ item.start[0] + "-" + item.start[1] + "-" + item.start[2] }}</span>
      </template>
      <template v-slot:item.trajanje="{ item }">
        <span>{{ (item.end[3] * 60 + item.end[4]) - (item.start[3] * 60 + item.start[4]) }}</span>
      </template>
      <template v-slot:item.izvestaj="{ item }">
        <v-btn class="mx-2" dark color="blue" @click="showIzvestaj(item)">
          Otvori
        </v-btn>
      </template>
    </v-data-table>
    <v-dialog
      v-model="dialog"
      persistent
      max-width="600"
    >
      <v-card>
        <v-card-title class="headline">
          Preporučeni lekovi
        </v-card-title>
        <template>
          <v-simple-table dense>
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">Naziv</th>
                  <th class="text-left">Terapija</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in selectedPreporuceni" :key="item.id">
                  <td>{{ item.naziv }}</td>
                  <td>{{ item.terapija }}</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </template>
      </v-card>
      <v-card-actions class="justify-center">
          <v-btn
            class="white"
            text
            @click="endDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
    </v-dialog>
  </v-card>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";

export default {
  components: {},

  data: () => ({
    headers: [
      { text: "Savetovanje", value: "name", sortable: false },
      { text: "Datum", value: "datum" },
      { text: "Trajanje (min)", value: "trajanje" },
      { text: "Izveštaj", value: "izvestaj" },
    ],
    savetovanja: [],
    selectedPreporuceni: [],
    dialog: false,
  }),

  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
    }),
  },

  mounted() {
    this.loadSavetovanja();
  },

  methods: {
    loadSavetovanja() {
      axios.get(`/savetovanje/istorija/pacijent/${this.korisnik.id}`).then((response) => {
        this.savetovanja = response.data;
      });
    },

    showIzvestaj(item) {
      axios.get(`/savetovanje/preporuceni_lekovi/${item.id}`).then((response) => {
        const lekovi = [];
        response.data.forEach((element) => {
          lekovi.push({
            terapija: element.terapija,
            naziv: element.nazivLeka,
            id: element.id,
          });
          this.selectedPreporuceni = lekovi;
        });
      });
      this.dialog = true;
    },

    endDialog(){
      this.dialog = false;
    }
  },
};
</script>