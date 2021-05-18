<template>
  <v-card min-width="700px">
    <v-card-text>
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
        <template v-slot:item.actions="{ item }">
          <v-icon small class="mr-2" @click="showAccount(item)">
            mdi-account
          </v-icon>
        </template>
      </v-data-table>
    </v-card>
    <v-dialog
      v-model="dialog"
      persistent
      scrollable
      retain-focus
      max-width="960"
    >
      <v-card>
        <v-card-title class="headline"> Nalog pacijenta </v-card-title>
        <v-card-text>
          <AccountView
            :user="selectedUser"
            :farmaceut="false"
            :editable="false"
            :adminView="false"
            :key="componentKey"
          />
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="endDialog"> Ok </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

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
    ],

    pregledi: [],
  }),

  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
      apoteke: "apoteke/getApoteke",
    }),
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
            izvrsen: false,
            cena: 4000.0,
          },
        ];
      } else {
        this.pregledi = [];
      }
    },
  },
};
</script>

<style>
</style>