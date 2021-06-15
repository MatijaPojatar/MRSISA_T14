<template>
<v-card>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-text-field
      v-model="ime"
      :counter="20"
      :rules="nameRules"
      label="Ime"
      required
    ></v-text-field>

    <v-text-field
      v-model="prezime"
      :counter="20"
      :rules="nameRules"
      label="Prezime"
      required
    ></v-text-field>

    <v-text-field
      v-model=" brojTelefona"
      :counter="10"
      :rules="brojRules"
      label="Broj Telefona"
      required
    ></v-text-field>

    <v-text-field
      v-model=" adresa"
      :rules="requiredRules"
      label="Adresa"
      required
    ></v-text-field>

    <v-text-field
      v-model=" grad"
      :rules="requiredRules"
      label="Grad"
      required
    ></v-text-field>

    <v-text-field
      v-model=" drzava"
      :rules="requiredRules"
      label="Država"
      required
    ></v-text-field>

    <v-select
      v-model=" pol"
      :items="items"
      :rules="[v => !!v || 'Obavezno polje']"
      item-text="Pol"
      label="Pol"
      required
    ></v-select>

    <v-text-field
      v-model=" mail"
      :counter="40"
      :rules=" mailRules"
      label="Email"
      required
    ></v-text-field>


    <v-menu
    ref="menu3"
    v-model="menu3"
    :close-on-content-click="false"
    transition="scale-transition"
    offset-y
    min-width="auto"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-text-field
        v-model="datumRodjenja"
        label="Datum rođenja"
        prepend-icon="mdi-calendar"
        readonly
        v-bind="attrs"
        v-on="on"
      ></v-text-field>
    </template>
    <v-date-picker
      ref="picker"
      v-model="datumRodjenja"
      :max="new Date().toISOString().substr(0, 10)"
      min="1950-01-01"
      @change="$refs.menu3.save(datumRodjenja)"
    ></v-date-picker>
  </v-menu>


    <v-row>
    <v-col
      cols="11"
      sm="5"
    >
      <v-menu
        ref="menu1"
        v-model="menu1"
        :close-on-content-click="false"
        :nudge-right="40"
        :return-value.sync=" pocetakRadnogVremena"
        transition="scale-transition"
        offset-y
        max-width="290px"
        min-width="290px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model=" pocetakRadnogVremena"
            label="Početak radnog vremena"
            prepend-icon="mdi-clock-time-four-outline"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menu1"
          v-model=" pocetakRadnogVremena"
          format="24hr"
          full-width
          @click:minute="$refs.menu1.save( pocetakRadnogVremena)"
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
        v-model="menu2"
        :close-on-content-click="false"
        :nudge-right="40"
        :return-value.sync=" krajRadnogVremena"
        transition="scale-transition"
        offset-y
        max-width="290px"
        min-width="290px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model=" krajRadnogVremena"
            label="Kraj radnog vremena"
            prepend-icon="mdi-clock-time-four-outline"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menu2"
          v-model=" krajRadnogVremena"
          format="24hr"
          full-width
          @click:minute="$refs.menu2.save( krajRadnogVremena)"
        ></v-time-picker>
      </v-menu>
    </v-col>
    </v-row>


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
      Sačuvaj
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
        <v-card-text>Uspešno dodavanje.</v-card-text>
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
      
      ime: "",
      prezime: "",
      brojTelefona: "",
      adresa: "",
      grad: "",
      drzava: "",
      pol: "",
      pocetakRadnogVremena:null,
      krajRadnogVremena:null,
       mail:"",
      password:"",
      datumRodjenja:null,
      
      valid: true,
      nameRules: [
        v => !!v || 'Obavezno polje',
        v => (v && v.length <= 20) || 'Dužina maksimalno 20 karaktera',
      ],
       mailRules: [
        v => !!v || 'Obavezno polje',
        v => (v && v.length <= 40) || 'Dužina maksimalno 40 karaktera',
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
        'MUSKI',
        'ZENSKI',
      ],
      checkbox: false,
      dialog: false,
    }),
    props :{
        apotekaId: Number,
        farmaceut: Boolean,
    },
    
    mounted(){
    },
    watch: {
      menu3 (val) {
        val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
      },
    },
    methods: {
      validate () {
        if(this.$refs.form.validate()){
            const zaposleniDTO = {
              ime: this.ime,
              prezime: this.prezime,
              mail: this.mail,
              adresa: this.adresa,
              grad: this.grad,
              drzava: this.drzava,
              brojTelefona: this.brojTelefona,
              pol: this.pol,
              datumRodjenja: this.datumRodjenja,
              pocetakRadnogVremena: this.pocetakRadnogVremena,
              krajRadnogVremena: this.krajRadnogVremena
            }
            if(this.farmaceut){
                Vue.axios.post(`/farmaceut/dodajFarmaceuta/${this.apotekaId}`,zaposleniDTO)
            }else{
                console.log("dodati za dermatologa")
            }
            
            this.dialog=true;

            console.log(zaposleniDTO)
        }
        
      },
      
      endDialog(){
        this.dialog=false;
        location.reload();
      },
    },
  }
</script>