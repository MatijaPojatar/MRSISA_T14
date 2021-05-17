<template>
    <v-app >
    <v-navigation-drawer
      clipped
      fixed
      app
      expand-on-hover
      src="https://images.unsplash.com/photo-1599652749620-e45b7f38988e?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2089&q=80"
    >
      <v-list >
          <v-list-item class="px-2">
            <v-list-item-avatar>
              <v-img src="https://randomuser.me/api/portraits/men/85.jpg" v-if="user.pol==='MUSKI'"></v-img>
              <v-img src="https://randomuser.me/api/portraits/women/85.jpg" v-if="user.pol==='ZENSKI'"></v-img>
            </v-list-item-avatar>
          </v-list-item>

          <v-list-item link>
            <v-list-item-content>
              <v-list-item-title class="title">
                <div class="wh">
                    {{user.ime}} {{user.prezime}}
                </div>
              </v-list-item-title>
              <v-list-item-subtitle>{{user.mail}}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list
          nav
          dense
        >
          <v-list-item link >
            <v-list-item-icon>
              <v-icon>mdi-view-dashboard</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Dashboard
                </div></v-list-item-title>
          </v-list-item>

          <v-list-item link  @click="accountView">
            <v-list-item-icon>
              <v-icon>mdi-account-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Nalog
                </div></v-list-item-title>
          </v-list-item>

          <v-list-item link  @click="changePassword">
            <v-list-item-icon>
              <v-icon>mdi-lock-reset</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Promeni šifru
                </div></v-list-item-title>
          </v-list-item>
          
          <v-list-item link @click="apotekaView">
            <v-list-item-icon>
              <v-icon>mdi-gamepad-round-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Apoteka
                </div></v-list-item-title>
          </v-list-item>
          
          <v-list-item link @click="lekoviView">
            <v-list-item-icon>
              <v-icon>mdi-pill</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Lekovi
                </div></v-list-item-title>
          </v-list-item>

          <v-list-item link @click="farmaceutiView">
            <v-list-item-icon>
              <v-icon>mdi-test-tube</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Farmaceuti
                </div></v-list-item-title>
          </v-list-item>

          <v-list-item link @click="dermatoloziView">
            <v-list-item-icon>
              <v-icon>mdi-stethoscope</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Dermatolozi
                </div></v-list-item-title>
          </v-list-item>

           <v-list-item link @click="narucivanjeView">
            <v-list-item-icon>
              <v-icon>mdi-clipboard-edit-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Naručivanje
                </div></v-list-item-title>
          </v-list-item>
        </v-list>

        <template v-slot:append>
        <div class="pa-2">
            
          <v-btn block @click="onLogout">
            Logout
          </v-btn>
        </div>
      </template>
    </v-navigation-drawer>

    <v-main >
     <v-container fluid v-if="showAccount" :style="{width:'70vh'}">
        <AccountView :user="user" :editable = "true" :adminApoteke="true"/>
      </v-container>
      <v-container fluid v-if="showPassword" :style="{width:'70vh'}">
        <PasswordSwitch :pass="user.password"  :id="user.id"/>
      </v-container>
      <v-container fluid v-if="showLekovi" :style="{width:'180vh'}" >
        <LekoviAdminApoteke :apotekaId="user.apotekaId" :user="user"/>
      </v-container>
      <v-container fluid v-if="showFarmaceuti" :style="{width:'180vh'}">
        <FarmaceutiAdminApoteke :apotekaId="user.apotekaId" :user="user"/>
      </v-container>
       <v-container fluid v-if="showDermatolozi" :style="{width:'180vh'}">
        <DermatoloziAdminApoteke :apotekaId="user.apotekaId" :user="user"/>
      </v-container>
      <v-container fluid v-if="showNarucivanje" :style="{width:'180vh'}">
        <NarucivanjeAdminApoteke :apotekaId="user.apotekaId"/>
      </v-container>
      <v-container fluid v-if="showApoteka" :style="{width:'70vh'}">
        <ApotekaForm :apotekaId="user.apotekaId" :editable="true" />
      </v-container>
    </v-main>
      </v-app>
</template>

<script>

import ApotekaForm from "./ApotekaForm";
import AccountView from "./AccountView";
import PasswordSwitch from "./PasswordSwitch";
import LekoviAdminApoteke from "./LekoviAdminApoteke";
import FarmaceutiAdminApoteke from "./FarmaceutiAdminApoteke";
import DermatoloziAdminApoteke from "./DermatoloziAdminApoteke";
import NarucivanjeAdminApoteke from "./NarucivanjeAdminApoteke";
import axios from "axios";
import {mapActions} from 'vuex';

export default {
    name: "HomepageAdminApoteke",
    components:{
        ApotekaForm,
        AccountView,
        PasswordSwitch,
        LekoviAdminApoteke,
        FarmaceutiAdminApoteke,
        DermatoloziAdminApoteke,
        NarucivanjeAdminApoteke
    },
    data: () => ({
    showApoteka: false,
    showAccount: false,
    showPassword: false,
    showLekovi: false,
    showFarmaceuti: false,
    showDermatolozi: false,
    showNarucivanje: false,
    user: {},
  }),
  mounted(){
    console.log(window.location.pathname)
    
    
    axios.get("http://localhost:8080/adminApoteke/4").then(response => {
        console.log(response.data)
        this.user=response.data;
    });
    
  },
  methods:{
      ...mapActions({
        logout: "korisnici/logoutAction",
        resetStore: "resetStore"
      }),
      apotekaView(){
          this.showApoteka=true;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
      },
      accountView(){
          this.showApoteka=false;
          this.showAccount=true;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
      },

      onLogout(){
        this.logout();
        this.$router.push('/');
        this.resetStore();
      },

      changePassword(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=true;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
      },
      lekoviView(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = true;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
      },
      farmaceutiView(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = true;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
      },
      dermatoloziView(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = true;
          this.showNarucivanje = false;
      },
      narucivanjeView(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = true;
      },
  },
}

</script>

<style>

.wh{
    color:white;
}

 #app {
  background: url('https://images.unsplash.com/photo-1579165466741-7f35e4755660?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
  background-size: cover;
} 
</style>
