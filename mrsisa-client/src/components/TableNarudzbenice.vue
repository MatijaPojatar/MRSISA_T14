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

    <template v-slot:item.narudzbenicaActions="{ item }">
        <div>
      <v-btn
                class="mx-2"
                fab
                dark
                small
                color="pink"
                @click="ObrisiNarudzbenicu(item)"
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
                @click="IzmeniNarudzbenicu(item)"
            >
                <v-icon>
                    mdi-pencil
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
        <TablePonude :apotekaId="this.apotekaId" :narudzbenica="this.selektovanaNarudzbenica" :key="this.componentKey" :userId="this.userId" :obradjena="this.obradjena"/>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endPonudeDialog"
          >
            Povratak
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

     <v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Lekovi
        </v-card-title>
        <v-textarea
          v-model="this.lekoviStr"
          readonly
        ></v-textarea>
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
      v-model="obavestenje"
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
            @click="endObavestenje"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>

  <v-dialog
      v-model="izmenaDialog"
      persistent
      max-width="400"
    >
      <v-card>
        <v-card-title class="headline">
          Izmena roka
        </v-card-title>
        <v-form
            ref="form"
            lazy-validation
        >

        <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        transition="scale-transition"
        offset-y
        min-width="auto"
    >
        <template v-slot:activator="{ on, attrs }">
        
        <v-text-field
            v-model="definisanRok"
            label="Rok za ponude:"
            prepend-icon="mdi-calendar"
            readonly
            required
            v-bind="attrs"
            v-on="on"
        ></v-text-field>
        
        </template>
        <v-date-picker
        ref="picker"
        v-model="definisanRok"
        
        :min="new Date().toISOString().substr(0, 10)"
        @change="$refs.menu.save(definisanRok)"
        ></v-date-picker>
        
    </v-menu>
            

            <v-btn
            class="mr-4"
            @click="izmenaRoka"
            >
            Ok
            </v-btn>
        </v-form>
        
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
            obradjena: false,
            lekoviStr: "",
            componentKey: 0,
            ponudeDialog: false,
            dialog: false,
            obavestenje: false,
            selektovanaNarudzbenica: {},
            selektovana: "",
            radio: "SVE",
            headers: [
          {
            text: 'Id',
            align: 'start',
            value: 'id',
          },
          { text: 'Administrator', value: 'adminMail' },
          { text: 'Rok za ponude', value: 'rokStr' },
          { text: 'Status', value: 'statusStr' },
          { text: 'Lekovi', value: 'lekoviActions', sortable: false },
          { text: 'Ponude', value: 'ponudeActions', sortable: false },
          { text: 'Upravljaj', value: 'narudzbenicaActions', sortable: false },
        ],
            narudzbenice: [],
            message: {},
            menu: false,
            definisanRok: null,
            izmenaDialog: false,
         }),
        props:{
            apotekaId: Number,
            userId: Number,
            
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
                Vue.axios.get(`/narudzbenice/apoteka/${this.apotekaId}`).then(response => {
                        
                        response.data.forEach(element => {
                            narudzbenice.push({
                                id: element.id,
                                magacinId : element.magacinId,
                                rok: new Date(element.rok[0].toString()+"-"+element.rok[1].toString()+"-"+element.rok[2].toString()),
                                status: element.status,
                                rokStr: element.rokStr,
                                statusStr: element.statusStr,
                                adminId:element.adminId,
                                adminMail:element.adminMail,
                                bezPonuda: element.bezPonuda,
                                
                            })
                            this.narudzbenice = narudzbenice
                        })
                    });
             },
             prikaziLekove(item){
                 console.log(item);
                 Vue.axios.get(`/narudzbenice/lekovi/${item.id}`).then(response => {
                    this.lekoviStr=response.data;
                    this.dialog = true
                 });
             },
             prikaziPonude(item){
                 this.selektovanaNarudzbenica=Object.assign({}, item);
                 this.selektovana = this.selektovanaNarudzbenica.id;
                 this.obradjena = false;
                 if (this.selektovanaNarudzbenica.status == "OBRADJENA"){
                   this.obradjena = true;
                 }
                 console.log(this.obradjena);
                 this.componentKey += 1
                 this.ponudeDialog = true;
                 
                 
             },

             ObrisiNarudzbenicu(item){
                 console.log(item);
                 this.selektovanaNarudzbenica=Object.assign({}, item);
                 if (this.userId != this.selektovanaNarudzbenica.adminId){
                  this.message="Nije moguće obrisati narudzbenicu kreiranu od strane drugog administratora apoteke.";
                  this.obavestenje = true;
                }
                else if (!this.selektovanaNarudzbenica.bezPonuda){
                  this.message="Nije moguće obrisati narudzbenicu koja ima ponude.";
                  this.obavestenje = true;
                }
                else
                {
                  Vue.axios.put(`/narudzbenice/obrisi/${item.id}`);
                  const index=this.narudzbenice.findIndex((l)=>l.id==this.selektovanaNarudzbenica.id)
                  if(index !=-1){
                      this.narudzbenice.splice(index, 1);
                  }
                }
                 
             },

              IzmeniNarudzbenicu(item){
                console.log(item);
                this.selektovanaNarudzbenica=Object.assign({}, item);
                if (this.userId != this.selektovanaNarudzbenica.adminId){
                this.message="Nije moguće menjati narudžbenicu kreiranu od strane drugog administratora apoteke.";
                this.obavestenje = true;
                }
                else if (!this.selektovanaNarudzbenica.bezPonuda){
                  this.message="Nije moguće menjati narudzbenicu koja ima ponude.";
                  this.obavestenje = true;
                }else{
                  this.izmenaDialog = true;
                }
                
             },
             izmenaRoka(){
               if(this.definisanRok == null){
                this.message="Potrebno je definisati rok.";
                this.obavestenje = true;
               }else{
                this.selektovanaNarudzbenica.rok = this.definisanRok;
                Vue.axios.put(`/narudzbenice/izmeni/${this.selektovanaNarudzbenica.id}`, this.selektovanaNarudzbenica);
                location.reload();
                this.izmenaDialog = false;
               }
                
             },
             endDialog(){
                 this.dialog = false;
             },
             endObavestenje(){
                 this.obavestenje = false;
             },
             endPonudeDialog(){
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
                Vue.axios.post(`/narudzbenice/statusApoteka/${this.apotekaId}`, this.radio, {headers: {"Content-Type": "text/plain"}}).then(response => {
                        
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