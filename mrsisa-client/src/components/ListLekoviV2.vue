<template>
  <v-row justify="center" max-width="100">
    <v-expansion-panels accordion>
      <v-expansion-panel
        v-for="lek in lekovi"
        :key="lek.id"
      >
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
         }),
        props:{
            apotekaId: Number,
        },
        mounted(){
            console.log(this.apotekaId);
            this.loadLekovi();
        },
        methods:{
             loadLekovi(){
                axios.get(`http://localhost:8080/lekovi/apoteka/${this.apotekaId}`).then(response => {
                const lekovi = []
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
         }
    }

</script>