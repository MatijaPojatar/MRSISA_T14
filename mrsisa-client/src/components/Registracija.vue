<template>
  <v-card class="mt-16 mx-auto" min-width="50%" outlined app>
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
        <!--TODO: pol, datum-->
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
</template>

<script>
export default {
  name: "Registracija",

  props: {
    value: Boolean,
  },

  data: () => ({
    email: "",
    password: "",
    valid: true,
    show1: false,
    show2: false,
    rules: {
      required: (v) => !!v || "Obavezno polje",
      email: (v) => /.+@.+\..+/.test(v) || "Email nije validan",
      telefon: (v) => /^\d+$/.test(v) || "Broj telefona nije validan",
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
      cancel() {
      this.email = "";
      this.lozinka = "";
      this.potvrdaLozinke = "";
      this.ime = "";
      this.prezime = "";
      this.adresa = "";
      this.grad = "";
      this.drzava = "";
      this.telefon = "";
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