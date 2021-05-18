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
          
          <v-list-item link @click="kreirajLek">
            <v-list-item-icon>
              <v-icon>mdi-pill</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Unesi lek
                </div></v-list-item-title>
          </v-list-item>

          <v-list-item link @click="registrujApoteku">
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Registruj apoteku
                </div></v-list-item-title>
          </v-list-item>

          <v-list-item link @click="registrujZaposlenog">
            <v-list-item-icon>
              <v-icon>mdi-stethoscope</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Registruj zaposlenog
                </div></v-list-item-title>
          </v-list-item>

          <v-list-item link @click="odgovoriNaZalbe">
            <v-list-item-icon>
              <v-icon>mdi-alert-circle-check</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Odgovori na zalbe
                </div></v-list-item-title>
          </v-list-item>

          <v-list-item link @click="mojeZalbe">
            <v-list-item-icon>
              <v-icon>mdi-note-multiple</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Moje Žalbe
                </div></v-list-item-title>
          </v-list-item>

           <v-list-item link @click="odsustvaView">
            <v-list-item-icon>
              <v-icon>mdi-palm-tree</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Odsustva
                </div></v-list-item-title>
          </v-list-item>


        </v-list>

        <template v-slot:append>
        <div class="pa-2">
            <v-switch
            v-model="$vuetify.theme.dark"
            label="Dark mode"
            color="black"
            ></v-switch>
          <v-btn block @click="onLogout">
            Logout
          </v-btn>
        </div>
      </template>
    </v-navigation-drawer>

    <v-main >
     <v-container fluid v-if="showAccount" :style="{width:'70vh'}">
        <AccountView :user="user" />
      </v-container>
      <v-container fluid v-if="showPassword" :style="{width:'70vh'}">
        <PasswordSwitch :pass="user.password"  :id="user.id"/>
      </v-container>
      
      <v-containter fluid v-if="showKreirajLek" :style="{width:'70vh'}">
        <KreirajLek/>
      </v-containter>

      <v-container fluid v-if="showRegApoteku" :style="{width:'70vh'}">
        <RegApoteka/>
      </v-container>

      <v-container fluid v-if="showRegZaposlenog" :style="{width:'70vh'}">
        <RegZaposlenog/>
      </v-container>
      
      <v-container fluid v-if="showZalbeOdgovor" :style="{width:'70vh'}">
        <ZalbeOdgovor/>
      </v-container>

      <v-container fluid v-if="showMojeZalbe" :style="{width:'70vh'}">
        <ZalbeAdmina/>
      </v-container>

      <v-container fluid v-if="showOdsustva" :style="{width:'70vh'}">
        <TableOdsustva  :farmaceut=false />
      </v-container>

    </v-main>
      </v-app>
</template>

<script>

import AccountView from "../AccountView";
import PasswordSwitch from "../PasswordSwitch";
import KreirajLek from "./KreirajLek";
import RegApoteka from "./RegApoteka";
import RegZaposlenog from "./RegZaposlenog";
import ZalbeOdgovor from "./ZalbeOdgovor";
import ZalbeAdmina from "./ZalbeAdmina";
import TableOdsustva from "../TableOdsustva";

import {mapActions, mapGetters} from 'vuex'

export default {
    name: "HomepageAdminApoteke",
    components:{
      AccountView,
      PasswordSwitch,
      KreirajLek,
      RegApoteka,
      RegZaposlenog,
      ZalbeOdgovor,
      ZalbeAdmina,
      TableOdsustva,
    },
    data: () => ({
    showAccount: false,
    showPassword: false,
    showKreirajLek: false,
    showRegApoteku: false,
    showRegZaposlenog: false,
    showZalbeOdgovor: false,
    showMojeZalbe: false,
    showOdsustva: false,
  }),
  mounted(){
    console.log(window.location.pathname) //?
 
    
  },
  computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik",
    }),
  },
  methods:{
    ...mapActions({
      logout: "korisnici/logoutAction",
      resetStore: "resetStore"
    }),

    accountView(){
      this.showAccount=true;
      this.showPassword=false;
      this.showKreirajLek = false;
      this.showRegApoteku = false;
      this.showRegZaposlenog = false;
      this.showZalbeOdgovor = false;
      this.showMojeZalbe = false;
      this.showOdsustva = false;
    },

    changePassword(){
      this.showAccount=false;
      this.showPassword=true;
      this.showKreirajLek = false;
      this.showRegApoteku = false;
      this.showRegZaposlenog = false;
      this.showZalbeOdgovor = false;
      this.showMojeZalbe = false;
      this.showOdsustva = false;
    },

    kreirajLek() {
      this.showAccount=false;
      this.showPassword=false;
      this.showKreirajLek = true;
      this.showRegApoteku = false;
      this.showRegZaposlenog = false;
      this.showZalbeOdgovor = false;
      this.showMojeZalbe = false;
      this.showOdsustva = false;
    },

    registrujApoteku(){
      this.showAccount=false;
      this.showPassword=false;
      this.showKreirajLek = false;
      this.showRegApoteku = true;
      this.showRegZaposlenog = false;
      this.showZalbeOdgovor = false;
      this.showMojeZalbe = false;
      this.showOdsustva = false;
    },

    registrujZaposlenog(){
      this.showAccount=false;
      this.showPassword=false;
      this.showKreirajLek = false;
      this.showRegApoteku = false;
      this.showRegZaposlenog = true;
      this.showZalbeOdgovor = false;
      this.showMojeZalbe = false;
      this.showOdsustva = false;
    },

    odgovoriNaZalbe(){
      this.showAccount=false;
      this.showPassword=false;
      this.showKreirajLek = false;
      this.showRegApoteku = false;
      this.showRegZaposlenog = false;
      this.showZalbeOdgovor = true;
      this.showMojeZalbe = false;
      this.showOdsustva = false;
    },

    mojeZalbe(){
       this.showAccount=false;
      this.showPassword=false;
      this.showKreirajLek = false;
      this.showRegApoteku = false;
      this.showRegZaposlenog = false;
      this.showZalbeOdgovor = false;
      this.showMojeZalbe = true;
      this.showOdsustva = false;
    },

    odsustvaView(){
       this.showAccount=false;
      this.showPassword=false;
      this.showKreirajLek = false;
      this.showRegApoteku = false;
      this.showRegZaposlenog = false;
      this.showZalbeOdgovor = false;
      this.showMojeZalbe = false;
      this.showOdsustva = true;
    },

    onLogout(){
      this.logout();
      this.$router.push('/');
      this.resetStore();
    }

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
