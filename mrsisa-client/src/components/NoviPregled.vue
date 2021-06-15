<template>
  <v-card min-width="700px">
    <v-card>
      <v-data-table :headers="headeri" :items="pregledi">
        <template v-slot:item="red">
          <tr>
            <td>{{ red.item.name }}</td>
            <td>{{ red.item.start }}</td>
            <td>{{ red.item.end }}</td>
            <td>{{ red.item.dermatolog }}</td>
            <td>{{ red.item.ocena }}</td>
            <td>{{ red.item.cena }}</td>
            <td>
              <v-btn
                class="mx-2"
                fab
                dark
                small
                color="blue"
                @click="zakazi(red.item)"
              >
                <v-icon dark>mdi-check</v-icon>
              </v-btn>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-card>
  </v-card>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "NoviPregled",

  components: {},

  data: () => ({
    headeri: [
      { text: "Naziv", value: "name", sortable: false },
      { text: "Početak", value: "start", sortable: false },
      { text: "Kraj", value: "end", sortable: false },
      { text: "Dermatolog", value: "dermatolog", sortable: false },
      { text: "Ocena", value: "ocena", sortable: true },
      { text: "Cena", value: "cena", sortable: true },
      { text: "Zakaži" },
    ],

    pregledi: [],
  }),

  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
    }),
  },

  props:{
    apotekaId: Number
  },
  
  mounted(){
    this.loadPregledi();
  },

  methods: {
    ...mapActions({
    }),

    loadPregledi() {
      axios.get(`/pregled/all/slobodni/${this.apotekaId}`).then(response => {
        const pregledi = []
        response.data.forEach(element => {
        pregledi.push({
          id: element.id,
          name: element.name,
          pacijent: element.pacijentId,
          start: element.start[0].toString()+"-"+element.start[1].toString()+"-"+element.start[2].toString()+" "+element.start[3].toString()+":"+element.start[4].toString(),
          end: element.end[0].toString()+"-"+element.end[1].toString()+"-"+element.end[2].toString()+" "+element.end[3].toString()+":"+element.end[4].toString(),
          izvrsen: element.izvrsen,
          izvestaj: element.izvestaj,
          dermatologId: element.dermatologId,
          dermatolog: element.dermatolog,
          ocena: element.ocena,
          cena: element.cena,
        })
        this.pregledi = pregledi
        });
      });
    },

    zakazi(pregled) {
      axios.put(`/pregled/zakazi/${this.korisnik.id}/${pregled.id}`);
      location.reload();
    }
  },
};
</script>

<style>
</style>