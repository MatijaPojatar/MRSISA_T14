<template>
  <v-row justify="center">
     <v-data-table
    :headers="headers"
    :items="upiti"
    :sort-by="'id'"
    :sort-desc="[false, true]"
    multi-sort
    class="elevation-1"
  >
    
  </v-data-table>

   
  </v-row>
</template>

<script>
 import Vue from "vue";

    export default{
      name: "TableUpiti",
        data: () => ({
            headers: [
          {
            text: 'Id',
            align: 'start',
            value: 'id',
          },
          { text: 'Naziv leka', value: 'nazivLeka' },
          { text: 'Broj upita za lek', value: 'kolicina' },
          
        ],
            upiti: [],
         }),
        props:{
            apotekaId: Number,
            
        },
        mounted(){
            this.loadUpiti();
        },
        methods:{
             loadUpiti(){
                const upiti = []
                Vue.axios.get(`/apoteke/upiti/${this.apotekaId}`).then(response => {
                        
                        response.data.forEach(element => {
                            upiti.push({
                                id: element.id,
                                lekId : element.lekId,
                                nazivLeka: element.nazivLeka,
                                magacinId: element.magacinId,
                                kolicina: element.kolicina,
                                
                            })
                            this.upiti = upiti
                        })
                    });
             },

             

         }
    }

</script>