<template>
<v-card>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-text-field
      :rules="oldPassRules"
      :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
      :type="show1 ? 'text' : 'password'"
      label="Uneti staru šifru"
      required
      @click:append="show1 = !show1"
    ></v-text-field>

    <v-text-field
      v-model="newPass"
      :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
      :type="show2 ? 'text' : 'password'"
      :rules="requiredRules"
      label="Nova šifra"
      required
      @click:append="show2 = !show2"
    ></v-text-field>

    <v-text-field
      :rules="repeatPassRules"
      :append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
      :type="show3 ? 'text' : 'password'"
      label="Ponoviti novu šifru"
      required
      @click:append="show3 = !show3"
    ></v-text-field>

    <v-checkbox
      v-model="checkbox"
      :rules="[v => !!v || 'You must agree to continue!']"
      label="Da li ste sigurni?"
      required
    ></v-checkbox>

    <v-btn
      :disabled="!valid"
      class="mr-4"
      @click="validate"
    >
      Sačuvaj izmene
    </v-btn>
  </v-form>
    <v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>Vaša šifra je uspešno promenjena.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</v-card>
</template>

<script>
 import axios from "axios";

  export default {

    data: () => ({
      newPass: "",
      valid: true,
      oldPassRules: [],
      repeatPassRules: [],
      requiredRules: [
        v => !!v || 'Obavezno polje',
      ],
      checkbox: false,
      show1: false,
      show2: false,
      show3: false,
      dialog: false,
    }),
    props :{
        pass: String,
        id: Number,
        farmaceut: Boolean,
    },
    created(){
        this.oldPassRules= [
        v => !!v || 'Obavezno polje',
        v => (v===this.pass) || 'Šifra nije ispravna',]
        this.repeatPassRules= [
        v => !!v || 'Obavezno polje',
        v => (v ===this.newPass ) || 'Šifre se ne poklapaju',]
    },
    mounted(){
    },

    methods: {
      validate () {
        if(this.$refs.form.validate()){
            if(this.farmaceut){
                 axios.put(`http://localhost:8080/farmaceut/pass/${this.id}`,this.newPass,{headers: {"Content-Type": "text/plain"}})
            }else{
                 axios.put(`http://localhost:8080/dermatolog/pass/${this.id}`,this.newPass,{headers: {"Content-Type": "text/plain"}})
            }
            this.dialog=true;
        }
        
      },

      endDialog(){
        this.dialog=false;
        location.reload();
      },
    },
  }
</script>