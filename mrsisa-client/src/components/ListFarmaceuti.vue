<template>
  <v-row justify="center" max-width="100">
    <v-expansion-panels  accordion>
      <v-expansion-panel
        v-for="farmaceut in farmaceuti"
        :key="farmaceut.id"
      @click = "PanelSelected(farmaceut.id)">
        <v-expansion-panel-header>{{farmaceut.ime}} {{farmaceut.prezime}}</v-expansion-panel-header>
        <v-expansion-panel-content>
         <div>
             Mail: {{farmaceut.mail}}
         </div>  
         <v-divider></v-divider> 
         <div>
             Adresa: {{farmaceut.adresa}}
         </div>
         <v-divider></v-divider>
         <div>
             Grad: {{farmaceut.grad}}
         </div>
         <v-divider></v-divider>
         <div>
             Država: {{farmaceut.drzava}}
         </div>
         <v-divider></v-divider>
         <div>
             Broj telefona: {{farmaceut.brojTelefona}}
         </div>
         <v-divider></v-divider>
         
         <div>
             Početak radnog vremena: {{farmaceut.pocetakRadnogVremena}}
         </div>
         <v-divider></v-divider>
         <div>
             Kraj radnog vremena: {{farmaceut.krajRadnogVremena}}
         </div>
         <br/>
         <div>
            <v-btn
                class="mx-2"
                fab
                dark
                small
                color="pink"
                @click="ObrisiFarmaceuta"
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
                @click="IzmeniFarmaceuta"
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
    v-model="farmaceutUpdateDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Izmeni farmaceuta
        </v-card-title>
        <AccountView :user="this.selektovanFarmaceut" :farmaceut="true" :editable="false" :adminView="true"/>
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
 import  AccountView from "./AccountView";

    export default{
        data: () => ({
            farmaceuti: [],
            farmaceutUpdateDialog: false,
            selektovanFarmaceut: {},
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
            this.loadFarmaceuti();
        },
        methods:{
             loadFarmaceuti(){
                const farmaceuti = []
                Vue.axios.get(`/farmaceut/apoteka/${this.apotekaId}`).then(response => {
                        
                        response.data.forEach(element => {
                            farmaceuti.push({
                                id: element.id,
                                ime : element.ime,
                                prezime: element.prezime,
                                mail: element.mail,
                                adresa: element.adresa,
                                grad: element.grad,
                                drzava: element.drzava,
                                brojTelefona: element.brojTelefona, 
                                pol: element.pol,
                                pocetakRadnogVremena: element.pocetakRadnogVremenaStr,
                                krajRadnogVremena: element.krajRadnogVremenaStr
                            })
                            this.farmaceuti = farmaceuti
                        })
                    });
             },

             PanelSelected(id){
                 this.selektovan = id;
                 console.log(this.selektovan);
                 
             },

             ObrisiFarmaceuta(){
                 Vue.axios.put(`/farmaceut/obrisiFarmaceuta/${this.selektovan}`)
                 location.reload();
             },

             IzmeniFarmaceuta(){
                 console.log("izmena" + this.selektovan);
                 Vue.axios.get(`/farmaceut/${this.selektovan}`).then(response => {
                     console.log(response.data)
                    this.selektovanFarmaceut=response.data;
                    console.log(this.selektovanFarmaceut);
                 this.farmaceutUpdateDialog = true;
                });
                
             },
             endDialog(){
                this.farmaceutUpdateDialog = false;
             },

         }
    }

</script>