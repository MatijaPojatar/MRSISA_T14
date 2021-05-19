<template>
<div>
  <v-card class="mt-16 mx-auto" min-width="50%" outlined
  width="700" app>
    <v-card-title class="justify-center">Registracija</v-card-title>
    <v-form ref="forma" v-model="valid" lazy-validation>
    <v-container>
        <v-row no-gutters justify="space-around">
            <v-col md="4">
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
            </v-col>
            <v-col md="4">
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
          v-model="telefon"
          :rules="[rules.required, rules.telefon]"
          label="Broj telefona"
          required
        />

         <v-text-field
          v-model="datum"
          :rules="[rules.required, rules.counter]"
          label="Datum rođenja (1999-02-02)"
          required
          />

          <v-select
          v-model="pol"
          :items="polovi"
          label="Pol"
          required
          ></v-select>

            </v-col>
        </v-row> 
    </v-container>
    </v-form>
    <v-card-actions>
      <v-btn @click="cancel" class="blue white--text">Odustani</v-btn>
      <v-spacer></v-spacer>
      <v-btn @click="onSubmit" :disabled="!valid" class="blue white--text">Registracija</v-btn>
    </v-card-actions>
  </v-card>

    <v-dialog
      v-model="potvrda"
      persistent
      max-width="300"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>Da bi ste se prijavili na sistem, prethodno je potrebno da aktivirate profil preko linka koji ste dobili e-poštom.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endPotvrda"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>
</div>
</template>

<script>
import {mapActions} from 'vuex'


export default {
  name: "Registracija",

  props: {
    value: Boolean,
  },

  data: () => ({
    polovi: ["MUSKI", "ZENSKI"],

    email: "",
    lozinka: "",
    potvrdaLozinke: "",

    ime: "",
    prezime: "",
    telefon: "",
    datum: "",
    pol: "",

    adresa: "",
    grad: "",
    drzava: "",

    potvrda: false,

    valid: true,
    show1: false,
    show2: false,
    rules: {
      required: (v) => !!v || "Obavezno polje",
      email: (v) => /.+@.+\..+/.test(v) || "Email nije validan",
      telefon: (v) => /^\d+$/.test(v) || "Broj telefona nije validan",
      counter: v => v.length == 10 || 'Datum nije dobar',
    },
  }),

  computed: {
    show: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit("input", value);
      },
    },
  },

  methods: {
    ...mapActions({
      signUpAction: "korisnici/signUpAction"
    }),

      cancel() {
        this.$router.push("/");
    },

    endPotvrda() {
      this.potvrda = false;
      this.cancel();
    },

    async onSubmit() {
      if(this.lozinka.localeCompare(this.potvrdaLozinke)){ //ako su iste, vraca 0, pa je falsy
        alert("Lozinke nisu iste!");
        return;
      }

      const PacDTO = {
        ime: this.ime,
        prezime: this.prezime,

        mail: this.email,
        password: this.lozinka,

        adresa: this.adresa,
        grad: this.grad,
        drzava: this.drzava,

        brojTelefona: this.telefon,
        pol: this.pol,
        datumRodjenja: this.datum,

        brojPoena: 0,
        penali: 0,
        promenjenaLozinka: false,
        enabled: false,

        
      }
  try{
    alert("ZELJA ZA REGISTRACIJOM "+ this.ime + this.prezime);        
    await this.signUpAction(PacDTO);

    this.potvrda = true;
    console.log(PacDTO);

  }catch(error){
    alert("Greska pri registraciji")
  }

    },
  },
};
</script>

<style>
  #app {
    background: url("https://images.unsplash.com/photo-1579165466741-7f35e4755660?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    background-size: cover;
  }
</style>