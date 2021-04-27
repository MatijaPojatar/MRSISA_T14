<template>
  <div>
    <v-card>
      <v-data-table
      :items="zalbe"
      :headers="headers"
      >
        <template v-slot:item.actions="{ item }">
          <v-btn small @click="openZalbaDialog(item)">Otvori</v-btn>
        </template>
      </v-data-table>
    </v-card>

    <v-dialog v-model="zalbaDialog">
      <v-card>
        <v-card-title>Zalba na apoteku</v-card-title>

        <v-card-text>
          <v-textarea v-model="activeZalba.tekst" readonly></v-textarea>
          <v-textarea v-model="activeZalba.odgovor"></v-textarea>
        </v-card-text>

        <v-card-actions>
          <v-btn color="error" @click="closeZalbaDialog">Zatvori</v-btn>
          <v-btn color="success" @click="sendOdgovor">Posalji</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  data: () => ({
    headers: [
      { text: 'Apoteka', value: 'naziv' }
    ],

    zalbaDialog: false,
    activeZalba: null
  }),

  async beforeMount() {
    await this.getZalbeAction();
  },

  methods: {
    ...mapActions({
      getZalbeAction: "zalbe/getNeobradjeneApotekaAction",
      sendOdgovorAction: "zalbe/sendOdgovorApotekaAction"
    }),

    openZalbaDialog(item) {
      this.activeZalba = item;
      this.zalbaDialog = true;
    },

    closeZalbaDialog() {
      this.zalbaDialog = false;
    },

    async sendOdgovor() {
      await this.sendOdgovorAction(this.activeZalba.odgovor);
      alert("Uspesno ste odgovorili na zalbu");
    }
  },

  computed: {
    ...mapGetters({
      zalbe: "zalbe/getNeobradjeneApoteka"
    })
  }
}
</script>

<style>

</style>