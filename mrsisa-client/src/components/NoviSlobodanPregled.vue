<template>
<v-card>
  
 <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >

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
            v-model="datum"
            label="Datum:"
            prepend-icon="mdi-calendar"
            readonly
            required
            v-bind="attrs"
            v-on="on"
        ></v-text-field>
        
        </template>
        <v-date-picker
        ref="picker"
        v-model="datum"
        
        :min="new Date().toISOString().substr(0, 10)"
        @change="$refs.menu3.save(datum)"
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
        :return-value.sync=" pocetak"
        transition="scale-transition"
        offset-y
        max-width="290px"
        min-width="290px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model=" pocetak"
            label="Početak termina"
            prepend-icon="mdi-clock-time-four-outline"
            readonly
            
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menu1"
          v-model=" pocetak"
          format="24hr"
          :min="pocetakRadnogVremena"
          :max="kraj"
          full-width
          @click:minute="$refs.menu1.save( pocetak)"
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
        :return-value.sync=" kraj"
        transition="scale-transition"
        offset-y
        max-width="290px"
        min-width="290px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model=" kraj"
            label="Kraj termina"
            prepend-icon="mdi-clock-time-four-outline"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menu2"
          v-model=" kraj"
          format="24hr"
          :min="pocetak"
          :max="krajRadnogVremena"
          full-width
          @click:minute="$refs.menu2.save( kraj)"
        ></v-time-picker>
      </v-menu>
    </v-col>
    </v-row>

    <v-text-field
      v-model="cena"
      :counter="10"
      :rules="brojRules"
      label="Cena:"
      required
    ></v-text-field>


    <v-btn
      :disabled="!valid"
      class="mr-4"
      @click="dodaj"
    >
      Dodaj termin
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
        <v-card-text>{{message}}</v-card-text>
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
      pocetak:null,
      kraj:null,
      cena:"",
      pocetakRadnogVremena:"",
      krajRadnogVremena:"",
      datum:null,
      message:"",

      menu1: false,
      menu2: false,
      menu3: false,
      
      
      
      valid: true,
      dialog: false,
      brojRules: [
        v => !!v || 'Obavezno polje',
        v => (v && v.length <= 10 ) || 'Dužina maksimalno 10 karaktera',
        v => (new RegExp("^(0|([1-9][0-9]*))(\\.[0-9]+)?$").test(v)) || 'Loš format',
      ],
      requiredRules: [
        v => !!v || 'Obavezno polje',
      ],
    }),
    props :{
        apotekaId: Number,
        user: {},
    },
    created(){
        this.reset();
    },
    
    mounted(){
        this.reset();
    },
    methods: {
        reset (){
            console.log(this.user);
            this.pocetakRadnogVremena = this.user.pocetakRadnogVremenaStr;
            this.krajRadnogVremena = this.user.krajRadnogVremenaStr;
            
            this.pocetak = this.user.pocetakRadnogVremenaStr;
            this.kraj = this.user.krajRadnogVremenaStr; 
            this.cena = "";
            this.datum=null;
                
        },
      dodaj () 
      {
          if(this.$refs.form.validate()){
            let pocetak=new Date(this.datum+" "+this.pocetak)
            let kraj = new Date(this.datum+" "+this.kraj)
            if (!this.datum){
                this.message = "Uneti datum termina.";
                this.dialog=true;
            }else if (!this.pocetak){
                this.message = "Uneti pocetak termina.";
                this.dialog=true;
            }else if (!this.kraj){
                this.message = "Uneti kraj termina.";
                this.dialog=true;
            }else if(kraj-pocetak>7200000 || kraj-pocetak<1800000){
                this.message = "Termin može trajati između 30min i 2h.";
                this.dialog=true;
            }else{
                
                Vue.axios.put(`/pregled/dodajSlobodan/${this.user.id}`,{start:pocetak, end:kraj, apotekaId:this.apotekaId, izvrsen:false, cena:this.cena}).then(response=>{
                   
                            const uspesno = response.data
                            if (uspesno){
                                this.message = "Uspešno dodavanje termina.";
                            }else{
                              this.message = "Dermatolog nije dostupan u odabranom terminu.";
                            }
                this.dialog=true;
                 });
            }
            
          }

        
        
      },
      
      endDialog(){
        this.dialog=false;
        this.reset();
      },
    },
  }
</script>