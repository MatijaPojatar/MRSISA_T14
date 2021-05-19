<template>
  <v-card class="mt-16 mx-auto"
    min-width="33%"
    outlined
    app>
    <v-card-title class="justify-center">Prijava</v-card-title>
    <v-card-text>
      <v-form ref="forma" v-model="valid" lazy-validation>
        <v-text-field
          v-model="email"
          :rules="[rules.required, rules.email]"
          label="Email"
          required
        />
        <v-text-field
          v-model="lozinka"
          :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required]"
          :type="show ? 'text' : 'password'"
          label="Lozinka"
          @click:append="show = !show"
        />
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-btn @click="cancel" class="blue white--text">Odustani</v-btn>
      <v-spacer></v-spacer>
      <a @click="register" class="text-decoration-none">Kreiranje naloga</a>
      <v-spacer></v-spacer>
      <v-btn @click="onSubmit" :disabled="!valid" class="blue white--text">Prijava</v-btn>
    </v-card-actions>
    <v-card-actions>
      <v-btn @click="cancel">Pretraga apoteka</v-btn>
      <v-spacer></v-spacer>
      <v-btn @click="onSubmit">Pretraga lekova</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "Prijava",

  props: {
    value: Boolean,
  },

  data: () => ({
    email: "",
    lozinka: "",
    valid: true,
    show: false,
    rules: {
      required: (v) => !!v || "Obavezno polje",
      email: (v) => /.+@.+\..+/.test(v) || "Email nije validan",
    },
  }),

  computed: {
    ...mapGetters({
      currentMail: "korisnici/getMail",
      currentRole: "korisnici/getRole",
      korisnik: "korisnici/getKorisnik"
    }),
  },
  methods: {
    ...mapActions({
      logInAction: "korisnici/logInAction",
    }),

    cancel() {
      this.email = "";
      this.lozinka = "";
    },
    
    register() {
      this.$router.push("/registracija");
    },

    async onSubmit() {
      try {
        const credentials = {
          mail: this.email,
          password: this.lozinka,
        };
        await this.logInAction(credentials);

        if (!this.korisnik.promenjenaLozinka){
          alert("Usli ovde")
          this.$router.push("/promenaSifre");
          return;
        }

        let role = this.currentRole;
        console.log(role);
        switch (role) {
          case "ROLE_PACIJENT":
            this.$router.push("/pacijent");
            break;
          case "ROLE_DERMATOLOG":
            this.$router.push("/dermatolog");
            break;
          case "ROLE_FARMACEUT":
            this.$router.push("/farmaceut");
            break;
          case "ROLE_ADMIN_SISTEMA":
            this.$router.push("/admin");
            break;
          case "ROLE_ADMIN_APOTEKE":
            this.$router.push("/adminApoteke");
            break;
          case "ROLE_DOBAVLJAC":
            this.$router.push("/dobavljac");
            break;
        }
      } catch (error) {
        console.log(error);
        alert("Greska pri prijavi. Nekorektni kredencijali ili profil nije aktiviran");
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