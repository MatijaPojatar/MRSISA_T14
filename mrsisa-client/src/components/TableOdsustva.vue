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

    <template v-slot:item.actionOdbij="{ item }">
        <div>
      

            <v-btn
                class="mx-2"
                @click="dodajRazlog(item)"
            >
                Odbij
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

   <v-dialog
      v-model="dialogRazlog"
      persistent
      max-width="400"
    >
      <v-card>
        <v-card-title class="headline">
          Definisanje razloga odbijanja zahteva
        </v-card-title>
        <v-form
            ref="form"
            v-model="valid"
            lazy-validation
        >
            <v-textarea
            v-model="razlog"
            :rules="requiredRules"
            label="Razlog odbijanja: "
            required
        ></v-textarea>

            

            <v-btn
            class="mr-4"
            @click="odbij"
            >
            Ok
            </v-btn>
        </v-form>
        
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
          dialogRazlog: false,
          razlog: "",
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
          { text: 'Prihvati', value: 'actions', sortable: false },
          { text: 'Odbij', value: 'actionOdbij', sortable: false },
        ],
            zahtevi: [],
            selektovanZahtev: {},
            valid: true,
            requiredRules: [
                v => !!v || 'Obavezno polje',
            ],
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
                    Vue.axios.get(`/odsustvo/farmaceut/zaOdobrenje/${this.apotekaId}`).then(response => {
                        
                        response.data.forEach(element => {
                            zahtevi.push({
                                id: element.id,
                                apotekaId: element.apotekaId,
                                farmaceutId: element.farmaceutId,
                                pocetak: element.pocetak,
                                kraj: element.kraj,
                                status: element.status,
                                mail: element.mail,
                                pocetakStr: element.pocetakStr,
                                krajStr: element.krajStr,
                                razlog: element.razlog,
	
                                
                            })
                            this.zahtevi = zahtevi
                        })
                    });
                }else{
                    Vue.axios.get(`/odsustvo/dermatolog/zaOdobrenje`).then(response => {
                        
                        response.data.forEach(element => {
                            zahtevi.push({
                                id: element.id,
                                apotekaId: element.apotekaId,
                                farmaceutId: element.farmaceutId,
                                pocetak: element.pocetak,
                                kraj: element.kraj,
                                status: element.status,
                                mail: element.mail,
                                pocetakStr: element.pocetakStr,
                                krajStr: element.krajStr,
                                razlog: element.razlog,
	
                                
                            })
                            this.zahtevi = zahtevi
                        })
                    });
                }
                
             },
             odobri(item){
                this.selektovanZahtev=Object.assign({}, item);
                if (this.farmaceut){
                    Vue.axios.put(`/odsustvo/farmaceut/zaOdobrenje/${this.selektovanZahtev.id}`)
                }
                else{
                   Vue.axios.put(`/odsustvo/dermatolog/zaOdobrenje/${this.selektovanZahtev.id}`)
                }
                this.message = "Zahtev za odmor je odobren."
                
                this.dialog = true;
             },
             dodajRazlog(item){
                this.selektovanZahtev=Object.assign({}, item);
                this.razlog = "";
                this.dialogRazlog = true;
             },

             odbij(){
               if (this.$refs.form.validate()){
                this.selektovanZahtev.razlog = this.razlog;
                  if (this.farmaceut){
                      Vue.axios.put(`/odsustvo/farmaceut/zaOdbijanje/${this.selektovanZahtev.id}`,  this.selektovanZahtev)
                  }
                  else{
                    Vue.axios.put(`/odsustvo/dermatolog/zaOdbijanje/${this.selektovanZahtev.id}`, this.selektovanZahtev)
                  }
                  this.dialogRazlog = false;

                  this.message = "Zahtev za odmor je odbijen."
                  
                  this.dialog = true;
               }
             },
             endDialog(){
                 const index=this.zahtevi.findIndex((l)=>l.id==this.selektovanZahtev.id)
                if(index !=-1){
                    this.zahtevi.splice(index, 1);
                }
                 this.dialog = false;
             },
             

         }
    }

</script>