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
        <button class="btn btn-success" type="submit">Nadji</button>
      </form>
    </div>
  </div>
</template>

<script>
import RezervacijaService from "../services/RezervacijaService";
export default {
  name: "pretragaRezervacija",
  data() {
    return {
      sifra: "",
      naziv: "",
      rezim: null,
      oblik: null,
      vrsta: null,
      INSTRUCTOR: "rezervacija",
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
      vrsta: this.vrsta
    })
    RezervacijaService.pretrazi(this.INSTRUCTOR,{
      sifra: this.sifra,
      naziv: this.naziv,
      rezim: this.rezim,
      oblik: this.oblik,
      vrsta: this.vrsta
    }).then(() => {
                    this.$router.push('/rezervacija');
                });
  }
}}
</script>

<style>
</style>