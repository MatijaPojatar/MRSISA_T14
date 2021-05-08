

<template>
    <div justify="center">
    <div>
    <v-btn
        class="mx-2"
        fab
        dark
        small
        color="light-green"
        @click="DodajLek"
    >
        <v-icon>
            mdi-plus
        </v-icon>
    </v-btn>

    <v-btn
        class="mx-2"
        fab
        dark
        small
        color="light-blue"
        @click="showPretrazi"
    >
        <v-icon>
            mdi-magnify
        </v-icon>
    </v-btn>

    </div>
    <br/>
    <br/>
    <v-card width="700" v-if="this.pretraga">
    
    <v-form 
    ref="form"
    justify="center"
  >
    <v-text-field
      v-model="pretragaParams.sifra"
      :counter="50"
      label="Šifra"
    ></v-text-field>

    <v-text-field
      v-model="pretragaParams.naziv"
      :counter="50"
      label="Naziv"
    ></v-text-field>

    <v-select
      v-model="pretragaParams.rezim"
      :items="itemsRezim"
      item-text="Režim izdavanja"
      label="Režim izdavanja"
    ></v-select>

    <v-select
      v-model="pretragaParams.oblik"
      :items="itemsOblik"
      item-text="Oblik leka"
      label="Oblik leka"
    ></v-select>

    <v-select
      v-model="pretragaParams.vrsta"
      :items="itemsVrsta"
      item-text="Vrsta leka"
      label="Vrsta leka"
    ></v-select>

    <v-text-field
      v-model="pretragaParams.proizvodjac"
      :counter="50"
      label="Proizvođač"
      
    ></v-text-field>

    <v-btn
      class="mr-4"
      @click="pretrazi"
    >
      Pretraži
    </v-btn>

    
  </v-form>
  </v-card>
  <br/>
  <br/>

    <TableLekovi :apotekaId = "apotekaId" :adminView = "true"/>

    <v-dialog
    v-model="lekAddDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Novi lek
        </v-card-title>
        <ListLekoviToAdd :apotekaId="this.apotekaId"/>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endDialog"
          >
            Povratak
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog
      v-model="obavestenjeDialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>Nije pronađen lek sa zadatim parametrima u magacinu.</v-card-text>
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
import ListLekoviToAdd from "./ListLekoviToAdd";
import Vue from "vue";

export default{
    name: "LekoviAdminApoteke",
    components: {
        TableLekovi,
        ListLekoviToAdd
    },
    data: () => ({
        lekAddDialog: false,
        obavestenjeDialog: false,
        pretraga: false,
        prikaziPretrazene: false,
        pretragaParams: {
          sifra: "",
          naziv: "",
          rezim: null,
          oblik: null,
          vrsta: null,
          proizvodjac: "",
        },
        itemsRezim: [
        'NA_RECEPT',
        'BEZ_RECEPTA'
        ],

        itemsOblik: [
        'PRASAK',
        'KAPSULA',
        'TABLETA',
        'MAST',
        'PASTA',
        'GEL',
        'RASTVOR',
        'SIRUP'
        ],

        itemsVrsta: [
        'ANTIBIOTIK',
        'ANTIMIKOTIK'
        ],
    }),
    props :{
        user: {},
        apotekaId: Number,
    },

    methods:{
         DodajLek(){
            this.lekAddDialog = true;
        },
        endDialog(){
            this.lekAddDialog = false;
        },
        endObavestenjeDialog(){
            this.obavestenjeDialog = false;
        },
        showPretrazi(){
          if(!this.pretraga)
            this.pretraga = true;
          else{
            this.pretraga = false;
          }
        },
        pretrazi(){
          Vue.axios.post(`http://localhost:8080/lekovi/pretragaLekova/${this.apotekaId}`, this.pretragaParams).then(response=>{
                   
                     const uspesno = response.data
                     if (uspesno){
                       location.reload()
                     }else{
                       this.obavestenjeDialog=true
                     }
                    
                 })
          
        },
        
    },
}
</script>

<style>

</style>