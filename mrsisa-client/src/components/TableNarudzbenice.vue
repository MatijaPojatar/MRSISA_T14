<template>
  <v-container justify="center">
  <div>
  <v-card>
    <v-radio-group
    label="Status:"
      v-model="radio"
      @change="filtriraj()"
      row
    >
      <v-radio
        label="čeka ponude"
        value="CEKA_PONUDE"
      ></v-radio>
      <v-radio
        label="obrađene"
        value="OBRADJENA"
        
      ></v-radio>
      <v-radio
        label="sve"
        value="SVE"
      ></v-radio>
    </v-radio-group>
    </v-card>
    </div>
    <br/>
    <br/>
    <div>
     <v-data-table
    :headers="headers"
    :items="narudzbenice"
    :sort-by="'id'"
    :sort-desc="[false, true]"
    multi-sort
    class="elevation-1"
  >

  <template v-slot:item.lekoviActions="{ item }">
        <div>
      <v-btn
                class="mx-2"
                @click="prikaziLekove(item)"
            >
                <v-icon>
                    mdi-pill
                </v-icon>
            </v-btn>
            </div>
    </template>

    <template v-slot:item.ponudeActions="{ item }">
        <div>
      <v-btn
                class="mx-2"
                @click="prikaziPonude(item)"
            >
                <v-icon>
                    mdi-text-box-search-outline
                </v-icon>
            </v-btn>
            </div>
    </template>
    
  </v-data-table>

   </div>

    <v-dialog
    v-model="ponudeDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Ponude
        </v-card-title>
        <TablePonude :apotekaId="this.apotekaId" :narudzbenicaId="this.selektovana" :key="this.componentKey"/>
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

  </v-container>
</template>

<script>
import TablePonude from "./TablePonude";
import Vue from "vue";

    export default{
      name: "TableNarudzbenice",
        data: () => ({
            componentKey: 0,
            ponudeDialog: false,
            selektovanaNarudzbenica: {},
            selektovana: "",
            radio: "SVE",
            headers: [
          {
            text: 'Id',
            align: 'start',
            value: 'id',
          },
          { text: 'Rok za ponude', value: 'rokStr' },
          { text: 'Status', value: 'statusStr' },
          { text: 'Lekovi', value: 'lekoviActions', sortable: false },
          { text: 'Ponude', value: 'ponudeActions', sortable: false },
        ],
            narudzbenice: [],
         }),
        props:{
            apotekaId: Number,
            
        },
        components:{
            TablePonude,
        },
        mounted(){
            this.loadNarudzbenice();
        },
        methods:{
             loadNarudzbenice(){
                const narudzbenice = []
                Vue.axios.get(`http://localhost:8080/narudzbenice/apoteka/${this.apotekaId}`).then(response => {
                        
                        response.data.forEach(element => {
                            narudzbenice.push({
                                id: element.id,
                                magacinId : element.magacinId,
                                rok: element.rok,
                                status: element.status,
                                rokStr: element.rokStr,
                                statusStr: element.statusStr
                                
                                
                            })
                            this.narudzbenice = narudzbenice
                        })
                    });
             },
             prikaziLekove(item){
                 console.log(item);
             },
             prikaziPonude(item){
                 this.selektovanaNarudzbenica=Object.assign({}, item);
                 this.selektovana = this.selektovanaNarudzbenica.id;
                 console.log(this.selektovana);
                 this.componentKey += 1
                 this.ponudeDialog = true;
             },
             endDialog(){
                 this.ponudeDialog = false;
             },
             filtriraj(){
                 console.log(this.radio)
                 if (this.radio != "SVE"){
                     this.prikaziPoStatusu()
                 }
                 else{
                     this.loadNarudzbenice()
                 }
             },
             prikaziPoStatusu(){
                const narudzbenice = []
                Vue.axios.post(`http://localhost:8080/narudzbenice/statusApoteka/${this.apotekaId}`, this.radio, {headers: {"Content-Type": "text/plain"}}).then(response => {
                        
                        response.data.forEach(element => {
                            narudzbenice.push({
                                id: element.id,
                                magacinId : element.magacinId,
                                rok: element.rok,
                                status: element.status,
                                rokStr: element.rokStr,
                                statusStr: element.statusStr
                                
                            })
                            this.narudzbenice = narudzbenice
                            console.log("bla bla bla")
                        })
                    });
             }

             

         }
    }

</script>