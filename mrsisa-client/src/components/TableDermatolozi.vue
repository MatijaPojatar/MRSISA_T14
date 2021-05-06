<template>
  <v-row justify="center">
  <v-data-table
    :headers="headers"
    :items="dermatolozi"
    :sort-by="'ime'"
    :sort-desc="[false, true]"
    multi-sort
    class="elevation-1"
  >
    <template v-slot:item.actions="{ item }">
        <div>
      <v-btn
                class="mx-2"
                fab
                dark
                small
                color="pink"
                @click="ObrisiDermatologa(item)"
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
                @click="IzmeniDermatologa(item)"
            >
                <v-icon>
                    mdi-pencil
                </v-icon>
            </v-btn>
            </div>
    </template>
  </v-data-table>
 

    <v-dialog
    v-model="dermatologUpdateDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Izmeni dermatologa
        </v-card-title>
        <AccountView :user="this.selektovanDermatolog" :farmaceut="false" :editable="false" :adminView="true"/>
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
        name: "TableDermatolozi",
        data: () => ({
            headers: [
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
          { text: 'Upravljaj', value: 'actions', sortable: false },
        ],
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


             ObrisiDermatologa(user){
                 this.selektovanDermatolog=Object.assign({}, user);
                 this.selektovan = this.selektovanDermatolog.id;
                 axios.put(`http://localhost:8080/dermatolog/obrisiDermatologaApoteka/${this.selektovan}`, this.apotekaId, {headers: {"Content-Type": "text/plain"}});
                 location.reload();
             },

             IzmeniDermatologa(user){
                 this.selektovanDermatolog=Object.assign({}, user);
                 this.selektovan = this.selektovanDermatolog.id;
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