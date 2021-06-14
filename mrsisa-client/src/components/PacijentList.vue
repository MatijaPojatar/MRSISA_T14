<template>
<v-row>
  <v-data-table
    :headers="headers"
    :items="pacijenti"
    :sort-by="'ime'"
    :sort-desc="[false, true]"
    multi-sort
    class="elevation-1"
  >
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        class="mr-2"
        @click="showAccount(item)"
      >
        mdi-account
      </v-icon>
    </template>
  </v-data-table>
  <v-dialog
    v-model="dialog"
    persistent
    scrollable
    retain-focus
      max-width="960">
      <v-card>
        <v-card-title class="headline">
          Nalog pacijenta
        </v-card-title>
        <v-card-text>
        <AccountView :user="selectedUser" :farmaceut="farmaceut" :editable="false" :adminView="false" :key="componentKey"/>
        </v-card-text>
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
import AccountView from "./AccountView";
import moment from "moment";

export default {
    components:{
        AccountView,
    },
    data: () => ({
        headers: [
          {
            text: 'Ime',
            align: 'start',
            value: 'ime',
          },
          { text: 'Prezime', value: 'prezime' },
          { text: 'Datum poslednjeg pregleda', value: 'datum' },
          { text: 'Nalog', value: 'actions', sortable: false },
        ],
        pacijenti: [],
        dialog: false,
        selectedUser: {},
        componentKey:0,
    }),
    props: {
        doktorId: Number,
        farmaceut: Boolean,
    },
    mounted(){
        this.loadPacijenti();
    },
    methods :{
        async loadPacijenti(){
            let path="pregled"
            if(this.farmaceut){
                path="savetovanje"
            }
            await Vue.axios.get(`/${path}/all/pacijenti/${this.doktorId}`).then(response =>
            {
                const pacijenti=[];
                response.data.forEach(element => {
                    pacijenti.push({
                        ime: element.ime,
                        prezime: element.prezime,
                        brojTelefona: element.brojTelefona,
                        adresa: element.adresa,
                        grad: element.grad,
                        drzava: element.drzava,
                        pol: element.pol,
                        datum: moment(new Date(element.poslednjiPregled[0].toString()+"-"+element.poslednjiPregled[1].toString()+"-"+element.poslednjiPregled[2].toString()+" "+element.poslednjiPregled[3].toString()+":"+element.poslednjiPregled[4].toString())).format("dddd, MMMM Do YYYY, h:mm a"),
                    }
                    )
                    this.pacijenti=pacijenti;
                });
            }
            )
        },
        showAccount(user){
            console.log(user);
            //let index=this.pacijenti.indexOf(user)
            this.selectedUser=Object.assign({}, user);
            console.log(this.selectedUser);
            this.componentKey+=1;
            this.dialog=true;
        },
        endDialog(){
            this.dialog=false;
        },
    },
}

</script>