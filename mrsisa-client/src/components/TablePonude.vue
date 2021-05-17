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
  <template v-slot:item.actions="{ item }">
        <div>
      <v-btn
                class="mx-2"
                color="light-green"
                :disabled="obradjena"
                @click="odaberiPonudu(item)"
            >
                Odaberi
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
      name: "TablePonude",
        data: () => ({
          
          dialog: false,
          odabrana: {},
          message: "",
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
          { text: 'Upravljaj', value: 'actions', sortable: false },
        ],
            ponude: [],
         }),
        props:{
            userId: Number,
            apotekaId: Number,
            narudzbenica: {},
            obradjena: Boolean,
            
        },
        mounted(){
            this.loadPonude();
        },
        methods:{
             loadPonude(){
                const ponude = []
                Vue.axios.get(`http://localhost:8080/narudzbenice/ponude/${this.narudzbenica.id}`).then(response => {
                        
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
             odaberiPonudu(item){
                this.odabrana=Object.assign({}, item);
                let now = new Date();
                if (this.userId != this.narudzbenica.adminId){
                  this.message="Nije moguće odabrati ponudu za narudžbinu kreiranu od strane drugog administratora apoteke.";
                  this.dialog = true;
                }else if (this.narudzbenica.rok > now){
                  this.message="Rok za slanje ponuda još uvek nije istekao.";
                  this.dialog = true;
                }
                else{
                   Vue.axios.post(`http://localhost:8080/narudzbenice/prihvatiPonudu/${this.odabrana.id}`, this.narudzbenica.id, {headers: {"Content-Type": "text/plain"}})
                   location.reload();
                }
             },
             endDialog(){
                 this.dialog = false;
             },
             getCurrentTime () {
              return this.cal ? this.cal.times.now.hour * 60 + this.cal.times.now.minute : 0
            },

         }
    }

</script>