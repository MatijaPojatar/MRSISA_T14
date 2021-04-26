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
         <div>
             Početak radnog vremena: {{dermatolog.pocetakRadnogVremena}}
         </div>
         <v-divider></v-divider>
         <div>
             Kraj radnog vremena: {{dermatolog.krajRadnogVremena}}
         </div>
         <br/>
         <div>
            <v-btn
                class="mx-2"
                fab
                dark
                small
                color="pink"
                @click="ObrisiDermatologa"
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
                @click="IzmeniDermatologa"
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
    v-model="dermatologUpdateDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Izmeni dermatologa
        </v-card-title>
        <AccountView :user="this.selektovanDermatolog" :farmaceut="false" :editable="true" :adminView="true"/>
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
 import  AccountView from "./AccountView";

    export default{
        name: "ListDermatolozi",
        data: () => ({
            dermatolozi: [],
            dermatologUpdateDialog: false,
            selektovanDermatolog: {},
            selektovan: null,
         }),
        props:{
            apotekaId: Number,
            adminView: Boolean,
            
        },
        components:{
            AccountView,
        },
        mounted(){
            console.log(this.apotekaId);
            this.loadDermatolozi();
        },
        methods:{
             loadDermatolozi(){
                const dermatolozi = []
                axios.get(`http://localhost:8080/dermatolog/apoteka/${this.apotekaId}`).then(response => {
                        
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
                                pocetakRadnogVremena: element.pocetakRadnogVremenaStr,
                                krajRadnogVremena: element.krajRadnogVremenaStr
                            })
                            this.dermatolozi = dermatolozi
                        })
                    });
             },

             PanelSelected(id){
                 this.selektovan = id;
                 console.log(this.selektovan);
                 
             },

             ObrisiDermatologa(){
                 axios.put(`http://localhost:8080/dermatolog/obrisiDermatologa/${this.selektovan}`, this.apotekaId, {headers: {"Content-Type": "text/plain"}});
                 location.reload();
             },

             IzmeniDermatologa(){
                 console.log("izmena" + this.selektovan);
                 axios.get(`http://localhost:8080/dermatolog/${this.selektovan}`).then(response => {
                     console.log(response.data)
                    this.selektovanDermatolog=response.data;
                    console.log(this.selektovanDermatolog);
                 this.dermatologUpdateDialog = true;
                });
                
             },
             endDialog(){
                this.dermatologUpdateDialog = false;
             },

         }
    }

</script>