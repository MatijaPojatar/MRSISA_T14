<template>
  <v-row justify="center" max-width="100">
    <v-expansion-panels  accordion>
      <v-expansion-panel
        v-for="lek in lekovi"
        :key="lek.id"
      @click = "PanelSelected(lek.id)">
        <v-expansion-panel-header><v-row><div>{{lek.naziv}}</div>
           <v-badge 
            color="error"
            icon="mdi-alert-circle"
            :value="lek.alergija"
            >  
                <v-btn icon x-small v-if = "!adminView" @click="addLek(lek)" depressed class="w-25">
                    <v-icon>mdi-plus</v-icon>
                </v-btn>
            </v-badge>
        </v-row>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
         <div>
             Proizvodjac: {{lek.proizvodjac}}
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
             Rezim izdavanja: {{lek.rezim}}
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
             Dostupna kolicina: {{lek.kolicina}}
         </div>
         <br/>
         <div v-if = "adminView">
            <v-btn
                class="mx-2"
                fab
                dark
                small
                color="pink"
                @click="ObrisiLek"
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
                @click="IzmeniLek"
            >
                <v-icon>
                    mdi-pencil
                </v-icon>
            </v-btn>
            
         </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
      <v-divider></v-divider>
      <div v-if = "!adminView">Preporučeni lekovi:</div>
      <v-expansion-panel
        v-for="preporuceniLek in preporuceniLekovi"
        :key="preporuceniLek.lek.lekId"
      >
        <v-expansion-panel-header>{{preporuceniLek.lek.naziv}}
            <v-btn icon x-small v-if = "!adminView" @click="removePreporuceni(preporuceniLek.lek.id)">
                <v-icon>mdi-minus</v-icon>
            </v-btn>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
         <div>
             Terapija: {{preporuceniLek.terapija}}
         </div>
        </v-expansion-panel-content>
        </v-expansion-panel>
    </v-expansion-panels>

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
      v-model="obavestenjeDialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          {{dialogTitle}}
        </v-card-title>
        <v-card-text><div>{{dialogMessage}}</div>
        <div v-if="stanje">
            <v-form
                ref="form"
                v-model="valid"
                lazy-validation
            >
                <v-text-field
                    v-model="terapija"
                    :counter="10"
                    :rules="terapijaRules"
                    label="Trajanje terapije( format: dani x brojPuta)"
                    required
                ></v-text-field>
            </v-form>
        </div>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue"
            text
            v-if="!stanje"
            @click="zamenskiDobavi"
          >
            Zamenski lek
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="endDialogObavestenje"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog
      v-model="zamenskiDialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Zamenski lek
        </v-card-title>
        <v-card-text>
        <div v-if="zamenskiPostoji">
            <div>Naziv leka: {{zamenski.naziv}}</div>
            <v-form
                ref="form"
                v-model="valid"
                lazy-validation
            >
                <v-text-field
                    v-model="terapija"
                    :counter="10"
                    :rules="terapijaRules"
                    label="Trajanje terapije( format: dani x brojPuta)"
                    required
                ></v-text-field>
            </v-form>
        </div>
        <div v-if="!zamenskiPostoji">
            Ne postoje zamenski lekovi u apoteci.
        </div>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endZamenskiDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
 import axios from "axios";
 import UpdateAddLekApotekeForm from "./UpdateAddLekApotekeForm";

    export default{
        data: () => ({
            lekovi: [],
            alergije: [],
            lekUpdateDialog: false,
            selektovanLek: {},
            selektovan: null,
            obavestenjeDialog: false,
            valid: true,
            dialogMessage: "",
            dialogTitle: "",
            terapija: "",
            zamenskiPostoji: false,
            zamenski: {},
            zamenskiDialog: false,
            stanje: false,
            terapijaRules: [
                v => !!v || 'Obavezno polje',
                v => (v && v.length <= 10 ) || 'Dužina maksimalno 10 karaktera',
                v => (new RegExp("^(([1-9][0-9]*))x(([1-9][0-9]*))$").test(v)) || 'Loš format',
            ],
         }),
        props:{
            apotekaId: Number,
            pacijentId: Number,
            adminView: Boolean,
            preporuceniLekovi: {
                type: Array,
                default: function () {
                    return []
                },
            }
            
        },
        components:{
            UpdateAddLekApotekeForm,
        },
        async mounted(){
            await this.loadLekovi();
            if(this.pacijentId){
                await this.loadAlergije();
                this.renderAlergije();
            }
        },
        methods:{
             async loadLekovi(){
                 const lekovi = []
                if (!this.apotekaId){
                    await axios.get(`http://localhost:8080/lekovi`).then(response => {
                        
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
                            })
                            this.lekovi = lekovi
                        })
                    });
                }
                else {
                    await axios.get(`http://localhost:8080/lekovi/apoteka/${this.apotekaId}`).then(response => {
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
                }
             },

             async loadAlergije(){
                 await axios.get(`http://localhost:8080/pacijent/alergije/${this.pacijentId}`).then(response=>{
                     const alergije=[]
                     response.data.forEach(element => {
                         alergije.push({
                             id: element.id,
                         })
                         this.alergije=alergije
                     });
                 })
             },

             renderAlergije(){
                 this.alergije.forEach(element => {
                     let id=element.id
                     this.lekovi.forEach(lek => {
                         if(lek.lekId===id){
                             lek.alergija+=1;
                         }
                     });
                 });
             },

             PanelSelected(id){
                 this.selektovan = id;
                 console.log(this.selektovan);
                 
             },

             async addLek(lek){
                 if(lek.alergija!=0){
                     this.dialogTitle="Greška"
                     this.dialogMessage="Pacijent je alergičan na izabrani lek."
                     this.stanje=false
                 }else{
                    await axios.get(`http://localhost:8080/apoteke/stanje/${lek.id}`,{params:{apotekaId:this.apotekaId,kolicina:1}}).then(response=>{
                        this.dialogTitle="Obaveštenje"
                        this.selektovan=lek.id
                        this.selektovanLek=lek
                        this.stanje=response.data
                        if(response.data){
                            this.dialogMessage="Leka ima na stanju.."
                        }else{
                            this.dialogMessage="Leka nema na stanju.."
                        }
                    }) 
                 }
                 this.obavestenjeDialog=true

             },

             ObrisiLek(){
                 axios.put(`http://localhost:8080/apoteke/obrisiLek/${this.selektovan}`, this.apotekaId, {headers: {"Content-Type": "text/plain"}})
                 location.reload();
             },

             IzmeniLek(){
                 console.log("izmena" + this.selektovan);
                 axios.put(`http://localhost:8080/apoteke/preuzmiLek/${this.selektovan}`, this.apotekaId, {headers: {"Content-Type": "text/plain"}}).then(response => {
                     console.log(response.data)
                    this.selektovanLek=response.data;
                    console.log(this.selektovanLek);
                 this.lekUpdateDialog = true;
                });
                
             },
             endDialog(){
                this.lekUpdateDialog = false;
             },
             endDialogObavestenje(){
                 if(this.stanje){
                    if(this.$refs.form.validate()){
                        console.log(this.terapija); 
                        this.obavestenjeDialog = false;
                        const index=this.preporuceniLekovi.findIndex((l)=>l.lek.id==this.selektovanLek.id)
                        if(index !=-1){
                            this.preporuceniLekovi[index]={lek: this.selektovanLek,
                            terapija: this.terapija}
                        }else{
                           this.preporuceniLekovi.push({
                            lek: this.selektovanLek,
                            naziv : this.selektovanLek.naziv,
                            terapija: this.terapija
                        }) 
                        }
                        this.terapija=""
                    }
                 }else{
                    this.obavestenjeDialog = false;
                 }
             },
             removePreporuceni(id){
                const index=this.preporuceniLekovi.findIndex((l)=>l.lek.id==id)
                 this.preporuceniLekovi.splice(index,1)
             },
             async zamenskiDobavi(){
                 await axios.get(`http://localhost:8080/lekovi/zamenski/${this.selektovan}/${this.apotekaId}/${this.pacijentId}`).then(response=>{
                     this.obavestenjeDialog=false;
                     this.zamenski=response.data
                     if(this.zamenski.id===-1){
                         this.zamenskiPostoji=false
                     }else{
                         this.zamenskiPostoji=true
                         const index=this.lekovi.findIndex((l)=>l.lekId==this.zamenski.id)
                         console.log(index);
                         this.selektovanLek=this.lekovi[index]
                     }
                 })
                 this.zamenskiDialog=true;
             },

             endZamenskiDialog(){
                 if(this.zamenskiPostoji){
                    if(this.$refs.form.validate()){
                        this.zamenskiDialog=false;
                        const index=this.preporuceniLekovi.findIndex((l)=>l.lek.id==this.selektovanLek.id)
                        if(index !=-1){
                            this.preporuceniLekovi[index]={lek: this.selektovanLek,
                            terapija: this.terapija}
                        }else{
                           this.preporuceniLekovi.push({
                            lek: this.selektovanLek,
                            naziv : this.selektovanLek.naziv,
                            terapija: this.terapija
                        }) 
                        }
                        this.terapija=""
                    }
                 }else{
                    this.zamenskiDialog=false;
                 }
             }

         }
    }

</script>