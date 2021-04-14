<template>
  <v-card>
    <v-card-title class="display-1 red--text">Registracija Dermatologa</v-card-title>

    <v-card-text>
      <v-form ref="forma" v-model="valid" lazy-validation>
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
        </v-col>
        <v-col>
          
          <v-text-field
          v-model="datum"
          :rules="[rules.required, rules.counter]"
          label="Datum rođenja (22/02/1999)"
          counter
          required
          />

          <v-select
          v-model="pol"
          :items="polovi"
          label="Pol"
          required
          ></v-select>
            
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

        </v-col>

        </v-form>
    </v-card-text>
    <v-card-actions>
        <v-btn @click="cancel" class="grey white--text">Odustani</v-btn>
        <v-spacer></v-spacer>
        <v-btn v-on:click="onSubmit" :disabled="!valid" class="red white--text">Registracija</v-btn>
    </v-card-actions>
    </v-card>
</template>

<script>


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

    valid: true,
    show1: false,
    show2: false,
    rules: {
      required: v => !!v || "Obavezno polje",
      email: v => /.+@.+\..+/.test(v) || "Email nije validan",
      telefon: v => /^\d+$/.test(v) || "Broj telefona nije validan",
      counter: v => v.length == 10 || 'Datum nije dobar',
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
    }

}
</script>

<style>

</style>