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
      max-width="400"
    >
     <v-card>
       <v-card-title>Lekovi poručeni E-receptom</v-card-title>

       <v-data-table
        :headers="headersLekovi"
        :items="this.currErecept.lekoviErecepta">
       </v-data-table>

       <v-card-actions>
         <v-btn
         @click="closeLekovi"
         >OK</v-btn>
       </v-card-actions>
     </v-card>
  </v-dialog>
</div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'

export default {
  data: () => ({
    showLekovi: false,
    currErecept: {},
    headersLekovi: [
      { text: 'Naziv leka', value: 'nazivLeka'},
      { text: 'Količina', value: 'kolicina'}
    ],
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

    prikazErecepta(erecept){
      console.log("ODABRANI ERECEPT")
      console.log(erecept);
      this.currErecept = erecept;
      this.showLekovi = true;
    },

    closeLekovi(){
      this.showLekovi = false;
    }

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