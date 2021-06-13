<template>
  <v-row justify="center" max-width="100">
    <v-expansion-panels  accordion>
      <v-expansion-panel
        v-for="lek in lekovi"
        :key="lek.id"
      @click = "PanelSelected(lek.id)">
        <v-expansion-panel-header>{{lek.naziv}}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
         <div>
             Proizvodjac: {{lek.proizvodjac}}
         </div>  
         <v-divider></v-divider> 
         <div>
             Sastav: {{lek.sastav}}
         </div>
         <v-divider></v-divider>
         <div>
             Napomena: {{lek.napomena}}
         </div>
         <v-divider></v-divider>
         <div>
             Rezim izdavanja: {{lek.rezim}}
         </div>
         <v-divider></v-divider>
         <div>
             Oblik leka: {{lek.oblik}}
         </div>
         <v-divider></v-divider>
         <div>
             Vrsta leka: {{lek.vrsta}}
         </div>
         <v-divider></v-divider>
         
         <br/>
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
    </div>
         
        </v-expansion-panel-content>
      </v-expansion-panel>
      <v-divider></v-divider>
      
    </v-expansion-panels>

    <v-dialog
    v-model="lekAddDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Dodaj lek
        </v-card-title>
        <UpdateAddLekApotekeForm :apotekaId="this.apotekaId" :lekToAddId="this.selektovan" :addMode="true"/>
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
    
  </v-row>
</template>

<script>
 import Vue from "vue";
 import UpdateAddLekApotekeForm from "./UpdateAddLekApotekeForm";

    export default{
        name: "ListLekoviToAdd",
        data: () => ({
            lekovi: [],
            lekAddDialog: false,
            selektovanLek: {},
            selektovan: null,
            obavestenjeDialog: false,
           
            valid: true,
            
         }),
        props:{
            apotekaId: Number,
           
            
        },
        components:{
            UpdateAddLekApotekeForm,
        },
        mounted(){
            console.log(this.apotekaId);
            this.loadLekovi();
        },
        methods:{
             loadLekovi(){
                const lekovi = []
                Vue.axios.get(`http://localhost:8080/lekovi/vanApoteke/${this.apotekaId}`).then(response => {
                        
                        response.data.forEach(element => {
                            lekovi.push({
                                naziv: element.naziv,
                                id: element.id,
                                proizvodjac : element.proizvodjac,
                                sastav: element.sastav,
                                napomena: element.napomena,
                                rezim: element.rezimIzdavanja,
                                oblik: element.oblikLeka,
                                vrsta: element.vrstaLeka, 
                            })
                            this.lekovi = lekovi
                        })
                    });
             },

             PanelSelected(id){
                 this.selektovan = id;
                 console.log(this.selektovan);
                 
             },
              DodajLek(){
                this.lekAddDialog = true;
             },
             endDialog(){
                this.lekAddDialog = false;
             },
            
             

         }
    }

</script>