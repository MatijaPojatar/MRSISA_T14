<template>
  <v-card min-width="700px">
    <v-row no-gutters justify="space-around">
      <v-col md="3">
        <v-text-field
          v-model="pretragaParams.datum"
          label="Datum (1999-02-02)"
        />
      </v-col>
      <v-col md="3">
        <v-text-field
          v-model="pretragaParams.vreme1"
          label="Vreme početka (12:00)"
        />
      </v-col>
      <v-col md="3">
        <v-text-field
          v-model="pretragaParams.vreme2"
          label="Vreme kraja (12:00)"
        />
      </v-col>
      <v-card-actions class="justify-center">
        <v-btn @click="pretrazi" :disabled=isDisabled>Pretraži</v-btn>
      </v-card-actions>
    </v-row>
    <v-card>
      <v-data-table :headers="headeri" :items="podaci">
        <template v-slot:item.odaberi="{ item }">
          <v-btn
            class="mx-2"
            fab
            dark
            small
            color="blue"
            @click="odabrano(item)"
          >
            <v-icon dark>mdi-check</v-icon>
          </v-btn>
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
      { text: "Naziv", value: "naziv", sortable: false },
      { text: "Mesto", value: "grad", sortable: false },
      { text: "Ocena", value: "ocena", sortable: true },
      { text: "Odaberi", value: "odaberi", sortable: false },
    ],
    pretragaParams: {
      datum: "",
      vreme1: "",
      vreme2: "",
    },
    podaci: [],
    apotekaId: "",
    farmaceutId: "",
    korak1: true,
    korak2: false,
    isDisabled: false,
  }),

  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
    }),
  },

  methods: {
    pretrazi() {
      axios.post(`/apoteke/slobodne`, this.pretragaParams).then((response) => {
        this.podaci = response.data;
      });
      this.isDisabled = true;
    },

    odabrano(item) {
      if (this.korak1) {
        (this.headeri = [
          { text: "Ime", value: "ime", sortable: false },
          { text: "Prezime", value: "prezime", sortable: false },
          { text: "Ocena", value: "ocena", sortable: true },
          { text: "Odaberi", value: "odaberi", sortable: false },
        ]),
          (this.apotekaId = item.id);

        axios.get(`/farmaceut/apoteka/${this.apotekaId}`).then((response) => {
          this.podaci = response.data;
        });

        this.korak1 = false;
        this.korak2 = true;
      } else if (this.korak2) {
        this.farmaceutId = item.id;

        let termin = {
          name: "Savetovanje",
          izvestaj: "",
          start: new Date(this.pretragaParams.datum + " " + this.pretragaParams.vreme1),
          end: new Date(this.pretragaParams.datum + " " + this.pretragaParams.vreme2),
          pacijentId: this.korisnik.id,
          apotekaId: this.apotekaId,
          izvrsen: false,
          farmaceutId: this.farmaceutId
        };
        
        axios.put(`/savetovanje/dodaj/${this.farmaceutId}`, termin);
        location.reload();
      }
    },
  },
};
</script>

<style>
</style>