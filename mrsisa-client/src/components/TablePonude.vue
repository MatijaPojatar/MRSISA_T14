<template>
  <v-container justify="center">
 
    <div>
     <v-data-table
    :headers="headers"
    :items="ponude"
    :sort-by="'id'"
    :sort-desc="[false, true]"
    multi-sort
    class="elevation-1"
  >


    
  </v-data-table>

   </div>
  </v-container>
</template>

<script>
 import Vue from "vue";

    export default{
      name: "TablePonude",
        data: () => ({
            headers: [
          {
            text: 'Id',
            align: 'start',
            value: 'id',
          },
          { text: 'Dobavljač', value: 'nazivDobavljaca' },
          { text: 'Rok isporuke', value: 'rokStr' },
          { text: 'Cena', value: 'cena' },
          { text: 'Status', value: 'status' },
        ],
            ponude: [],
         }),
        props:{
            apotekaId: Number,
            narudzbenicaId: Number
            
        },
        mounted(){
            this.loadPonude();
        },
        methods:{
             loadPonude(){
                const ponude = []
                Vue.axios.get(`http://localhost:8080/narudzbenice/ponude/${this.narudzbenicaId}`).then(response => {
                        
                        response.data.forEach(element => {
                            ponude.push({
                                id: element.id,
                                cena: element.cena,
                                dobavljacId: element.dobavljacId,
                                narudzbenicaId: element.narudzbenicaId,
                                rokIsporuke: element.rokIsporuke,
                                status: element.status,
                                rokStr: element.rokStr,
                                nazivDobavljaca: element.nazivDobavljaca,
	
                                
                            })
                            this.ponude = ponude
                        })
                    });
             },

         }
    }

</script>