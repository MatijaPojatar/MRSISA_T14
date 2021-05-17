<template>
    <div justify="space-around" align="center">
    <br/>
    <div >
    <v-btn
        class="mx-2"
        fab
        dark
        medium
        color="light-green"
        @click="DodajFarmaceuta"
    >
        <v-icon>
            mdi-plus
        </v-icon>
    </v-btn>

     <v-btn
        class="mx-2"
        fab
        dark
        medium
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
      v-model="pretragaParams.ime"
      :counter="50"
      label="Ime"
    ></v-text-field>

    <v-text-field
      v-model="pretragaParams.prezime"
      :counter="50"
      label="Prezime"
    ></v-text-field>

    <v-text-field
      v-model="pretragaParams.grad"
      :counter="50"
      label="Grad"
    ></v-text-field>

    <v-text-field
      v-model="pretragaParams.drzava"
      :counter="50"
      label="Država"
    ></v-text-field>

    <v-select
      v-model="pretragaParams.pol"
      :items="itemsPol"
      item-text="Pol"
      label="Pol"
    ></v-select>

    

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
    <TableFarmaceuti :apotekaId = "apotekaId" :adminView = "true"/>

    <v-dialog
    v-model="farmaceutAddDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Novi farmaceut
        </v-card-title>
        <AddZaposleniForm :apotekaId="this.apotekaId" :farmaceut="true"/>
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
        <v-card-text>Nije pronađen farmaceut sa zadatim parametrima.</v-card-text>
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
import AddZaposleniForm from "./AddZaposleniForm";
import Vue from "vue";

export default{
    name: "FarmaceutiAdminApoteke",
    components: {
        TableFarmaceuti,
        AddZaposleniForm
    },
    data: () => ({
        farmaceutAddDialog: false,
        obavestenjeDialog: false,
        pretraga: false,
        prikaziPretrazene: false,
        pretragaParams: {
          ime: "",
          prezime: "",
          grad: "",
          drzava: "",
          pol: null,
        },
        itemsPol: [
        'MUSKI',
        'ZENSKI',
      ],
    }),
    props :{
        user: {},
        apotekaId: Number,
    },

    methods:{
         DodajFarmaceuta(){
                this.farmaceutAddDialog = true;
             },
        endDialog(){
                this.farmaceutAddDialog = false;
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
          Vue.axios.post(`http://localhost:8080/farmaceut/pretragaFarmaceuta/${this.apotekaId}`, this.pretragaParams).then(response=>{
                   
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