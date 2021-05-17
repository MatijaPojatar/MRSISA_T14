<template>
  <v-row justify="center">
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

   
  </v-row>
</template>

<script>
 import axios from "axios";

    export default{
      name: "TableNarudzbenice",
        data: () => ({
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
                axios.get(`http://localhost:8080/apoteke/narudzbenice/${this.apotekaId}`).then(response => {
                        
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

             

         }
    }

</script>