<template>
  <v-row justify="center" max-width="100">
    <v-expansion-panels  accordion>
      <v-expansion-panel
        v-for="dermatolog in dermatolozi"
        :key="dermatolog.id"
      @click = "PanelSelected(dermatolog.id)">
        <v-expansion-panel-header>{{dermatolog.ime}} {{dermatolog.prezime}}</v-expansion-panel-header>
        <v-expansion-panel-content>
         <div>
             Mail: {{dermatolog.mail}}
         </div>  
         <v-divider></v-divider> 
         <div>
             Adresa: {{dermatolog.adresa}}
         </div>
         <v-divider></v-divider>
         <div>
             Grad: {{dermatolog.grad}}
         </div>
         <v-divider></v-divider>
         <div>
             Država: {{dermatolog.drzava}}
         </div>
         <v-divider></v-divider>
         <div>
             Broj telefona: {{dermatolog.brojTelefona}}
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
        @click="DodajDermatologa"
        >
        <v-icon>
            mdi-plus
        </v-icon>
        </v-btn>
        </div>
         
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>

    <v-dialog
    v-model="dermatologAddRadnoVremeDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Radno vreme
        </v-card-title>
        <AddRadnoVremeForm :userId="this.selektovan" :apotekaId="this.apotekaId"/>
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
 import  AddRadnoVremeForm from "./AddRadnoVremeForm";

    export default{
        name: "ListDermatoloziAddSearch",
        data: () => ({
            dermatolozi: [],
            dermatologAddRadnoVremeDialog: false,
            selektovanDermatolog: {},
            selektovan: null,
         }),
        props:{
            apotekaId: Number,
            
        },
        components:{
            AddRadnoVremeForm,
        },
        mounted(){
            console.log(this.apotekaId);
            this.loadDermatolozi();
        },
        methods:{
             loadDermatolozi(){
                const dermatolozi = []
                axios.get(`http://localhost:8080/dermatolog/vanApoteka/${this.apotekaId}`).then(response => {
                        
                        response.data.forEach(element => {
                            dermatolozi.push({
                                id: element.id,
                                ime : element.ime,
                                prezime: element.prezime,
                                mail: element.mail,
                                adresa: element.adresa,
                                grad: element.grad,
                                drzava: element.drzava,
                                brojTelefona: element.brojTelefona, 
                                pol: element.pol,
                                datumRodjenja: element.datumRodjenja,
                            })
                            this.dermatolozi = dermatolozi
                        })
                    });
             },

             PanelSelected(id){
                 this.selektovan = id;
                 console.log(this.selektovan);
                 
             },
             DodajDermatologa(){
                this.dermatologAddRadnoVremeDialog = true;
             },

            
             endDialog(){
                this.dermatologAddRadnoVremeDialog = false;
             },

         }
    }

</script>