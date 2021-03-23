<template>
  <div>
    <h2>LISTA REZERVACIJA:</h2>
    <table>
        <thead>
          <tr>
            <th>Sifra</th>
            <th>Datum</th>
            <th>Sifra leka</th>
            <th>Naziv leka</th>
            <th>Sastav leka</th>
            <th>Proizvodjac leka</th>
            <th>Napomena</th>
            <th>RezimIzdavanja leka</th>
            <th>OblikLeka</th>
            <th>VrstaLeka</th>
            <th>Sifra apoteke</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="rez in rezervacije" v-bind:key="rez.id">
            <td>{{rez.id}}</td>
            <td>{{rez.datum}}</td>
            <td>{{rez.lek.sifra}}</td>
            <td>{{rez.lek.naziv}}</td>
            <td>{{rez.lek.sastav}}</td>
            <td>{{rez.lek.proizvodjac}}</td>
            <td>{{rez.lek.napomena}}</td>
            <td>{{rez.lek.rezimIzdavanja}}</td>
            <td>{{rez.lek.oblikLeka}}</td>
            <td>{{rez.lek.vrstaLeka}}</td>
            <td>{{rez.apotekaId}}</td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script>

import RezervacijaService from '../services/RezervacijaService'

export default {
  name: "ListRez",

  data() {
    return {
      rezervacije: [],
      INSTRUCTOR: "rezervacija"
    }
  },

  methods: {
    refreshRez() {
      RezervacijaService.retrieveRez(this.INSTRUCTOR) //HARDCODED
        .then(response => {
          this.rezervacije = response.data;
        });
    }
  },

  created() {
    this.refreshRez();
  }
}
</script>

<style>

</style>