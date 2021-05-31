<template>
  <v-row justify="center">
  <v-data-table
    :headers="headers"
    :items="farmaceuti"
    :sort-by="'ime'"
    :sort-desc="[false, true]"
    multi-sort
    class="elevation-1"
    v-if="!adminView"
  >
  </v-data-table>


     <v-data-table
    :headers="headersAdmin"
    :items="farmaceuti"
    :sort-by="'ime'"
    :sort-desc="[false, true]"
    multi-sort
    class="elevation-1"
    v-if="adminView"
  >
    <template v-slot:item.actions="{ item }">
        <div>
      <v-btn
                class="mx-2"
                fab
                dark
                small
                color="pink"
                @click="ObrisiFarmaceuta(item)"
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
                @click="IzmeniFarmaceuta(item)"
            >
                <v-icon>
                    mdi-pencil
                </v-icon>
            </v-btn>
            </div>
    </template>
  </v-data-table>

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
 import axios from "axios";
 import  AccountView from "./AccountView";

    export default{
      name: "TableFarmaceuti",
        data: () => ({
            headersAdmin: [
          {
            text: 'Ime',
            align: 'start',
            value: 'ime',
          },
          { text: 'Prezime', value: 'prezime' },
          { text: 'Mail', value: 'mail' },
          { text: 'Broj telefona', value: 'brojTelefona'},
          { text: 'Adresa', value: 'adresa'},
          { text: 'Grad', value: 'grad' },
          { text: 'Država', value: 'drzava' },
          { text: 'Pol', value: 'pol'},
          { text: 'Početak radnog vremena', value: 'pocetakRadnogVremena'},
          { text: 'Kraj radnog vremena', value: 'krajRadnogVremena'},
          { text: 'Ocena', value: 'ocena'},
          { text: 'Upravljaj', value: 'actions', sortable: false },
        ],

        headers: [
          {
            text: 'Ime',
            align: 'start',
            value: 'ime',
          },
          { text: 'Prezime', value: 'prezime' },
          { text: 'Grad', value: 'grad' },
          { text: 'Država', value: 'drzava' },
          { text: 'Pol', value: 'pol'},
          { text: 'Početak radnog vremena', value: 'pocetakRadnogVremena'},
          { text: 'Kraj radnog vremena', value: 'krajRadnogVremena'},
          { text: 'Ocena', value: 'ocena'},
        ],
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
                axios.get(`http://localhost:8080/farmaceut/apoteka/${this.apotekaId}`).then(response => {
                        
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
                                krajRadnogVremena: element.krajRadnogVremenaStr,
                                ocena: element.ocena,
                            })
                            this.farmaceuti = farmaceuti
                        })
                    });
             },

             ObrisiFarmaceuta(user){
                 this.selektovanFarmaceut=Object.assign({}, user);
                 this.selektovan = this.selektovanFarmaceut.id;
                 axios.put(`http://localhost:8080/farmaceut/obrisiFarmaceuta/${this.selektovan}`)
                 location.reload();
             },

             IzmeniFarmaceuta(user){
                 console.log("izmena" + this.selektovan);
                 this.selektovanFarmaceut=Object.assign({}, user);
                 this.selektovan = this.selektovanFarmaceut.id;
                 axios.get(`http://localhost:8080/farmaceut/${this.selektovan}`).then(response => {
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