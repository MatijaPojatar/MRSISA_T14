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
  </v-row>
</template>

<script>
 import axios from "axios";

    export default{
        data: () => ({
            lekovi: [],
            selektovan: null,
         }),
        props:{
            apotekaId: Number,
            adminView: Boolean
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
                 console.log(id);
                 this.selektovan = id;
             },

             ObrisiLek(){
                 console.log("brisanje");
                 axios.put(`http://localhost:8080/apoteke/obrisiLek/${this.selektovan}`, this.apotekaId, {headers: {"Content-Type": "text/plain"}})
                 location.reload();
             },

             IzmeniLek(){
                 console.log("izmena");
             },

         }
    }

</script>