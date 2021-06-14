<template>
<v-row>
<v-col cols="12">
<v-card>
    <v-card-title>
        Preuzimanje leka
    </v-card-title>
        <v-form
        ref="form"
        v-model="valid"
        lazy-validation
        >
            <v-text-field
            v-model="code"
            :rules="codeRules"
            :counter="5"
            label="Uneti šifru rezervacije"
            required
            @click:append="show1 = !show1"
            ></v-text-field>

            <v-btn
            :disabled="!valid"
            class="mr-4"
            @click="pronadjiRezervaciju"
            >
            Proveri šifru
            </v-btn>
        </v-form>
</v-card>
</v-col>
<v-col cols="12">
<v-expand-transition>
<div v-if="sent">
    <v-card v-if="!found" color="red">
        <v-card-title>
        Greška
        </v-card-title>
        {{message}}
    </v-card>
    <v-card v-if="found">
        <v-card-title>
        Rezervacija
        </v-card-title>
        <v-card-text>
            <v-expansion-panels>
            <v-expansion-panel :key="lek.id">
                <v-expansion-panel-header><div>{{lek.naziv}}</div>
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
                </v-expansion-panel-content>
            </v-expansion-panel>
            </v-expansion-panels>
            Kolicina: {{rezervacija.kolicina}}<v-divider/>
            Datum: {{rezervacija.datum | moment("dddd, MMMM Do YYYY")}}<v-divider/>
        </v-card-text>
        <v-card-actions>
            <v-btn
                color="orange"
                text
                @click="preuzmiLek"
            >
                Preuzmi lek
            </v-btn>
        </v-card-actions>
    </v-card>  
</div>
</v-expand-transition>
</v-col>
<v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>{{dialogMessage}}</v-card-text>
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
</v-row>
</template>

<script>
import Vue from "vue";

export default{

    data: () => ({
      valid: true,
      code: "",
      saveCode:"",
      message: "",
      found: false,
      sent: false,
      rezervacija:{},
      lek:{},
      apotekaId: -1,
      dialog: false,
      dialogMessage: "",
      codeRules: [
        v => !!v || 'Obavezno polje',
        v => (v.length == 5) || 'Dužina 5 karaktera',
      ],
    }),
    props :{
        id: Number,
    },
    mounted(){
        this.dobaviApotekaId()
    },
    methods: {
        async dobaviApotekaId(){
            await Vue.axios.get(`/farmaceut/${this.id}`).then(response=>{
                console.log(response.data);
                this.apotekaId=response.data.apotekaId
            })
        },
        async dobaviRezervaciju(f){
            await Vue.axios.get(`/rezervacija/findOne/${this.code}`).then(response=>{
                if(response.data){
                   this.rezervacija={
                    lekId:response.data.lekId,
                    kolicina: response.data.kolicina,
                    datum: new Date(response.data.datum[0].toString()+"-"+response.data.datum[1].toString()+"-"+response.data.datum[2].toString()),
                    } 
                }
                else{
                    f=false;
                }

            })
            if(f){
                await Vue.axios.get(`/lekovi/findOneInApoteka/${this.rezervacija.lekId}`,{params:{apotekaId:this.apotekaId}}).then(response=>{
                    this.lek={
                        naziv: response.data.naziv,
                        id: response.data.id,
                        proizvodjac : response.data.proizvodjac,
                        sastav: response.data.sastav,
                        napomena: response.data.napomena,
                        rezim: response.data.rezimIzdavanja,
                        oblik: response.data.oblikLeka,
                        vrsta: response.data.vrstaLeka, 
                        cena: response.data.cena, 
                    }
                })
            }

            return f;
        },
        async pronadjiRezervaciju(){
            let f=false;
            let g=false;
            await Vue.axios.get(`/rezervacija/checkOne/${this.code}`,{params:{apotekaId:this.apotekaId}}).then(response=>{
                this.message=response.data
                if(response.data==="Uspeh"){
                    f=true
                }
                else if(response.data==="Greska"){
                    g=true;
                }
            })
            if(g){
                this.dialog=true;
                this.message="Došlo je do greške!"
            }else{
              if(f){
                f=await this.dobaviRezervaciju(f);
              }
              this.found=f
              this.saveCode=this.code
              this.sent=true  
            }

        },
        async preuzmiLek(){
            let uspeh=false;
            await Vue.axios.put(`/rezervacija/preuzmi/${this.code}`).then(response=>{
                if(response.data==="Uspeh"){
                    uspeh=true;
                }
            }).catch(err=>{
                console.log (err)
                uspeh=false;
            })
            if(uspeh){
                this.dialogMessage="Lek je uspešno preuzet.";
            }else{
                this.dialogMessage="Došlo je do greške pri preuzimanju."
            }
            this.dialog=true
        },
        endDialog(){
            this.dialog=false;
            this.sent=false;
        },
    },
}

</script>
