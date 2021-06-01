<template>
    <div justify="space-around" align="center">
    <br/>
    <br/>
    <div>

     <v-btn
        class="mx-2"
        @click="viewFarmaceuti"
    >
        Farmaceuti ocene
    </v-btn>

     <v-btn
        class="mx-2"
        @click="viewDermatolozi"
    > 
        Dermatolozi ocene 
    </v-btn>
     <v-btn
        class="mx-2"
        @click="viewApoteka"
    > 
        Ocena apoteke
    </v-btn>
    <br/>
    <br/>
    <v-btn
        class="mx-2"
        
        @click="viewLekovi"
    >
        Potrošnja lekova
    </v-btn>
    <v-btn
        class="mx-2"
        @click="viewPregledi"
    > 
        Pregledi 
    </v-btn>
    <v-btn
        class="mx-2"
        @click="viewPrihodi"
    > 
        Prihodi
    </v-btn>
    
    </div>
    <br/>
    <br/>

    
    
    <v-container fluid v-if="showLekovi">
        <IzvestajPotrosnjaLekova :apotekaId = "apotekaId"/>
      </v-container>

    <v-container fluid v-if="showFarmaceuti">
        <TableFarmaceuti :apotekaId = "apotekaId" :adminView = "false"/>
      </v-container>

    <v-container fluid v-if="showDermatolozi">
        <TableDermatolozi :apotekaId = "apotekaId" :adminView = "false"/>
      </v-container>

      <v-container fluid v-if="showApoteka">
        <IzvestajOcenaApoteke :apotekaId = "apotekaId"/>
      </v-container>

      <v-container fluid v-if="showPregledi">
        <IzvestajOdrzaniPregledi :apotekaId = "apotekaId"/>
      </v-container>

      

      
   

    <v-dialog
      v-model="obavestenjeDialog"
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
            @click="endObavestenjeDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>

  
    </div>
    
</template>

<script>
import TableFarmaceuti from "./TableFarmaceuti";
import TableDermatolozi from "./TableDermatolozi";
import IzvestajOcenaApoteke from "./IzvestajOcenaApoteke";
import IzvestajOdrzaniPregledi from "./IzvestajOdrzaniPregledi";
import IzvestajPotrosnjaLekova from "./IzvestajPotrosnjaLekova";
import Vue from "vue";


export default{
    components: {
        TableFarmaceuti,
        TableDermatolozi,
        IzvestajOcenaApoteke,
        IzvestajOdrzaniPregledi,
        IzvestajPotrosnjaLekova,

    },
    data: () => ({
        
        obavestenjeDialog: false,
        showLekovi:false,
        showFarmaceuti:false,
        showDermatolozi:false,
        showPregledi: false,
        showApoteka: false,
        showPrihodi: false,
        message:"",
        apoteka: {
          naziv: "",
          opis: "",
          adresa: "",
          grad: "",
          drzava: "",
          ocena: "",
      },
    }),
    props :{
        apotekaId: Number,
    },
    mounted(){
        Vue.axios.get(`http://localhost:8080/apoteke/getOne/${this.apotekaId}`).then(response => {
            this.apoteka = response.data
        });
    },

    methods:{
        endObavestenjeDialog(){
            this.obavestenjeDialog = false;
        },
        viewLekovi(){
            this.showLekovi = true;
            this.showFarmaceuti = false;
            this.showDermatolozi = false;
            this.showPregledi = false;
            this.showApoteka = false;
        },
        viewFarmaceuti(){
            this.showLekovi = false;
            this.showFarmaceuti = true;
            this.showDermatolozi = false;
            this.showPregledi = false;
            this.showApoteka = false;
        },
        viewDermatolozi(){
            this.showLekovi = false;
            this.showFarmaceuti = false;
            this.showDermatolozi = true;
            this.showPregledi = false;
           this.showApoteka = false;
        },
        viewApoteka(){
            this.showLekovi = false;
            this.showFarmaceuti = false;
            this.showDermatolozi = false;
            this.showPregledi = false;
            this.showApoteka = true;
        },
        viewPregledi(){
            this.showLekovi = false;
            this.showFarmaceuti = false;
            this.showDermatolozi = false;
            this.showPregledi = true;
            this.showApoteka = false;
        },
        viewPrihodi(){
            this.showLekovi = false;
            this.showFarmaceuti = false;
            this.showDermatolozi = false;
            this.showPregledi = true;
            this.showApoteka = false;
        },
        

        

        
        
        
    },
}
</script>

<style>

</style>