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
        label="čeka obradu"
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
  </v-container>
</template>

<script>
 import Vue from "vue";

    export default{
      name: "TableNarudzbenice",
        data: () => ({
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
        mounted(){
            this.loadNarudzbenice();
        },
        methods:{
             loadNarudzbenice(){
                const narudzbenice = []
                Vue.axios.get(`http://localhost:8080/apoteke/narudzbenice/${this.apotekaId}`).then(response => {
                        
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
                 console.log(item);
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
                Vue.axios.post(`http://localhost:8080/apoteke/narudzbeniceStatus/${this.apotekaId}`, this.radio, {headers: {"Content-Type": "text/plain"}}).then(response => {
                        
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