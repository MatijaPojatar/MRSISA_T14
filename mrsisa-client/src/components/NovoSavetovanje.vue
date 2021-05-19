<template>
  <v-card min-width="700px">
    <v-text-field
      v-model="search"
      append-icon="mdi-magnify"
      label="2001-01-01"
      single-line
      hide-details
    ></v-text-field>
    <v-card>
      <v-data-table :headers="headeri" :items="apoteke">
        <template v-slot:item="red">
          <tr>
            <td>{{ red.item.naziv }}</td>
            <td>{{ red.item.mesto }}</td>
            <td>5</td>
            <td>2500</td>
            <td>
              <v-btn
                class="mx-2"
                fab
                dark
                small
                color="blue"
                @click="odaberi()"
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
import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "NovoSavetovanje",

  components: {},

  data: () => ({
    headeri: [
      { text: "Naziv", value: "name", sortable: false },
      { text: "Mesto", value: "mesto", sortable: false },
      { text: "Ocena", value: "ocena", sortable: true },
      { text: "Cena", value: "cena", sortable: true },
      { text: "Odaberi" },
    ],
    apoteke: [
      {
        id: 1,
        naziv: "Apoteka 1",
        adresa: "Adresa apoteke 1",
        mesto: "Novi Sad",
        drzava: "Srbija",
        opis: "Test",
      },
    ],
    savetovanja: [],
  }),

  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
    }),
  },

  methods: {
    odaberi() {
      this.headeri = [
        { text: "Ime", value: "ime", sortable: false },
        { text: "Prezime", value: "prezime", sortable: false },
        { text: "Ocena", value: "ocena", sortable: true },
        { text: "Cena", value: "cena", sortable: true },
        { text: "Odaberi" },
      ],
      this.apoteke = [
          {
            id: 1,
            apotekaId: 1,
            naziv: "Pera",
            mesto: "Peric",
            mail: "perap@gmail.com",
            adresa: "Adresa 1",
            grad: "Novi Sad",
            drzava: "Srbija",
            brojTelefona: "0651234567",
            pocetakRadnogVremenaStr: "08:00",
            krajRadnogVremenaStr: "16:00",
            pol: "MUSKI",
            datumRodjenja: [1990, 1, 1],
            pocetakRadnogVremena: [8, 0],
            krajRadnogVremena: [16, 0],
            promenjenaLozinka: true,
            enabled: true,
          },
          {
            id: 6,
            apotekaId: 1,
            naziv: "Maja",
            mesto: "Majic",
            mail: "maja93@gmail.com",
            adresa: "Adresa 2",
            grad: "Novi Sad",
            drzava: "Srbija",
            brojTelefona: "0651234567",
            pocetakRadnogVremenaStr: "08:00",
            krajRadnogVremenaStr: "16:00",
            pol: "ZENSKI",
            datumRodjenja: [1993, 1, 1],
            pocetakRadnogVremena: [8, 0],
            krajRadnogVremena: [16, 0],
            promenjenaLozinka: true,
            enabled: true,
          },
        ];
    },

    zakazi(item) {
      this.pregledi = [
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
      let pregled = {
        name: item.name,
        izvestaj: "",
        start: [2021, 5, 20, 12, 0],
        end: [2021, 5, 20, 12, 45],
        pacijentId: this.korisnik.id,
        dermatologId: item.dermatologId,
        apotekaId: item.apotekaId,
        izvrsen: false,
      };
      axios.put(`http://localhost:8080/pregled/zakazi`, pregled);
    },
  },
};
</script>

<style>
</style>