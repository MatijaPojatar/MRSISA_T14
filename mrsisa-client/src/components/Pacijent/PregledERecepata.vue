<template>
<div>
  <v-data-table
  :headers="headers"
  :items="erecepti"
  sort-by="datumIzdavanja"
  >
    <template v-slot:item.datumIzdavanja="{ item }">
      <span>{{ new Date(item.datumIzdavanja).toLocaleDateString() }}</span>
    </template>

    <template v-slot:item.actions="{ item }">
      <v-btn
      class ="mx-2"
      fab
      small
      dark
      color="indigo">
        <v-icon
        small
        @click="prikazErecepta(item)"
      >
        mdi-pill
      </v-icon>
      </v-btn>
      
    </template>
  </v-data-table>
  <v-dialog
      v-model="showLekovi"
      persistent
      max-width="290"
    >
     
  </v-dialog>
</div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'

export default {
  data: () => ({
    headers: [
      { text: 'ID', value: 'id'},
      { text: 'Datum Izdavanja', value: 'datumIzdavanja'}, //ovde prepraviti?
      { text: 'Status', value: 'status'},
      { text: 'Lekovi', value: 'actions', sortable: false},
      ]
  }),

  methods: {
    ...mapActions({
      getEreceptiPacijentaAction: "erecepti/getEreceptiPacijentaAction"
    }),




  },
  computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik",
      erecepti: "erecepti/getEreceptiPacijenta",

    }),
  },

  async mounted() {
    await this.getEreceptiPacijentaAction(this.user.id);
  }

}
</script>

<style>

</style>