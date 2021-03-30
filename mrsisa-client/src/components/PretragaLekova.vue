<template>
  <div>
    <h3>Pretraga</h3>
    <div class="container">
      <form @submit="validateAndSubmit">
        <fieldset class="form-group">
          <label>Sifra leka: </label>
          <input type="text" v-model="sifra">
        </fieldset>
        <fieldset class="form-group">
          <label>Naziv: </label>
          <input type="text" v-model="naziv" >
        </fieldset>
        <fieldset class="form-group">
          <label>RezimIzdavanja: </label>
          <select id="rezim" name="rezim" v-model="rezim">
            <option value="BEZ_RECEPTA">Bez recepta</option>
            <option value="NA_RECEPT">Na recept</option>
          </select>
        </fieldset>
        <fieldset class="form-group">
          <label>OblikLeka: </label>
          <select id="oblik" name="oblik" v-model="oblik">
            <option value="PRASAK">Prasak</option>
            <option value="KAPSULA">Kapsula</option>
            <option value="TABLETA">Tableta</option>
            <option value="MAST">Mast</option>
            <option value="PASTA">Pasta</option>
            <option value="GEL">Gel</option>
            <option value="RASTVOR">Rastvor</option>
            <option value="SIRUP">Sirup</option>
          </select>
        </fieldset>
        <fieldset class="form-group">
          <label>VrstaLeka: </label>
          <select id="vrsta" name="vrsta" v-model="vrsta">
            <option value="ANTIBIOTIK">Antibiotik</option>
            <option value="ANTIMIKOTIK">Antimikotik</option>
          </select>
        </fieldset>
        <fieldset class="form-group">
          <label>Proizvodjac: </label>
          <input type="text" v-model="proizvodjac" >
        </fieldset>
        <button class="btn btn-success" type="submit">Nadji</button>
      </form>
    </div>
  </div>
</template>

<script>
import LekService from "../services/LekService";
export default {
  name: "pretragaLekova",
  data() {
    return {
      sifra: "",
      naziv: "",
      rezim: null,
      oblik: null,
      vrsta: null,
      proizvodjac: "",
      INSTRUCTOR: "lekovi",
      errors: []
    };
  },
  methods: {
    validateAndSubmit(e) {
      e.preventDefault();
      console.log({
      sifra: this.sifra,
      naziv: this.naziv,
      rezim: this.rezim,
      oblik: this.oblik,
      vrsta: this.vrsta,
      proizvodjac: this.proizvodjac
    })
    LekService.pretrazi(this.INSTRUCTOR,{
      sifra: this.sifra,
      naziv: this.naziv,
      rezim: this.rezim,
      oblik: this.oblik,
      vrsta: this.vrsta,
      proizvodjac: this.proizvodjac
    }).then(() => {
                    this.$router.push('/lekovi');
                });
  }
}}
</script>

<style>
</style>