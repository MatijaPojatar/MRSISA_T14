<template>
  <v-card max-width="500">
    <v-card-title>
      Registracija Apoteke
    </v-card-title>

    <v-card-text>
      <v-form ref="forma" v-model="valid" lazy-alidation>
        <v-text-field
        v-model="naziv"
        label="Naziv"
        :rules="[rules.required]"
        required
        />

        <v-text-field
        v-model="adresa"
        label="Adresa"
        :rules="[rules.required]"
        required
        />
        <v-text-field
        v-model="grad"
        label="Grad"
        :rules="[rules.required]"
        required
        />
        <v-text-field
        v-model="drzava"
        label="Drzava"
        :rules="[rules.required]"
        required
        />
        <v-textarea
        v-model="opis"
        label="Opis"
        placeholder="Osnovana je ..."
        />
      </v-form>
    </v-card-text>

    <v-card-actions>
      <v-btn @click="cancel">Odustani</v-btn>
      <v-spacer></v-spacer>
      <v-btn @click="onSubmit" :disabled="!valid">Registruj</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  name: "RegApoteka",

  data: () => ({
    naziv: "",
    adresa: "",
    grad: "",
    drzava: "",
    opis: "",

    valid : true,
    rules: {
      required: v=> !!v || "Obavezno polje"
    }
  }),

  methods:
  {
    ...mapActions({
      getApotekeAction: "apoteke/getApotekeAction", 
      addApotekaAction: "apoteke/addApotekaAction",

    }),

    onSubmit() {
      if(this.$refs.forma.validate()){
        const apotekaInfo = {
          naziv: this.naziv,
          adresa: this.adresa,
          grad: this.grad,
          drzava: this.drzava,
          opis: this.opis
        }
        try{
          this.addApotekaAction(apotekaInfo);
        }catch(e){
          alert("Ime apoteke nije originalno")
          return;
        }
        alert("Uspehh")
      }
      alert("Submit")
      this.cancel();
    },

    cancel(){
      this.reset();
      this.resetValidation();
    },

    reset(){
      this.$refs.forma.reset();
    },

    resetValidation(){
      this.$refs.forma.resetValidation();
    }
  },


}
</script>

<style>

</style>

// naziv i adresu apoteke (dodatno prikaz adrese na mapi), 
// ● listu dermatologa i farmaceuta koji su zaposleni u njoj, 
// ● listu lekova koje apoteka ima na stanju, 
// ● listu svih termina za preglede kod dermatologa koje može da zakaže, 
// ● prosečnu ocenu apoteke. 

// Integer id, String naziv, String adresa, String grad, String drzava, String opis,
// 			List<Lek> lekovi, Set<Farmaceut> farmaceuti, Set<Dermatolog> dermatolozi, List<Termin> termini,
// 			Magacin magacin