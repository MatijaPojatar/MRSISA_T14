<template>
  <v-card min-width="700px">
    <v-card-text v-if="!fromProfilApoteke">
      <v-autocomplete
        v-model="izabranaApotekaId"
        :items="apoteke"
        label="Apoteka"
        item-text="naziv"
        item-value="id"
        required
        @change="updateTable()"
      >
        <template slot="selection" slot-scope="data">
          {{ data.item.naziv }}
        </template>
        <template slot="item" slot-scope="data">
          {{ data.item.naziv }}
        </template>
      </v-autocomplete>
    </v-card-text>
    <v-card>
      <v-data-table :headers="headeri" :items="pregledi">
        <template v-slot:item="red">
          <tr>
            <td>{{ red.item.name }}</td>
            <td>{{ red.item.datum }}</td>
            <td>{{ red.item.pocetak }}</td>
            <td>{{ red.item.kraj }}</td>
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
    izabranaApotekaId: 0,
    headeri: [
      { text: "Naziv", value: "name", sortable: false },
      { text: "Datum", value: "datum", sortable: false },
      { text: "Početak", value: "pocetak", sortable: false },
      { text: "Kraj", value: "kraj", sortable: false },
      { text: "Dermatolog", value: "dermatolog", sortable: false },
      { text: "Ocena", value: "ocena", sortable: false },
      { text: "Cena", value: "cena", sortable: true },
      { text: "Zakaži" },
    ],

    pregledi: [],
  }),

  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
      apoteke: "apoteke/getApoteke",
    }),
  },
  props:{
    apotekaId: Number,
    fromProfilApoteke: Boolean,
  },
  mounted(){
    if (this.fromProfilApoteke){
      this.izabranaApotekaId = this.apotekaId;
    }
    this.updateTable();
  },

  async beforeMount() {
    await this.getApotekeAction();
  },

  methods: {
    ...mapActions({
      getApotekeAction: "apoteke/getApotekeAction",
    }),

    updateTable() {
      if (this.izabranaApotekaId == 1) {
        this.pregledi = [
          {
            name: "Pregled 13",
            datum: "2021-5-20",
            pocetak: "12:00",
            kraj: "12:45",
            ocena: 5,
            apotekaId: 1,
            id: 13,
            dermatolog: "Petar Petrovic",
            dermatologId: 3,
            izvrsen: false,
            cena: 2500.0,
          },
          {
            name: "Pregled 14",
            datum: "2021-05-21",
            pocetak: "13:10",
            kraj: "14:30",
            ocena: 5,
            apotekaId: 1,
            id: 14,
            dermatolog: "Petar Petrovic",
            dermatologId: 3,
            izvrsen: false,
            cena: 3000.0,
          },
        ];
      } else if (this.izabranaApotekaId == 2) {
        this.pregledi = [
          {
            name: "Pregled 15",
            datum: "2021-5-19",
            pocetak: "10:45",
            kraj: "11:30",
            ocena: 5,
            apotekaId: 2,
            id: 15,
            dermatolog: "Luka Lukic",
            dermatologId: 10,
            izvrsen: false,
            cena: 4000.0,
          },
        ];
      } else {
        this.pregledi = [];
      }
    },

    zakazi(item) {
      this.pregledi = [{
            name: "Pregled 14",
            datum: "2021-05-21",
            pocetak: "13:10",
            kraj: "14:30",
            ocena: 5,
            apotekaId: 1,
            id: 14,
            dermatolog: "Petar Petrovic",
            dermatologId: 3,
            izvrsen: false,
            cena: 3000.0,
          }]
      let pregled = {
        name: item.name,
        izvestaj: '',
        start: [
            2021,
            5,
            20,
            12,
            0
        ],
        end: [
            2021,
            5,
            20,
            12,
            45
        ],
        pacijentId: this.korisnik.id,
        dermatologId: item.dermatologId,
        apotekaId: item.apotekaId,
        izvrsen: false
      };
      axios.put(`/pregled/zakazi`, pregled);
    },
  },
};
</script>

<style>
</style>