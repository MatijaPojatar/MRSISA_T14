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
    </v-data-table>
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
    ],
    savetovanja: [],
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
  },
};
</script>