<template>
    <div>
        <h2>Apoteke:</h2>
        <div class="container">
            <form @submit="validateAndSubmit">
                <fieldset class="form-group">
                    <label>Naziv: </label>
                    <input type="text" v-model="naziv">
                </fieldset>
                <fieldset class="form-group">
                    <label>Mesto: </label>
                    <input type="text" v-model="grad" >
                </fieldset>
                <button class="btn btn-success" type="submit">Nađi</button>
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Naziv</th>
                    <th>Adresa</th>
                    <th>Grad</th>
                    <th>Država</th>
                    <th>Ocena</th>
                </tr>
            </thead>
            <tbody>
              <tr v-for="apoteka in apoteke" v-bind:key="apoteka.id">
                    <td>{{apoteka.naziv}}</td>
                    <td>{{apoteka.adresa}}</td>
                    <td>{{apoteka.grad}}</td>
                    <td>{{apoteka.drzava}}</td>
                    <td>{{apoteka.ocena}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

import ApotekaService from '../services/ApotekaService'

export default {
  name: "ListApoteke",

  data() {
    return {
        naziv: "",
        grad: "",
        apoteke: [],
        INSTRUCTOR: "apoteke"
    }
  },

  methods: {
    refreshApoteke() {
        ApotekaService.pronadjiApoteke("", "")
        .then(response => {
            this.apoteke = response.data;
        });
    },

    validateAndSubmit(e) {
        e.preventDefault();
        console.log({
            naziv: this.naziv,
            grad: this.grad
        })
        ApotekaService.pronadjiApoteke({
            naziv: this.naziv,
            grad: this.grad
        }).then(response => {
            this.apoteke = response.data;
        });
    }
  },

  created() {
      this.refreshApoteke();
  }
}
</script>

<style>
</style>