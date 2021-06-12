<template>
<v-row>
  <v-card>
    <v-card-title>
      Pacijenti
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="pacijenti"
      :search="search"
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
  </v-card>
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
        <AccountView :user="selectedUser" :farmaceut="false" :editable="false" :adminView="false" :key="componentKey"/>
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

  export default {
      components:{
        AccountView,
        },
      data: () => ({
        headers: [
          {
            text: 'Ime',
            align: 'start',
            sortable: false,
            value: 'ime',
          },
          { text: 'Prezime', value: 'prezime' },
          { text: 'Nalog', value: 'actions', sortable: false },
        ],
        pacijenti: [],
        dialog: false,
        selectedUser: {},
        search:'',
        componentKey: 0,
    }),
    mounted(){
        this.loadPacijenti();
    },
    methods :{
        async loadPacijenti(){
            await Vue.axios.get(`http://localhost:8080/pacijent/all`).then(response =>
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
                    }
                    )
                    this.pacijenti=pacijenti;
                });
            }
            )
        },
        showAccount(user){
            //let index=this.pacijenti.indexOf(user)
            this.selectedUser=Object.assign({}, this.selectedUser,{
                ime:user.ime,
                prezime:user.prezime,
                brojTelefona: user.brojTelefona,
                adresa: user.adresa,
                grad: user.grad,
                drzava: user.drzava,
                pol: user.pol,
                });
            this.componentKey += 1;
            this.dialog=true;
        },
        endDialog(){
            this.dialog=false;
        },
    },
  }
</script>