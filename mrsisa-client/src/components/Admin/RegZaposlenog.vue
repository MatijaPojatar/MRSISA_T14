<template>
  <v-card>
    <v-card-title class="display-1 red--text">Registracija Zaposlenog</v-card-title>

    <v-card-text>
      <v-form ref="forma" v-model="valid" lazy-validation>
        <v-row>
        <v-col>
          <v-text-field
            v-model="email"
            :rules="[rules.required, rules.email]"
            label="Email"
            required
          />

          <v-text-field
            v-model="lozinka"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules.required]"
            :type="show1 ? 'text' : 'password'"
            label="Lozinka"
            @click:append="show1 = !show1"
          />

          <v-text-field
            v-model="potvrdaLozinke"
            :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules.required]"
            :type="show2 ? 'text' : 'password'"
            label="Potvrda lozinke"
            @click:append="show2 = !show2"
          />

          <v-text-field
            v-model="ime"
            :rules="[rules.required]"
            label="Ime"
            required
          />

          <v-text-field
            v-model="prezime"
            :rules="[rules.required]"
            label="Prezime"
            required
          />

          <v-text-field
          v-model="brojTelefona"
          :rules="[rules.required, rules.telefon]"
          label="Broj telefona"
          required
          />
          <v-text-field
          v-model="datum"
          :rules="[rules.required, rules.counter]"
          label="Datum rođenja (22/02/1999)"
          required
          />

          <v-select
          v-model="pol"
          :items="polovi"
          label="Pol"
          required
          ></v-select>

        </v-col>
        <v-col>
           
          <v-text-field
          v-model="adresa"
          :rules="[rules.required]"
          label="Adresa"
          required
          />

          <v-text-field
          v-model="grad"
          :rules="[rules.required]"
          label="Grad"
          required
          />

          <v-text-field
          v-model="drzava"
          :rules="[rules.required]"
          label="Država"
          required
          />

          <v-text-field
          v-model="pocetakRadnogVremena"
          :rules="[rules.required, rules.counterVreme]"
          label="Pocetak radnog vremena (08:00)"
          required
          />

          <v-text-field
          v-model="krajRadnogVremena"
          :rules="[rules.required, rules.counterVreme]"
          label="Kraj radnog vremena (16:00)"
          required
          />

          <v-text-field
          v-model="apotekeGdeJeZaposlenString"
          :rules="[rules.required, rules.counterVreme]"
          label="Id Apoteke/a gde je zaposlen (3,4) DORADITI"
          />

          <v-text-field
          class="blue--text"
          v-model="nazivPreduzeca"
          label="Naziv preduzeća"
          />

        </v-col>
        </v-row>
        </v-form>
    </v-card-text>
    <v-card-actions>
        <v-btn @click="cancel" class="grey white--text">Odustani</v-btn>
        <v-spacer></v-spacer>
        <v-btn v-on:click="onSubmitDerm" :disabled="!valid" class="red white--text">Registracija Dermatologa</v-btn>
        <v-btn v-on:click="onSubmitDob" :disabled="!valid" class="blue white--text">Registracija Dobavljaca</v-btn>
    </v-card-actions>
    </v-card>
</template>

<script>
//import { mapActions } from 'vuex';


export default {

  name: "RegistracijaZaposlenog",
  data: () => ({
    polovi: ["MUSKI", "ZENSKI"],

    email: "",
    lozinka: "",
    potvrdaLozinke: "",

    ime: "",
    prezime: "",
    brojTelefona: "",
    datum: "",
    pol: "",

    adresa: "",
    grad: "",
    drzava: "",

    pocetakRadnogVremena: "",
    krajRadnogVremena: "",
    nazivPreduzeca: "",

    apotekeGdeJeZaposlenString: "",
    apotekeGdeJeZaposlen: [],
    valid: true,
    show1: false,
    show2: false,
    rules: {
      required: v => !!v || "Obavezno polje",
      email: v => /.+@.+\..+/.test(v) || "Email nije validan",
      telefon: v => /^\d+$/.test(v) || "Broj telefona nije validan",
      counter: v => v.length == 10 || 'Datum nije dobar',
      counterVreme: v => v.length == 5 || "Vreme nije dobro",
    }
  }),

  computed: {
      show: {
          get() {
              return this.value;
          },
          set(value){
              this.$emit("input", value);
          }
      },
  },

  methods: {


    async onSubmitDerm(){
      const DermDTO = {
        ime: this.ime,
        prezime: this.prezime,
        mail: this.mail,
        password: this.password,
        adresa: this.adresa,
        grad: this.grad,
        drzava: this.drzava,
        brojTelefona: this.brojTelefona,
        pol: this.pol,
        datumRodjenja: this.datum,
        pocetakRadnogVremena: this.pocetakRadnogVremena,
        krajRadnogVremena: this.krajRadnogVremena
      }
      try{
        console.log(DermDTO);
      }catch(error){
        alert("Greska pri registraciji dermatologa")
      }

    },

    async onSubmitDob(){

    },

    cancel(){
      this.$refs.forma.reset();
      this.$refs.forma.resetValidation();
    }
  }

}
</script>

<style>

</style>