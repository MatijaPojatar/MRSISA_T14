<template>
  <v-card min-width="700px">
    <v-text-field
      v-model="search"
      append-icon="mdi-magnify"
      label="Search"
      single-line
      hide-details
    ></v-text-field>
    <v-data-table :headers="headeri" :items="lekovi" :search="search">
      <template v-slot:item.rezervisi="{ item }">
        <v-btn class="mx-2" color="light-green" @click="definisiKolicinu(item)">
          Rezerviši
        </v-btn>
      </template>
    </v-data-table>

    <v-dialog v-model="dialogKolicina" persistent max-width="400">
      <v-card>
        <v-card-title class="headline">
          Definisanje količine i datuma
        </v-card-title>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="definisanaKolicina"
            :counter="10"
            :rules="brojRules"
            label="Količina"
            required
          ></v-text-field>
          <v-menu
            ref="menu"
            v-model="menu"
            :close-on-content-click="false"
            transition="scale-transition"
            offset-y
            min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="definisanRok"
                label="Rok za preuzimanje:"
                prepend-icon="mdi-calendar"
                readonly
                required
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
              ref="picker"
              v-model="definisanRok"
              :min="new Date().toISOString().substr(0, 10)"
              @change="$refs.menu.save(definisanRok)"
            ></v-date-picker>
          </v-menu>
          <v-card-actions>
            <v-btn color="green darken-1" text @click="rezervisi"> 
              Ok
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" text @click="endDialogKolicina">
              Close
            </v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>

    <v-dialog v-model="dialogObavestenje" persistent max-width="290">
      <v-card>
        <v-card-title class="headline"> Obaveštenje </v-card-title>
        <v-card-text>{{ message }}</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="endDialogObavestenje">
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "RezervacijaLeka",

  components: {},

  data: () => ({
    odabrano: true,
    headeri: [
      { text: "Naziv", value: "naziv", sortable: false },
      { text: "Tip", value: "oblikLeka", sortable: false },
      { text: "Ocena", value: "ocena", sortable: false },
      { text: "Apoteka", value: "nazivApoteke", sortable: false },
      { text: "Cena", value: "cena", sortable: false },
      { text: "Rezerviši", value: "rezervisi", sortable: false },
    ],
    search: "",
    lekovi: [],
    selektovanLek: {},
    dialogKolicina: false,
    dialogObavestenje: false,
    definisanaKolicina: "",
    message: "",
    definisanRok: null,
  }),

  async mounted() {
    this.loadLekovi();
  },

  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
    }),
  },

  methods: {
    async loadLekovi() {
      const lekovi = [];
      await axios
        .get(`http://localhost:8080/lekovi/dostupni/`)
        .then((response) => {
          response.data.forEach((element) => {
            lekovi.push({
              id: element.id,
              apotekaId: element.apotekaId,
              naziv: element.naziv,
              nazivApoteke: element.nazivApoteke,
              oblikLeka: element.oblikLeka,
              cena: element.cena,
              ocena: 5,
            });
            this.lekovi = lekovi;
          });
        });
    },

    definisiKolicinu(lek) {
      this.selektovanLek = lek;
      this.dialogKolicina = true;
    },

    rezervisi() {
      axios
        .post(`http://localhost:8080/rezervacija/novaRezervacija`, {
          apotekaId: this.selektovanLek.apotekaId,
          lekId: this.selektovanLek.id,
          kolicina: this.definisanaKolicina,
          pacijentId: this.korisnik.id,
          datum: this.definisanRok,
        })
        .then((response) => {
          this.message = response.data;
          this.dialogObavestenje = true;
        });

      this.endDialogKolicina();
    },

    endDialogKolicina() {
      this.dialogKolicina = false;
      this.definisanaKolicina = "";
      this.definisanRok = null;
    },

    endDialogObavestenje() {
      this.dialogObavestenje = false;
    },
  },
};
</script>

<style>
</style>