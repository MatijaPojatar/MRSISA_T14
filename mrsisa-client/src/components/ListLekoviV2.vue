<template>
  <v-row justify="center" max-width="100">
    <v-expansion-panels  accordion>
      <v-expansion-panel
        v-for="lek in lekovi"
        :key="lek.id"
      @click = "PanelSelected(lek.id)">
        <v-expansion-panel-header>{{lek.naziv}}</v-expansion-panel-header>
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
         <div>
             Cena leka: {{lek.cena}}
         </div>
         <v-divider></v-divider>
         <div>
             Dostupna kolicina: {{lek.kolicina}}
         </div>
         <br/>
         <div v-if = "adminView">
            <v-btn
                class="mx-2"
                fab
                dark
                small
                color="pink"
                @click="ObrisiLek"
            >
                <v-icon>
                    mdi-delete
                </v-icon>
            </v-btn>

            <v-btn
                class="mx-2"
                fab
                dark
                small
                color="cyan"
                @click="IzmeniLek"
            >
                <v-icon>
                    mdi-pencil
                </v-icon>
            </v-btn>
            
         </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>

    <v-dialog
    v-model="lekUpdateDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Izmeni lek
        </v-card-title>
        <UpdateAddLekApotekeForm :apotekaId="this.apotekaId" :lekUMagacinu="this.selektovanLek"/>
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
 import axios from "axios";
 import UpdateAddLekApotekeForm from "./UpdateAddLekApotekeForm";

    export default{
        data: () => ({
            lekovi: [],
            lekUpdateDialog: false,
            selektovanLek: {},
            selektovan: null,
         }),
        props:{
            apotekaId: Number,
            adminView: Boolean,
            
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
                if (!this.apotekaId){
                    axios.get(`http://localhost:8080/lekovi`).then(response => {
                        
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
                }
                else {
                    axios.get(`http://localhost:8080/lekovi/apoteka/${this.apotekaId}`).then(response => {
                        
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
                                cena: element.cena,
                                kolicina: element.kolicina
                            })
                            this.lekovi = lekovi
                        })
                    });
                }
             },

             PanelSelected(id){
                 this.selektovan = id;
                 console.log(this.selektovan);
                 
             },

             ObrisiLek(){
                 axios.put(`http://localhost:8080/apoteke/obrisiLek/${this.selektovan}`, this.apotekaId, {headers: {"Content-Type": "text/plain"}})
                 location.reload();
             },

             IzmeniLek(){
                 console.log("izmena" + this.selektovan);
                 axios.put(`http://localhost:8080/apoteke/preuzmiLek/${this.selektovan}`, this.apotekaId, {headers: {"Content-Type": "text/plain"}}).then(response => {
                     console.log(response.data)
                    this.selektovanLek=response.data;
                    console.log(this.selektovanLek);
                 this.lekUpdateDialog = true;
                });
                
             },
             endDialog(){
                this.lekUpdateDialog = false;
             },

         }
    }

</script>