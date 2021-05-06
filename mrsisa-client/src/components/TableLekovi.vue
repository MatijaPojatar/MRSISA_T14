<template>
  <v-row justify="center">
   
   <v-data-table
    :headers="headers"
    :items="lekovi"
    :sort-by="'naziv'"
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
                @click="ObrisiLek(item)"
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
                @click="IzmeniLek(item)"
            >
                <v-icon>
                    mdi-pencil
                </v-icon>
            </v-btn>
            </div>
    </template>
  </v-data-table>

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
            headers: [
          {
            text: 'Naziv',
            align: 'start',
            value: 'naziv',
          },
          { text: 'Id', value: 'id' },
          { text: 'Proizvođač', value: 'proizvodjac' },
          { text: 'Sastav', value: 'sastav'},
          { text: 'Napomena', value: 'napomena' },
          { text: 'Režim', value: 'rezim' },
          { text: 'Oblik', value: 'oblik'},
          { text: 'Vrsta', value: 'vrsta'},
          { text: 'Cena', value: 'cena'},
          { text: 'Količina', value: 'kolicina'},
          { text: 'Upravljaj', value: 'actions', sortable: false },
        ],
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
        async mounted(){
            await this.loadLekovi();
            if(this.pacijentId){
                await this.loadAlergije();
                this.renderAlergije();
            }
        },
        methods:{
             async loadLekovi(){
                 const lekovi = []
                await axios.get(`http://localhost:8080/lekovi/apoteka/${this.apotekaId}`).then(response => {
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
                                kolicina: element.kolicina,
                                lekId: element.lekId,
                                alergija: 0,
                            })
                            this.lekovi = lekovi
                        })
                    });
             },

             ObrisiLek(lek){
                 this.selektovanLek=Object.assign({}, lek);
                 this.selektovan = this.selektovanLek.id;
                 axios.put(`http://localhost:8080/apoteke/obrisiLek/${this.selektovan}`, this.apotekaId, {headers: {"Content-Type": "text/plain"}})
                 location.reload();
             },

             IzmeniLek(lek){
                 console.log("izmena" + this.selektovan);
                 this.selektovanLek=Object.assign({}, lek);
                 this.selektovan = this.selektovanLek.id;
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