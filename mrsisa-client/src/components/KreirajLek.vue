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
              :items="this.vrste"
              label="Vrsta leka"
            ></v-select>

            <v-select
              v-model="oblik"
              :items="this.oblici"
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
      <v-btn>Odustani</v-btn>
      <v-btn>Kreiraj</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import {mapActions, mapState } from "vuex"

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

    rules: {
            required: v => !!v || "Obavezno polje",
        }
  }),

  methods: {
    ...mapState({
      oblici: state => state.lekovi.oblici,
      vrste: state => state.lekovi.vrste
    }),

    ...mapActions({
      // create : "lekovi/"
      getObliciAction: "lekovi/getObliciAction",
      getVrsteAction: "lekovi/getVrsteAction"
    }),

    onSubmit() {
      // if(this.$refs.forma.validate()){

      // }
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

  beforeMount() {
    console.log("BEFORE MOUNT")
    this.getObliciAction();
    this.getVrsteAction();
  }
}
</script>

<style>

</style>