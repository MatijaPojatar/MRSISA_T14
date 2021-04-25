<template>
<v-card>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-text-field
      v-model="newInfo.ime"
      :counter="20"
      :rules="nameRules"
      label="Ime"
      :disabled="!editable"
      required
    ></v-text-field>

    <v-text-field
      v-model="newInfo.prezime"
      :counter="20"
      :disabled="!editable"
      :rules="nameRules"
      label="Prezime"
      required
    ></v-text-field>

    <v-text-field
      v-model="newInfo.brojTelefona"
      :counter="10"
      :disabled="!editable"
      :rules="brojRules"
      label="Broj Telefona"
      required
    ></v-text-field>

    <v-text-field
      v-model="newInfo.adresa"
      :rules="requiredRules"
      :disabled="!editable"
      label="Adresa"
      required
    ></v-text-field>

    <v-text-field
      v-model="newInfo.grad"
      :rules="requiredRules"
      :disabled="!editable"
      label="Grad"
      required
    ></v-text-field>

    <v-text-field
      v-model="newInfo.drzava"
      :rules="requiredRules"
      :disabled="!editable"
      label="Država"
      required
    ></v-text-field>

    <v-select
      v-model="newInfo.pol"
      :disabled="!editable"
      :items="items"
      :rules="[v => !!v || 'Obavezno polje']"
      item-text="Pol"
      label="Pol"
      required
    ></v-select>

    <v-checkbox
      v-model="checkbox"
      v-if="editable"
      :rules="[v => !!v || 'You must agree to continue!']"
      label="Da li ste sigurni?"
      required
    ></v-checkbox>

    <v-btn
      :disabled="!valid"
      v-if="editable"
      class="mr-4"
      @click="validate"
    >
      Sačuvaj izmene
    </v-btn>

    <v-btn
      color="error"
      class="mr-4"
      v-if="editable"
      @click="reset"
    >
      Resetuj
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
        <v-card-text>Izmene su uspešno sačuvane.</v-card-text>
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
import Vue from "vue";

  export default {

    data: () => ({
      newInfo: {
          ime: "",
          prezime: "",
          brojTelefona: "",
          adresa: "",
          grad: "",
          drzava: "",
          pol: "",
      },
      valid: true,
      nameRules: [
        v => !!v || 'Obavezno polje',
        v => (v && v.length <= 20) || 'Dužina maksimalno 20 karaktera',
      ],
      brojRules: [
        v => !!v || 'Obavezno polje',
        v => (v && v.length == 10 ) || 'Dužina mora biti 10 karaktera',
        v => (new RegExp("06[0-9]{8}").test(v)) || 'Loš format',
      ],
      requiredRules: [
        v => !!v || 'Obavezno polje',
      ],
      items: [
        'M',
        'Ž',
      ],
      checkbox: false,
      dialog: false,
    }),
    props :{
        user: {},
        farmaceut: Boolean,
        editable: Boolean,
    },
    created(){
        this.reset()
    },
    mounted(){
        console.log(this.user)
    },

    methods: {
      validate () {
        if(this.$refs.form.validate()){
            this.user.ime=this.newInfo.ime
            this.user.prezime=this.newInfo.prezime
            this.user.brojTelefona=this.newInfo.brojTelefona
            this.user.adresa=this.newInfo.adresa
            this.user.grad=this.newInfo.grad
            this.user.drzava=this.newInfo.drzava
            this.user.pol=this.newInfo.pol==="M" ? "MUSKI" : "ZENSKI"
            if(this.farmaceut){
                Vue.axios.put(`http://localhost:8080/farmaceut/save/${this.user.id}`,this.user)
            }else{
                 Vue.axios.put(`http://localhost:8080/dermatolog/save/${this.user.id}`,this.user)
            }
            this.dialog=true;
        }
        
      },
      reset () {
        this.newInfo.ime=this.user.ime
        this.newInfo.prezime=this.user.prezime
        this.newInfo.brojTelefona=this.user.brojTelefona
        this.newInfo.adresa=this.user.adresa
        this.newInfo.grad=this.user.grad
        this.newInfo.drzava=this.user.drzava
        this.newInfo.pol= this.user.pol==="MUSKI" ? "M" : "Ž"
      },
      endDialog(){
        this.dialog=false;
        location.reload();
      },
    },
  }
</script>