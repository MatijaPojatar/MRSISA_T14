<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="290">
      <v-card>
        <v-card-title class="headline"> Obaveštenje </v-card-title>
        <v-card-text>Uspešna kupovina!</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="endDialog"> Ok </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <div v-if="upload">
      <v-card>
        <v-card-title>Izaberite QR kod sa E-receptom:</v-card-title>

        <v-card-text>
          <v-file-input
            v-model="file"
            accept="image/*"
            label="File input"
          ></v-file-input>
        </v-card-text>

        <v-card-actions>
          <v-btn @click="submitFile()">Pošalji</v-btn>
        </v-card-actions>
      </v-card>
    </div>

    <div v-if="!upload">
      <v-card v-if="odbijen">
        <v-card-text>
          ERecept je odbijen! Mogući razlozi: -Pacijent je alergičan na neki od
          lekova -Ne postoje lekovi na ereceptu
        </v-card-text>
      </v-card>

      <v-card class="my-2">
        <v-card-title>Lekovi iz recepta:</v-card-title>

        <v-data-table
          :headers="headersLekovi"
          :items="this.erecept.lekoviErecepta"
        >
        </v-data-table>
      </v-card>

      <v-card class="my-2">
        <v-card-title>Snabdevene apoteke:</v-card-title>

        <v-card-text v-if="!postojeApoteke"
          >Ne postoje apoteke koje nude lekove sa recepta
        </v-card-text>

        <v-data-table
          v-if="postojeApoteke"
          :headers="headersApoteke"
          :items="this.snabdeveneApoteke"
        >
          <template v-slot:item.actions="{ item }">
            <v-btn
              @click="odaberiApoteku(item.idApoteke)"
              class="mx-2"
              fab
              small
              dark
              color="indigo"
            >
              Kupi
            </v-btn>
          </template>
        </v-data-table>
      </v-card>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik",
      erecept: "erecepti/getCurrentErecept",
      snabdeveneApoteke: "erecepti/getSnabdeveneApoteke",
    }),
    odbijen() {
      if (!this.erecept) {
        return false;
      }

      return this.erecept.status == "ODBIJEN";
    },
    postojeApoteke() {

      return this.snabdeveneApoteke.length != 0;
    },
  },

  data: () => ({
    file: null,
    upload: true,
    dialog: false,
    headersLekovi: [
      { text: "Naziv leka", value: "nazivLeka" },
      { text: "Količina", value: "kolicina" },
    ],
    headersApoteke: [
      { text: "Apoteka", value: "nazivApoteke" },
      { text: "Cena", value: "cenaLekova" },
      { text: "Odaberi", value: "actions", sortable: false },
    ],
  }),

  methods: {
    ...mapActions({
      sendFileAction: "erecepti/sendFileAction",
      getSnabdeveneApotekeAction: "erecepti/getSnabdeveneApotekeAction",
      kupiLekoveAction: "erecepti/kupiLekoveAction",
    }),

    async submitFile() {
      let formData = new FormData();
      formData.append("file", this.file);
      try {
        await this.sendFileAction({ formData: formData, id: this.user.id });
        this.upload = false;
      } catch (error) {
        console.log(error);
        alert("Greska pri upload-u fajla");
      }
      try {
        await this.getSnabdeveneApotekeAction({erecept: this.erecept, idPac: this.user.id});
      } catch (error) {
        console.log(error);
        alert("Greska pri dobavljanju snabdevenih apoteka");
      }
    },

    async odaberiApoteku(idApoteke) {
      console.log(idApoteke);
      try {
        await this.kupiLekoveAction({ id: idApoteke, erecept: this.erecept });
        this.dialog = true;
      } catch (error) {
        console.log(error);
        alert("Greska pri kupovini lekova");
      }
    },

    endDialog() {
      this.dialog = false;
      this.upload = true;
    },
  },
};
</script>

<style>
</style>