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

    <v-row v-if = "adminView">
    <v-col
      cols="11"
      sm="5"
    >
      <v-menu
        ref="menu1"
        :disabled="!editable"
        v-model="menu1"
        :close-on-content-click="false"
        :nudge-right="40"
        :return-value.sync="newInfo.pocetakRadnogVremena"
        transition="scale-transition"
        offset-y
        max-width="290px"
        min-width="290px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model="newInfo.pocetakRadnogVremena"
            label="Početak radnog vremena"
            prepend-icon="mdi-clock-time-four-outline"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menu1"
          v-model="newInfo.pocetakRadnogVremena"
          format="24hr"
          full-width
          @click:minute="$refs.menu1.save(newInfo.pocetakRadnogVremena)"
        ></v-time-picker>
      </v-menu>
    </v-col>

    <v-spacer></v-spacer>

    <v-col
      cols="11"
      sm="5"
    >
      <v-menu
        ref="menu2"
        :disabled="!editable"
        v-model="menu2"
        :close-on-content-click="false"
        :nudge-right="40"
        :return-value.sync="newInfo.krajRadnogVremena"
        transition="scale-transition"
        offset-y
        max-width="290px"
        min-width="290px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model="newInfo.krajRadnogVremena"
            label="Kraj radnog vremena"
            prepend-icon="mdi-clock-time-four-outline"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menu2"
          v-model="newInfo.krajRadnogVremena"
          format="24hr"
          full-width
          @click:minute="$refs.menu2.save(newInfo.krajRadnogVremena)"
        ></v-time-picker>
      </v-menu>
    </v-col>
    </v-row>

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
      menu1: false,
      menu2: false,
      menu3: false,
      newInfo: {
          ime: "",
          prezime: "",
          brojTelefona: "",
          adresa: "",
          grad: "",
          drzava: "",
          pol: "",
          pocetakRadnogVremena: null,
          krajRadnogVremena: null,
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
        adminView: Boolean,
    },
    created(){
        this.reset()
    },
    mounted(){
      if(this.adminView){
        console.log(this.user)
        if (this.newInfo.pocetakRadnogVremena[0].toString().length == 1){
            this.newInfo.pocetakRadnogVremena[0] = "0" + this.newInfo.pocetakRadnogVremena[0].toString();
        }
        else{
            this.newInfo.pocetakRadnogVremena[0] = this.newInfo.pocetakRadnogVremena[0].toString();
        }
        if (this.newInfo.pocetakRadnogVremena[1].toString().length == 1){
            this.newInfo.pocetakRadnogVremena[1] = "0" + this.newInfo.pocetakRadnogVremena[1].toString();
        }
        else{
            this.newInfo.pocetakRadnogVremena[1] = this.newInfo.pocetakRadnogVremena[1].toString();
        }
        this.newInfo.pocetakRadnogVremena = this.newInfo.pocetakRadnogVremena[0] + ":"+this.newInfo.pocetakRadnogVremena[1]


        if (this.newInfo.krajRadnogVremena[0].toString().length == 1){
            this.newInfo.krajRadnogVremena[0] = "0" + this.newInfo.krajRadnogVremena[0].toString();
        }
        else{
            this.newInfo.krajRadnogVremena[0] = this.newInfo.krajRadnogVremena[0].toString();
        }
        if (this.newInfo.krajRadnogVremena[1].toString().length == 1){
            this.newInfo.krajRadnogVremena[1] = "0" + this.newInfo.krajRadnogVremena[1].toString();
        }
        else{
            this.newInfo.krajRadnogVremena[1] = this.newInfo.krajRadnogVremena[1].toString();
        }
        this.newInfo.krajRadnogVremena = this.newInfo.krajRadnogVremena[0] + ":"+this.newInfo.krajRadnogVremena[1]
      }
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
            this.user.pocetakRadnogVremena=this.newInfo.pocetakRadnogVremena
            this.user.krajRadnogVremena=this.newInfo.krajRadnogVremena
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
        this.newInfo.pocetakRadnogVremena=this.user.pocetakRadnogVremena
        this.newInfo.krajRadnogVremena=this.user.krajRadnogVremena
      },
      endDialog(){
        this.dialog=false;
        //location.reload();
      },
    },
  }
</script>