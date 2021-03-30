<template>
  <div>
    <h2>LISTA LEKOVA:</h2>
    <table>
        <thead>
          <tr>
            <th>Sifra</th>
            <th>Naziv</th>
            <th>Sastav</th>
            <th>Proizvodjac</th>
            <th>Napomena</th>
            <th>RezimIzdavanja</th>
            <th>OblikLeka</th>
            <th>VrstaLeka</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="lek in lekovi" v-bind:key="lek.sifra">
            <td>{{lek.sifra}}</td>
            <td>{{lek.naziv}}</td>
            <td>{{lek.sastav}}</td>
            <td>{{lek.proizvodjac}}</td>
            <td>{{lek.napomena}}</td>
            <td>{{lek.rezimIzdavanja}}</td>
            <td>{{lek.oblikLeka}}</td>
            <td>{{lek.vrstaLeka}}</td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script>

import ApotekaService from '../services/ApotekaService'

export default {
  name: "ListLekoviApoteka",

  data() {
    return {
      lekovi: [],
      INSTRUCTOR: "apoteka/lekovi"
    }
  },

  methods: {
    refreshLekovi() {
      ApotekaService.preuzmiLekove(this.INSTRUCTOR) //HARDCODED
        .then(response => {
          this.lekovi = response.data;
        });
    }
  },

  created() {
    this.refreshLekovi();
  }
}
</script>

<style>

</style>