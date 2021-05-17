<template>
  <v-row justify="center" max-width="100">
    <v-expansion-panels accordion>
      <v-expansion-panel
        v-for="lek in lekovi"
        :key="lek.id"
      @click = "PanelSelected(lek.id)">
        <v-expansion-panel-header><v-row><div>{{lek.naziv}}</div>
           <v-btn icon x-small  @click="dodajLek(lek)" depressed class="w-25">
                    <v-icon>mdi-plus</v-icon>
                </v-btn>
        </v-row>
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
         <div>
             Cena leka: {{lek.cena}}
         </div>
         <v-divider></v-divider>
         <div>
             Dostupna kolicina: {{lek.kolicina}}
         </div>
         <br/>
         <div>
            <v-btn
                class="mx-2"
                fab
                dark
                small
                color="light-green"
                @click="ObrisiLek"
            >
                <v-icon>
                    mdi-plus
                </v-icon>
            </v-btn>

           
         </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
      <v-divider></v-divider>
      
  </v-row>
</template>

<script>
 import axios from "axios";

    export default{
        data: () => ({
            lekovi: [],
            selektovanLek: {},
            selektovan: null,
            
         }),
        props:{
            apotekaId: Number,
            preporuceniLekovi: {
                type: Array,
                default: function () {
                    return []
                },
            }
            
        },
        components:{
            UpdateAddLekApotekeForm,
        },
        async mounted(){
            await this.loadLekovi();
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

             

         }
    }

</script>