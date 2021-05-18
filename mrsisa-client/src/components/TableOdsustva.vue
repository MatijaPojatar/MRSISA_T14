<template>
  <v-container justify="center">
 
    <div>
     <v-data-table
    :headers="headers"
    :items="zahtevi"
    :sort-by="'id'"
    :sort-desc="[false, true]"
    multi-sort
    class="elevation-1"
  >
  <template v-slot:item.actions="{ item }">
        <div>
      <v-btn
                class="mx-2"
                color="light-green"
                @click="odobri(item)"
            >
                Odobri
            </v-btn>
            </div>
    </template>

    
  </v-data-table>

   </div>

   <v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>{{message}}</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>
  </v-container>
</template>

<script>
 import Vue from "vue";

    export default{
      name: "TableOdsustva",
        data: () => ({
          
          dialog: false,
          message: "",
            headers: [
          {
            text: 'Id',
            align: 'start',
            value: 'id',
          },
          
          { text: 'Zaposleni', value: 'mail' },
          { text: 'Početak', value: 'pocetakStr' },
          { text: 'Kraj', value: 'krajStr' },
          { text: 'Upravljaj', value: 'actions', sortable: false },
        ],
            zahtevi: [],
            odobrenZahtev: {},
         }),
        props:{
            apotekaId: Number,
            farmaceut: Boolean,
            
        },
        mounted(){
            this.loadZahtevi();
        },
        methods:{
             loadZahtevi(){
                const zahtevi = []
                if (this.farmaceut){
                    Vue.axios.get(`http://localhost:8080/odsustvo/farmaceut/zaOdobrenje/${this.apotekaId}`).then(response => {
                        
                        response.data.forEach(element => {
                            zahtevi.push({
                                id: element.id,
                                apotekaId: element.apotekaId,
                                farmaceutId: element.farmaceutId,
                                pocetak: element.pocetak,
                                kraj: element.kraj,
                                odobren: element.odobren,
                                mail: element.mail,
                                pocetakStr: element.pocetakStr,
                                krajStr: element.krajStr,
                                
	
                                
                            })
                            this.zahtevi = zahtevi
                        })
                    });
                }else{
                    Vue.axios.get(`http://localhost:8080/odsustvo/dermatolog/zaOdobrenje`).then(response => {
                        
                        response.data.forEach(element => {
                            zahtevi.push({
                                id: element.id,
                                apotekaId: element.apotekaId,
                                farmaceutId: element.farmaceutId,
                                pocetak: element.pocetak,
                                kraj: element.kraj,
                                odobren: element.odobren,
                                mail: element.mail,
                                pocetakStr: element.pocetakStr,
                                krajStr: element.krajStr,
                                
	
                                
                            })
                            this.zahtevi = zahtevi
                        })
                    });
                }
                
             },
             odobri(item){
                this.odobrenZahtev=Object.assign({}, item);
                if (this.farmaceut){
                    Vue.axios.put(`http://localhost:8080/odsustvo/farmaceut/zaOdobrenje/${this.odobrenZahtev.id}`)
                }
                else{
                   Vue.axios.put(`http://localhost:8080/odsustvo/dermatolog/zaOdobrenje/${this.odobrenZahtev.id}`)
                }
                this.message = "Zahtev za odmor je odobren."
                
                this.dialog = true;
             },
             endDialog(){
                 const index=this.zahtevi.findIndex((l)=>l.id==this.odobrenZahtev.id)
                if(index !=-1){
                    this.zahtevi.splice(index, 1);
                }
                 this.dialog = false;
             },
             

         }
    }

</script>