<template>
    <div justify="space-around" align="center">
    <br/>
    <br/>
    <v-card class="mx-2">
    <br/>
    Naziv: {{apoteka.naziv}} <br/>
    Opis: {{apoteka.opis}} <br/>
    Adresa: {{apoteka.adresa}}, {{apoteka.grad}}, {{apoteka.drzava}}<br/>
    <br/>
    </v-card>
    <br/>
    <div>
    <v-btn
        class="mx-2"
        
        @click="viewLekovi"
    >
        Lekovi
    </v-btn>

     <v-btn
        class="mx-2"
        @click="viewFarmaceuti"
    >
        Farmaceuti
    </v-btn>

     <v-btn
        class="mx-2"
        @click="viewDermatolozi"
    > 
        Dermatolozi 
    </v-btn>
    <br/>
    <br/>
    <v-btn
        class="mx-2"
        @click="viewPregledi"
        v-if="registrovan"
    > 
        Zakaži pregled 
    </v-btn>
    <v-btn
        class="mx-2"
        @click="viewSavetovanja"
        v-if="registrovan"
    > 
        Zakaži savetovanje 
    </v-btn>
    <v-btn
        class="mx-2"
        @click="pretplatiSe"
        v-if="registrovan"
    > 
        Pretplati se 
    </v-btn>
    </div>
    <br/>
    <br/>

    
    
    <v-container fluid v-if="showLekovi">
        <TableLekovi :apotekaId = "apotekaId" :adminView = "false" :registrovanView="registrovan"/>
      </v-container>

    <v-container fluid v-if="showFarmaceuti">
        <TableFarmaceuti :apotekaId = "apotekaId" :adminView = "false"/>
      </v-container>

    <v-container fluid v-if="showDermatolozi">
        <TableDermatolozi :apotekaId = "apotekaId" :adminView = "false"/>
      </v-container>

      <v-container fluid v-if="showPregledi">
        <NoviPregled :apotekaId = "apotekaId" :fromProfilApoteke = "true"/>
      </v-container>

       <v-container fluid v-if="showSavetovanja">
        <NovoSavetovanje/>
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
import TableLekovi from "./TableLekovi";
import TableFarmaceuti from "./TableFarmaceuti";
import TableDermatolozi from "./TableDermatolozi";
import NoviPregled from "./NoviPregled";
import NovoSavetovanje from "./NovoSavetovanje";
import Vue from "vue";


export default{
    name: "ProfilApoteke",
    components: {
        TableLekovi,
        TableFarmaceuti,
        TableDermatolozi,
        NoviPregled,
        NovoSavetovanje,
    },
    data: () => ({
        
        obavestenjeDialog: false,
        showLekovi:false,
        showFarmaceuti:false,
        showDermatolozi:false,
        showPregledi: false,
        showSavetovanja: false,
        message:"",
        apoteka: {
          naziv: "",
          opis: "",
          adresa: "",
          grad: "",
          drzava: "",
      },
    }),
    props :{
        user: {},
        apotekaId: Number,
        registrovan: Boolean,
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
            this.showSavetovanja = false;
        },
        viewFarmaceuti(){
            this.showLekovi = false;
            this.showFarmaceuti = true;
            this.showDermatolozi = false;
            this.showPregledi = false;
            this.showSavetovanja = false;
        },
        viewDermatolozi(){
            this.showLekovi = false;
            this.showFarmaceuti = false;
            this.showDermatolozi = true;
            this.showPregledi = false;
            this.showSavetovanja = false;
        },
        viewPregledi(){
            this.showLekovi = false;
            this.showFarmaceuti = false;
            this.showDermatolozi = false;
            this.showPregledi = true;
            this.showSavetovanja = false;
        },
        viewSavetovanja(){
            this.showLekovi = false;
            this.showFarmaceuti = false;
            this.showDermatolozi = false;
            this.showPregledi = false;
            this.showSavetovanja = true;
        },

        pretplatiSe(){
            Vue.axios.put(`http://localhost:8080/pacijent/pretplata/${this.user.id}/${this.apotekaId}`);
            this.message = "Uspešno ste se pretplatili na dobijanje obaveštenja o akcijama i promocijama apoteke."
            this.obavestenjeDialog=true;
        },
        
        
    },
}
</script>

<style>

</style>