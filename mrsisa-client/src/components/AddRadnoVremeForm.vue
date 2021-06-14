<template>
<v-card>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >

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
          required
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
            required
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


   

    <v-btn
      :disabled="!valid"
      class="mr-4"
      @click="validate"
    >
      Dodaj
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
        <v-card-text>Dermatlog je zauzet u odabranom radnom vremenu.</v-card-text>
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
    name:"AddRadnoVremeForm",

    data: () => ({
      menu1: false,
      menu2: false,
      
     
      pocetakRadnogVremena:null,
      krajRadnogVremena:null,
      
      valid: true,
      requiredRules: [
        v => !!v || 'Obavezno polje',
      ],
      dialog: false,
    }),
    props :{
        userId: Number,
        apotekaId: Number,
    },
    
    mounted(){
    },
    
    methods: {
      validate () {
        if(this.$refs.form.validate()){
            console.log("apoteka" + this.apotekaId)
            const radnoVreme = {
                pocetak : this.pocetakRadnogVremena,
                kraj : this.krajRadnogVremena
            }
            Vue.axios.put(`/dermatolog/dodajDermatologaApoteka/${this.userId}/${this.apotekaId}`, radnoVreme).then(response=>{
                   
                     const uspesno = response.data
                     if (uspesno){
                       location.reload()
                     }else{
                       this.dialog=true
                     }
                    
                 })

        }
        
      },
      
      endDialog(){
        this.dialog=false;
        location.reload();
      },
    },
  }
</script>