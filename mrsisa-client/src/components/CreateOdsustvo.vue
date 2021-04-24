<template>
<v-card>
  <v-row>
    <v-col
      cols="12"
      sm="6"
    >
      <v-date-picker
        v-model="dates"
        range
        :min="trenutniDatum"
      ></v-date-picker>
    </v-col>
    <v-col
      cols="12"
      sm="6"
    >
      <v-text-field
        v-model="dateRangeText"
        label="Date range"
        prepend-icon="mdi-calendar"
        readonly
      ></v-text-field>
      <v-btn
        color="green darken-1"
        text
        outlined
        @click="posaljiZahtev"
      >
        Pošalji zahtev
      </v-btn>
    </v-col>
  </v-row>
  <v-dialog
        v-model="dialog"
        persistent
        max-width="290"
        >
        <v-card>
            <v-card-title class="headline">
            {{dialogTitle}}
            </v-card-title>
            <v-card-text>{{dialogMessage}}.</v-card-text>
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
</v-card>
</template>

<script>

import axios from "axios";

export default {
    data: () => ({
      dates: [],
      trenutniDatum: null,
      dialog: false,
      dialogTitle: "",
      dialogMessage: "",
      odgovor: true,
    }),
    props: {
        doktorId: Number,
        farmaceut: Boolean,
    },
    mounted(){
        let tren=new Date()
        this.trenutniDatum=new Date(tren)
        this.trenutniDatum.setDate(this.trenutniDatum.getDate()+1)
        this.trenutniDatum=this.trenutniDatum.toISOString().slice(0,10);
    },
    computed: {
      dateRangeText () {
        return this.dates.join(' ~ ')
      },
    },
    methods:{
        async posaljiZahtev(){
            if(this.dates.length!=2){
                this.dialogTitle="Greška";
                this.dialogMessage="Niste izabrali dva datuma";
                this.dialog=true;
            }
            let pocetak,kraj;
            if(this.dates[0]<this.dates[1]){
                pocetak=new Date(this.dates[0])
                kraj = new Date(this.dates[1])
            }else{
                pocetak=new Date(this.dates[1])
                kraj = new Date(this.dates[0])
            }
            let path="dermatolog"
            let odsustvo={}
            if(this.farmaceut){
                path="farmaceut"
                odsustvo={
                    pocetak:pocetak,
                    kraj: kraj,
                    farmaceutId: this.doktorId,
                    odobren: true,
                    adminId: null,
                }
            }
            await axios.put(`http://localhost:8080/odsustvo/${path}/dodaj/${this.doktorId}`,odsustvo).then(response => {
                    this.odgovor=response.data;
                });
            
            if(!this.odgovor){
                this.dialogTitle="Greška";
                this.dialogMessage="Postoje termini u unetom periodu.";
            }else{
                this.dialogTitle="Uspeh";
                this.dialogMessage="Zahtev poslat.";
            }
            this.dialog=true;
        },
        endDialog(){
            this.dialog=false;
        }
    },
  }

</script>