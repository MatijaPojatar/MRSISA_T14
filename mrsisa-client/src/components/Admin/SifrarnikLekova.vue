<template>
  <div>
    <v-card width="800">
      <v-card-title>Šifrarnik lekova</v-card-title>
      <v-card-text>
        
      <v-text-field
      v-model="search"
      append-icon="mdi-magnify"
      label="Search"
      single-line
      hide-details
    ></v-text-field>

        <v-data-table
      :search="search"
      :headers="headers"
      :items="lekovi"
    >
      <template v-slot:item.actions="{ item }">
        <v-btn
          @click="dostupnostLeka(item.id)"
          class="mx-2"
          small
          dark
          color="green"
        >
          Dostupnost
        </v-btn>
      </template>

      <template v-slot:item.actions2="{ item }">
        <v-btn
        @click="specifikacijaLeka(item)"
          class="mx-2"
          small
          dark
          color="indigo">
          Specifikacija
        </v-btn>
      </template>
    </v-data-table>
      </v-card-text>
    </v-card>
    
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex';

export default {

  data: () => ({
    search: "",
    headers: [
      { text: "Naziv leka", value: "naziv"},
      { text: "Vrsta leka", value: "vrstaLeka"},
      { text: "Ocena leka", value: "ocenaLeka"},
      { text: "Dostupnost leka", value: "actions", sortable: false},
      { text: "Specifikacija leka", value: "actions2", sortable: false}

    ]
  }),

  computed: {
    ...mapGetters({
      lekovi: "lekovi/getLekoviSifrarnik",

    })
  },

  mounted() {
    this.getLekoviSifrarnikAction();
  },

  methods: {
    ...mapActions({
      getLekoviSifrarnikAction: "lekovi/getLekoviSifrarnikAction",
    }),

    dostupnostLeka(id){
      //pozvati axios
      alert("Pozvati axios za id"+ id);
    },

    specifikacijaLeka(lek){
      alert("Pozvati axios za id"+ lek.id);
    }
  }
}
</script>

<style>

</style>