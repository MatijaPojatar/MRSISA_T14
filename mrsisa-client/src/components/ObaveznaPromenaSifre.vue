<template>
<div>
  <v-card width="600" class="mt-16 mx-auto"
    min-width="33%"
    outlined
    app>
    <v-form ref="forma" v-model="valid" lazy-validation>
      <div>
        Upišite svoju novu lozinku:
      </div>

      <v-text-field
      v-model="lozinka"
      :rules=[rules.required]
      label="Lozinka"
      required
      @click:append="show1 = !show1"
      :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
      :type="show1 ? 'text' : 'password'"
      />

      <div>
        Potvrdite svoju lozinku:
      </div>

      <v-text-field
      v-model="potvrdaLozinke"
      :rules=[rules.required]
      label="Ponovljena lozinka"
      required
      @click:append="show2 = !show2"
      :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
      :type="show2 ? 'text' : 'password'"
      />

      <v-btn
      :disabled="!valid"
      @click="validateAndSend"
      >Sačuvaj</v-btn>
    </v-form>
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
        <v-card-text>Uspešno ste promenili lozinku.</v-card-text>
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
import {mapActions, mapGetters} from 'vuex'

export default {
  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
      role: "korisnici/getRole",
    }),
  },

  data: () => ({
    lozinka: "",
    potvrdaLozinke: "",
    potvrda: false,

    valid: true,
    show1: false,
    show2: false,

    rules: {
      required: (v) => !!v || "Obavezno polje",
    }
  }),

  methods: {
  ...mapActions({
    prvaPromenaSifreAction: "korisnici/prvaPromenaSifreAction",
    logoutAction: "korisnici/logoutAction"
  }),

  endPotvrda(){
    this.potvrda = false;
    this.logoutAction();
      this.$router.push("/");
  },
  async validateAndSend(){
    if(this.lozinka.localeCompare(this.potvrdaLozinke)){
      alert("Lozinke nisu iste!");
      return;
    }
    try{
      await this.prvaPromenaSifreAction({id: this.korisnik.id, sifra: this.lozinka})
      this.potvrda = true;
      


      // switch (this.role) {
      //   case "ROLE_DERMATOLOG":
      //     this.$router.push("/dermatolog");
      //     break;
      //   case "ROLE_FARMACEUT":
      //     this.$router.push("/farmaceut");
      //     break;
      //   case "ROLE_ADMIN_SISTEMA":
      //     this.$router.push("/admin");
      //     break;
      //   case "ROLE_ADMIN_APOTEKE":
      //     this.$router.push("/adminApoteke");
      //     break;
      //   case "ROLE_DOBAVLJAC":
      //     this.$router.push("/dobavljac");
      //     break;
      // }
    }catch(error){
      alert("Greska prilikom promene sifre")
    }
  },
}


}
</script>

<style>
  #app {
    background: url("https://images.unsplash.com/photo-1579165466741-7f35e4755660?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    background-size: cover;
  }
</style>