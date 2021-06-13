<template>
  <v-row justify="center">
   
   <v-data-table
    :headers="headersAdmin"
    :items="lekovi"
    :sort-by="'naziv'"
    :sort-desc="[false, true]"
    multi-sort
    class="elevation-1"
    v-if="adminView"
  >
    <template v-slot:item.actions="{ item }">
        <div>
      <v-btn
                class="mx-2"
                fab
                dark
                small
                color="pink"
                @click="ObrisiLek(item)"
                v-if="adminView"
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
                v-if="adminView"
                @click="IzmeniLek(item)"
            >
                <v-icon>
                    mdi-pencil
                </v-icon>
            </v-btn>
            </div>
    </template>
  </v-data-table>

   <v-data-table
    :headers="headers"
    :items="lekovi"
    :sort-by="'naziv'"
    :sort-desc="[false, true]"
    multi-sort
    class="elevation-1"
    v-if="!adminView"
  >
    <template v-slot:item.actions="{ item }">
        <div>
      <v-btn
                class="mx-2"
                color="light-green"
                @click="definisiKolicinu(item)"
                v-if="registrovanView"
            >
                Rezerviši
            </v-btn>

            
            </div>
    </template>
  </v-data-table>

    <v-dialog
    v-model="lekUpdateDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Izmeni lek
        </v-card-title>
        <UpdateAddLekApotekeForm :apotekaId="this.apotekaId" :lekUMagacinu="this.selektovanLek"/>
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
    
    <v-dialog
      v-model="dialogKolicina"
      persistent
      max-width="400"
    >
      <v-card>
        <v-card-title class="headline">
          Definisanje količine i datuma
        </v-card-title>
        <v-form
            ref="form"
            v-model="valid"
            lazy-validation
        >
            <v-text-field
            v-model="definisanaKolicina"
            :counter="10"
            :rules="brojRules"
            label="Količina"
            required
            ></v-text-field>

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
            label="Rok za preuzimanje:"
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
            @click="rezervisi"
            >
            Ok
            </v-btn>
        </v-form>
        
      </v-card>
  </v-dialog>

  <v-dialog
      v-model="obavestenjeDialog"
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
            @click="endObavestenjeDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>
  </v-row>
</template>

<script>
 import Vue from "vue";
 import UpdateAddLekApotekeForm from "./UpdateAddLekApotekeForm";

    export default{
        data: () => ({
            headersAdmin: [
          {
            text: 'Naziv',
            align: 'start',
            value: 'naziv',
          },
          { text: 'Id', value: 'id' },
          { text: 'Proizvođač', value: 'proizvodjac' },
          { text: 'Sastav', value: 'sastav'},
          { text: 'Napomena', value: 'napomena' },
          { text: 'Režim', value: 'rezim' },
          { text: 'Oblik', value: 'oblik'},
          { text: 'Vrsta', value: 'vrsta'},
          { text: 'Cena', value: 'cena'},
          { text: 'Količina', value: 'kolicina'},
          { text: 'Upravljaj', value: 'actions', sortable: false },
        ],
        headers: [
          {
            text: 'Naziv',
            align: 'start',
            value: 'naziv',
          },
          { text: 'Id', value: 'id' },
          { text: 'Proizvođač', value: 'proizvodjac' },
          { text: 'Sastav', value: 'sastav'},
          { text: 'Napomena', value: 'napomena' },
          { text: 'Režim', value: 'rezim' },
          { text: 'Oblik', value: 'oblik'},
          { text: 'Vrsta', value: 'vrsta'},
          { text: 'Cena', value: 'cena'},
          { text: 'Rezerviši', value: 'actions', sortable: false },
        ],

            lekovi: [],
            obavestenjeDialog:false,
            lekUpdateDialog: false,
            dialogKolicina:false,
            selektovanLek: {},
            selektovan: null,
            definisanaKolicina:"",
            message:"",
            definisanRok:null,
            menu:false,

            valid: true,
            brojRules: [
                v => !!v || 'Obavezno polje',
                v => (v && v.length <= 10 ) || 'Dužina maksimalno 10 karaktera',
                v => (new RegExp("^(0|([1-9][0-9]*))(\\.[0-9]+)?$").test(v)) || 'Loš format',
            ],
            requiredRules: [
                v => !!v || 'Obavezno polje',
            ],
         }),
        props:{
            apotekaId: Number,
            adminView: Boolean,
            registrovanView:Boolean,
            userId: Number, 
            
        },
        components:{
            UpdateAddLekApotekeForm,
        },
        async mounted(){
            await this.loadLekovi();
        },
        methods:{
             async loadLekovi(){
                 const lekovi = []
                await Vue.axios.get(`http://localhost:8080/lekovi/apoteka/${this.apotekaId}`).then(response => {
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
                                cena: element.cena,
                                kolicina: element.kolicina,
                                lekId: element.lekId,
                                alergija: 0,
                            })
                            this.lekovi = lekovi
                        })
                    });
             },

             ObrisiLek(lek){
                 this.selektovanLek=Object.assign({}, lek);
                 this.selektovan = this.selektovanLek.id;
                 Vue.axios.put(`http://localhost:8080/apoteke/obrisiLek/${this.selektovan}`, this.apotekaId, {headers: {"Content-Type": "text/plain"}})
                 location.reload();
             },

             IzmeniLek(lek){
                 console.log("izmena" + this.selektovan);
                 this.selektovanLek=Object.assign({}, lek);
                 this.selektovan = this.selektovanLek.id;
                 Vue.axios.put(`http://localhost:8080/apoteke/preuzmiLek/${this.selektovan}`, this.apotekaId, {headers: {"Content-Type": "text/plain"}}).then(response => {
                     console.log(response.data)
                    this.selektovanLek=response.data;
                    console.log(this.selektovanLek);
                 this.lekUpdateDialog = true;
                });
                
             },
             definisiKolicinu(lek){
                this.selektovanLek=Object.assign({}, lek);
                this.selektovan = this.selektovanLek.id;
                this.dialogKolicina = true;
             },

             rezervisi(){
               if(!this.definisanRok){
                        this.message= "Rok preuzimanja definisan."
                        this.obavestenje = true;
                }
                else{
                  Vue.axios.post(`http://localhost:8080/rezervacija/novaRezervacija`, {apotekaId:this.apotekaId, lekId:this.selektovanLek.lekId, kolicina:this.definisanaKolicina, pacijentId:this.userId, datum:this.definisanRok}).then(response => {
                        
                        this.message=response.data;
                    this.obavestenjeDialog = true;
                    });
                    
                  this.dialogKolicina = false;
                }
             },
             endDialog(){
                this.lekUpdateDialog = false;
             },
             endObavestenjeDialog(){
                this.obavestenjeDialog = false;
            },
             
            

             

         }
    }

</script>