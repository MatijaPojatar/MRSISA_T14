<template>
  <v-card>
    <v-card-title>Unos leka: </v-card-title>
    <v-card-text>
      <v-form ref= "forma" v-model="valid" lazy-vaidation>
        <v-row>

          <v-col>
            <v-text-field
            v-model="sifra"
            :rules="[rules.required]"
            label="Sifra"
            required
            />

            <v-text-field
            v-model="naziv"
            :rules="[rules.required]"
            label="Naziv"
            required
            />

            <v-text-field
            v-model="proizvodjac"
            :rules="[rules.required]"
            label="Proizvodjac"
            required
            />

          <v-radio-group v-model="rezimIzdavanja">
            <v-radio
              :label="`Recept`"
              :value="true"
            ></v-radio>
            <v-radio
              :label="`Bez Recepta`"
              :value="false"
            ></v-radio>
          </v-radio-group>

          <v-text-field
            v-model="zamenski" 
            :rules="[rules.required]"
            label="Zamenski lekovi"
            required
            />

          </v-col>

          <v-col>
            <v-select
            v-model="vrsta"
              :items="vrste"
              label="Vrsta leka"
            ></v-select>

            <v-select
              v-model="oblik"
              :items="oblici"
              label="Oblik leka"
            ></v-select>

            <v-text-field
            v-model="sastav"
            :rules="[rules.required]"
            label="Sastav"
            required
            />

            <v-textarea
            outlined
            v-model="napomena"
            label="Napomena"
          ></v-textarea>

          </v-col>


        </v-row>

      </v-form>
    </v-card-text>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn @click="cancel">Odustani</v-btn>
      <v-btn @click="onSubmit" :disabled="!valid">Kreiraj</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import {mapActions, mapGetters } from "vuex"

export default {
  name: "KreirajLek",

  props : {
    
  },

  data: () => ({
    sifra: "",
    naziv: "",
    proizvodjac: "",

    rezimIzdavanja: false,

    vrsta: "",
    oblik: "",
    sastav: "",

    zamenski: "", //isparsirati u listu
    napomena: "",

    valid: true,

    rules: {
            required: v => !!v || "Obavezno polje",
        }
  }),

  computed: {
    ...mapGetters({
      oblici: "lekovi/getOblici",
      vrste: "lekovi/getVrste"
    }),
  },

  methods: {
    ...mapActions({
      // create : "lekovi/"
      getObliciAction: "lekovi/getObliciAction",
      getVrsteAction: "lekovi/getVrsteAction",
      addLekAction: "lekovi/addLekAction",
    }),

    onSubmit() {
      if(this.$refs.forma.validate()){
        const lekInfo = {
          id : 55,
          naziv : this.naziv,
          sastav : this.sastav,
          proizvodjac : this.proizvodjac,
          napomena : this.napomena,
          rezimIzdavanja : this.rezimIzdavanja,
          oblikLeka : this.oblik,
          vrstaLeka : this.vrsta
          }
          try{
            this.addLekAction(lekInfo);
          }catch(e){
            alert("Ime leka nije originalno");
            return;
          }
          alert("Lek uspesno dodat")
      }
      alert("Submit");
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

  async beforeMount() {
    console.log("BEFORE MOUNT")
    await this.getObliciAction();
    await this.getVrsteAction();
  }
}
</script>

<style>

</style>