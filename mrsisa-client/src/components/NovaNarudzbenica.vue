<template>
  <v-container justify="center" max-width="100">

<div justify="space-around" align="center" >
    <v-card max-width="70vh">
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
    <v-data-table
    :headers="headers"
    :items="naruceniLekovi"
    :sort-by="'id'"
    :sort-desc="[false, true]"
    
    multi-sort
    class="elevation-1"
  >
    
  </v-data-table>
  <br/>
    <v-btn
                class="mx-2"
                @click="kreirajNarudzbenicu"
            >
               Kreiraj narudžbenicu
            </v-btn>
    
  </v-card>
  </div>
  <v-divider/>
    <br/>
    <br/>
<div>
    <v-row align="center" justify="center">
        <v-btn
                class="mx-2"
                @click="viewLekoviUpit"
            >
               Iz upita
            </v-btn>

        <v-btn
                class="mx-2"
                @click="viewLekoviApoteka"
            >
               Iz apoteke
            </v-btn>

        <v-btn
                class="mx-2"
                @click="viewLekoviOstali"
            >
               Ostali
            </v-btn>
    </v-row>
    </div>
    <br/>
    <br/>
    <v-expansion-panels accordion>
      <v-expansion-panel
        v-for="lek in lekovi"
        :key="lek.id"
      @click = "PanelSelected(lek)">
        <v-expansion-panel-header><v-row><div>{{lek.naziv}}</div>
        </v-row>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
         <div>
             Proizvođač: {{lek.proizvodjac}}
         </div>  
         <v-divider></v-divider> 
         <div>
             Sastav: {{lek.sastav}}
         </div>
         <v-divider></v-divider>
         <div>
             Napomena: {{lek.napomena}}
         </div>
         <v-divider></v-divider>
         <div>
             Režim izdavanja: {{lek.rezim}}
         </div>
         <v-divider></v-divider>
         <div>
             Oblik leka: {{lek.oblik}}
         </div>
         <v-divider></v-divider>
         <div>
             Vrsta leka: {{lek.vrsta}}
         </div>
         <v-divider></v-divider>
         <div>
             Cena leka: {{lek.cena}}
         </div>
         <v-divider></v-divider>
         <div>
             Dostupna količina: {{lek.kolicina}}
         </div>
         <br/>
         <div>
            <v-btn
                class="mx-2"
                fab
                dark
                small
                color="light-green"
                @click="naruciLek"
            >
                <v-icon>
                    mdi-plus
                </v-icon>
            </v-btn>

           
         </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
      <v-divider></v-divider>
      </v-expansion-panels>

      <v-dialog
      v-model="dialog"
      persistent
      max-width="400"
    >
      <v-card>
        <v-card-title class="headline">
          Definisanje narudžbine
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

            <v-text-field
            v-model="definisanaCena"
            v-if="showLekoviOstali"
            :counter="10"
            :rules="brojRules"
            label="Cena"
            required
            ></v-text-field>

            <v-btn
            class="mr-4"
            @click="definisiKolicinaCena"
            >
            Ok
            </v-btn>
        </v-form>
        
      </v-card>
  </v-dialog>

  <v-dialog
      v-model="obavestenjeRok"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>Potrebno je uneti rok za ponude.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endObavestenjeRok"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>
      
  </v-container >
</template>

<script>
 import Vue from "vue";

    export default{
        data: () => ({
            headers: [
          {
            text: 'Id',
            align: 'start',
            value: 'lekId',
          },
          { text: 'Naziv leka', value: 'naziv' },
          { text: 'Količina', value: 'kolicina' },
          
        ],
            lekovi: [],
            selektovanLek: {},
            selektovan: null,
            naruceniLekovi: [],
            lekoviUpit:[],
            lekoviApoteka: [],
            lekoviOstali:[],
            showLekoviUpit:[],
            showLekoviApoteka: [],
            showLekoviOstali:[],
            definisanaKolicina: "",
            definisanaCena: "",
            definisanRok: null,
            menu: false,
            obavestenjeRok:false,
            

            valid: true,
            validRok: false,
            brojRules: [
                v => !!v || 'Obavezno polje',
                v => (v && v.length <= 10 ) || 'Dužina maksimalno 10 karaktera',
                v => (new RegExp("^(0|([1-9][0-9]*))(\\.[0-9]+)?$").test(v)) || 'Loš format',
            ],
            requiredRules: [
                v => !!v || 'Obavezno polje',
            ],
            
            dialog: false,
            
         }),
        props:{
            apotekaId: Number,
            preporuceniLekovi: {
                type: Array,
                default: function () {
                    return []
                },
            },
            user:{},
            
        },
        components:{
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
                                lekMagacinId: element.id,
                                proizvodjac : element.proizvodjac,
                                sastav: element.sastav,
                                napomena: element.napomena,
                                rezim: element.rezimIzdavanja,
                                oblik: element.oblikLeka,
                                vrsta: element.vrstaLeka, 
                                cena: element.cena,
                                kolicina: element.kolicina,
                                id: element.lekId,
                            })
                            this.lekoviApoteka = lekovi
                        })
                    });

                    const lekovi2 = []
                    await Vue.axios.get(`http://localhost:8080/lekovi/vanApoteke/${this.apotekaId}`).then(response => {
                        response.data.forEach(element => {
                            lekovi2.push({
                                naziv: element.naziv,
                                id: element.id,
                                proizvodjac : element.proizvodjac,
                                sastav: element.sastav,
                                napomena: element.napomena,
                                rezim: element.rezimIzdavanja,
                                oblik: element.oblikLeka,
                                vrsta: element.vrstaLeka, 
                                cena: 0,
                                kolicina: 0,
                                lekMagacinId: element.id,
                                alergija: 0,
                            })
                            this.lekoviOstali = lekovi2
                        })
                    });

                    const lekovi3 = []
                    await Vue.axios.get(`http://localhost:8080/apoteke/upitiLekovi/${this.apotekaId}`).then(response => {
                        response.data.forEach(element => {
                            lekovi3.push({
                                naziv: element.naziv,
                                id: element.id,
                                proizvodjac : element.proizvodjac,
                                sastav: element.sastav,
                                napomena: element.napomena,
                                rezim: element.rezimIzdavanja,
                                oblik: element.oblikLeka,
                                vrsta: element.vrstaLeka, 
                                cena: 0,
                                kolicina: 0,
                                lekMagacinId: element.id,
                                alergija: 0,
                            })
                            this.lekoviUpit = lekovi3
                        })
                    });
                },
                PanelSelected(lek){
                 this.selektovanLek = lek;
                 this.selektovan= lek.id;
                 console.log(this.selektovan);
                 
                },
                viewLekoviApoteka(){
                    this.lekovi = this.lekoviApoteka;
                    this.showLekoviApoteka=true;
                    this.showLekoviOstali=false;
                    this.showLekoviUpit=false;
                },
                viewLekoviOstali(){
                    this.lekovi = this.lekoviOstali;
                    this.showLekoviApoteka=false;
                    this.showLekoviOstali=true;
                    this.showLekoviUpit=false;
                },
                viewLekoviUpit(){
                    this.lekovi = this.lekoviUpit;
                    this.showLekoviApoteka=false;
                    this.showLekoviOstali=false;
                    this.showLekoviUpit=true;
                },
                naruciLek(){
                    console.log(this.selektovanLek.naziv)
                    this.dialog = true
                },
                definisiKolicinaCena(){
                    if(this.$refs.form.validate()){
                        this.naruceniLekovi.push(
                            {
                                lekId: this.selektovan,
                                naziv: this.selektovanLek.naziv,
                                kolicina: this.definisanaKolicina,
                            }
                        )
                        if (this.showLekoviOstali){
                            Vue.axios.put(`http://localhost:8080/apoteke/dodajLek/${this.selektovan}/${this.apotekaId}`,this.definisanaCena, {headers: {"Content-Type": "text/plain"}});
                            const index=this.lekoviOstali.findIndex((l)=>l.id==this.selektovanLek.id)
                            if(index !=-1){
                                this.lekoviOstali.splice(index, 1);
                                }
                        }else if(this.showLekoviApoteka){
                            const index=this.lekoviApoteka.findIndex((l)=>l.id==this.selektovanLek.id)
                            if(index !=-1){
                                this.lekoviApoteka.splice(index, 1);
                                }
                        }
                        else if(this.showLekoviUpit){
                            const index=this.lekoviUpit.findIndex((l)=>l.id==this.selektovanLek.id)
                            if(index !=-1){
                                this.lekoviUpit.splice(index, 1);
                                }
                        }
                        this.dialog = false;
                        this.definisanaCena="";
                        this.definisanaKolicina="";
                    }
                },
                kreirajNarudzbenicu(){
                    if(!this.definisanRok){
                        this.obavestenjeRok = true;
                    }else{
                        Vue.axios.post(`http://localhost:8080/narudzbenice/dodaj/${this.apotekaId}`, {lekovi:this.naruceniLekovi, adminId:this.user.id, rok:this.definisanRok});
                    }
                },
                endObavestenjeRok(){
                    this.obavestenjeRok=false;
                }
                

             

         }
    }

</script>