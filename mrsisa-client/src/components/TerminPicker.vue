<template>
    <v-stepper  non-linear>
        <v-stepper-header>
            <v-stepper-step
                editable
                step="1"
            >
                Informacije o terminu
            </v-stepper-step>

            <v-divider></v-divider>
            <v-stepper-step
                editable
                step="2"
            >
                Izbor metode
            </v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step
                editable
                step="3"
            >
                Odaberi termin
            </v-stepper-step>
        </v-stepper-header>
        <v-stepper-items>
            <v-stepper-content step="1">
                <v-card
                class="mb-12"
                >
                Izveštaj: {{izvestaj}}
                </v-card>
            </v-stepper-content>

            <v-stepper-content step="2">
                <v-card
                class="mb-12"
                >
                    <v-container fluid>
                        <v-radio-group
                        v-model="radios"
                        mandatory
                        >
                        <v-radio
                            label="Izbor već postojećih termina"
                            value="i-1"
                        ></v-radio>
                        <v-radio
                            label="Kreiraj novi termin"
                            value="i-2"
                        ></v-radio>
                        </v-radio-group>
                    </v-container>
                </v-card>
            </v-stepper-content>

            <v-stepper-content step="3">
                <v-card
                class="mb-12"
                v-if="radios=='i-1'"
                >
                    <v-list dense scrollable>
                        <v-subheader>Termini</v-subheader>
                        <v-list-item-group
                            v-model="terminSelected"
                            color="primary"
                        >
                            <v-list-item
                            v-for="termin in termini"
                            :key="termin.id"
                            @dblclick="zauzmiTermin(termin.id)"
                            >
                            <v-list-item-content>
                                <v-list-item-title >{{termin.name}} {{termin.start.toLocaleString()}} - {{termin.end.toLocaleString()}}</v-list-item-title>
                            </v-list-item-content>
                            </v-list-item>
                        </v-list-item-group>
                    </v-list>
                </v-card>
                <v-card
                class="mb-12"
                v-if="radios=='i-2'"
                scrollable
                >
                    <v-row justify="center">
                        <v-date-picker 
                        v-model="picker"
                        :min="trenutniDatum+1"
                        ></v-date-picker>
                    </v-row>
                    <v-row
                    justify="space-around"
                    align="center"
                    >
                    <v-col style="width: 350px; flex: 0 1 auto;">
                        <h2>Početak:</h2>
                        <v-time-picker
                        v-model="start"
                        :max="end"
                        :min="pocetakRadnogVremena"
                        scrollable
                        ></v-time-picker>
                    </v-col>
                    <v-col style="width: 350px; flex: 0 1 auto;">
                        <h2>Kraj:</h2>
                        <v-time-picker
                        v-model="end"
                        :min="start"
                        :max="krajRadnogVremena"
                        scrollable
                        ></v-time-picker>
                    </v-col>
                    </v-row>
                    <v-spacer/>
                    <v-row 
                    justify="space-around"
                    align="center">
                    <v-btn
                        color="green darken-1"
                        text
                        @click="kreirajTermin"
                    >
                        Kreiraj termin
                    </v-btn>
                    </v-row>
                </v-card>
            </v-stepper-content>
        </v-stepper-items>
        <v-dialog
        v-model="dialog"
        persistent
        max-width="290"
        >
        <v-card>
            <v-card-title class="headline">
            Greška
            </v-card-title>
            <v-card-text>Termin može trajati između 30min i 2h.</v-card-text>
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
        v-model="dialogAdd"
        persistent
        max-width="290"
        >
        <v-card>
            <v-card-title class="headline">
            Greška
            </v-card-title>
            <v-card-text>Termin je već zauzet.</v-card-text>
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
    </v-stepper>
</template>

<script>

import axios from "axios";

export default{
    data: ()=>({
        e1: 1,
        radios: null,
        terminSelected: {},
        termini: [],
        picker: null,
        start: null,
        end: null,
        pocetakRadnogVremena: null,
        krajRadnogVremena: null,
        trenutniDatum: null,
        dialog: false,
        dialogAdd: false,
        check: false,
    }),
    props:{
        izvestaj: String,
        doktorId: Number,
        pacijentId: Number,
        apotekaId: Number,
        farmaceut: Boolean,
    },
    mounted(){
        this.loadTermini();
        this.loadRadnoVreme();
        let tren=new Date()
        this.trenutniDatum=new Date(tren)
        this.trenutniDatum.setDate(this.trenutniDatum.getDate()+1)
        this.trenutniDatum=this.trenutniDatum.toISOString().slice(0,10);
        this.picker=this.trenutniDatum
    },
    methods:{
        loadTermini(){
            let path="pregled"
            if(this.farmaceut){
                path="savetovanje"
            }
            axios.get(`http://localhost:8080/${path}/all/active/${this.doktorId}`,{params:{apotekaId:this.apotekaId}}).then(response => {
           const termini = []
            response.data.forEach(element => {
            termini.push({
              name:element.name,
              pacijent:element.pacijentId,
              apoteka: element.apotekaId,
              start: new Date(element.start[0].toString()+"-"+element.start[1].toString()+"-"+element.start[2].toString()+" "+element.start[3].toString()+":"+element.start[4].toString()),
              end: new Date(element.end[0].toString()+"-"+element.end[1].toString()+"-"+element.end[2].toString()+" "+element.end[3].toString()+":"+element.end[4].toString()),
              izvrsen: element.izvrsen,
              izvestaj: element.izvestaj,
              id: element.id,
            })
            this.termini = termini
          });
        });
        },
        zauzmiTermin(id){
            let path="pregled"
            if(this.farmaceut){
                path="savetovanje"
            }
            console.log(id);
            axios.put(`http://localhost:8080/${path}/zauzmi/${id}/${this.pacijentId}`)
            this.$emit('termin-end')
        },
        loadRadnoVreme(){
            let path="dermatolog"
            if(this.farmaceut){
                path="farmaceut"
            }
            axios.get(`http://localhost:8080/${path}/radnoVreme/${this.doktorId}`).then(response => {
            console.log(response.data);
            this.pocetakRadnogVremena=response.data.pocetak[0].toString()+":"+response.data.pocetak[1].toString()
            this.krajRadnogVremena=response.data.kraj[0].toString()+":"+response.data.kraj[1].toString()
            this.end=response.data.kraj[0].toString()+":"+response.data.kraj[1].toString()
            this.start=response.data.pocetak[0].toString()+":"+response.data.pocetak[1].toString()
        });
        },
        async kreirajTermin(){
            let pocetak=new Date(this.picker+" "+this.start)
            let kraj = new Date(this.picker+" "+this.end)
            console.log(pocetak);
            if(kraj-pocetak>7200000 || kraj-pocetak<1800000){
                this.dialog=true
            }else{
                 let path="pregled"
                if(this.farmaceut){
                    path="savetovanje"
                }
                let newTermin={
                    name: path,
                    izvestaj: '',
                    start: pocetak,
                    end: kraj,
                    pacijentId: this.pacijentId,
                    apotekaId: this.apotekaId,
                    izvrsen: false,
                }
                await axios.put(`http://localhost:8080/${path}/dodaj/${this.doktorId}`,newTermin).then(response => {
                    this.check=response.data;
                });
                if(this.check){
                    this.$emit('termin-end')
                }else{
                    this.dialogAdd=true;
                }
            }
        },
        endDialog(){
        this.dialog=false;
        this.dialogAdd=false;
        },
    },

}

</script>