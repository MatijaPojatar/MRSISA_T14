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

          <v-list-item link @click="odsustvaView">
            <v-list-item-icon>
              <v-icon>mdi-palm-tree</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Odsustva
                </div></v-list-item-title>
          </v-list-item>

          <v-list-item link  @click="akcijeView">
            <v-list-item-icon>
              <v-icon>mdi-percent-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Akcije i promocije
                </div></v-list-item-title>
          </v-list-item>

          <v-list-item link  @click="izvestajiView">
            <v-list-item-icon>
              <v-icon>mdi-clipboard-text-multiple-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Izveštaji
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
        <PasswordSwitch :pass="apotekaId"  :id="user.id" :adminApoteke="true"/>
      </v-container>
      <v-container fluid v-if="showLekovi" :style="{width:'180vh'}" >
        <LekoviAdminApoteke :apotekaId="apotekaId" :user="user"/>
      </v-container>
      <v-container fluid v-if="showFarmaceuti" :style="{width:'180vh'}">
        <FarmaceutiAdminApoteke :apotekaId="apotekaId" :user="user"/>
      </v-container>
       <v-container fluid v-if="showDermatolozi" :style="{width:'180vh'}">
        <DermatoloziAdminApoteke :apotekaId="apotekaId" :user="user"/>
      </v-container>
      <v-container fluid v-if="showNarucivanje" :style="{width:'180vh'}">
        <NarucivanjeAdminApoteke :apotekaId="apotekaId" :user="user"/>
      </v-container>
      <v-container fluid v-if="showApoteka" :style="{width:'70vh'}">
        <ApotekaForm :apotekaId="apotekaId" :editable="true" />
      </v-container>
      <v-container fluid v-if="showOdsustva" :style="{width:'100vh'}">
        <TableOdsustva :apotekaId="apotekaId" :farmaceut=true />
      </v-container>
      <v-container fluid v-if="showAkcije" :style="{width:'70vh'}">
        <NovaAkcijaPromocija :apotekaId="apotekaId" :user="user" />
      </v-container>
      <v-container fluid v-if="showIzvestaji" :style="{width:'180vh'}">
        <IzvestajiAdminApoteke :apotekaId="apotekaId" />
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
import TableOdsustva from "./TableOdsustva";
import NovaAkcijaPromocija from "./NovaAkcijaPromocija";
import IzvestajiAdminApoteke from "./IzvestajiAdminApoteke";

import {mapActions, mapGetters} from 'vuex'

export default {
    name: "HomepageAdminApoteke",
    components:{
        ApotekaForm,
        AccountView,
        PasswordSwitch,
        LekoviAdminApoteke,
        FarmaceutiAdminApoteke,
        DermatoloziAdminApoteke,
        NarucivanjeAdminApoteke,
        TableOdsustva,
        NovaAkcijaPromocija,
        IzvestajiAdminApoteke,
    },
    data: () => ({
    showApoteka: false,
    showAccount: false,
    showPassword: false,
    showLekovi: false,
    showFarmaceuti: false,
    showDermatolozi: false,
    showNarucivanje: false,
    showOdsustva: false,
    showAkcije: false,
    showIzvestaji: false,
    
  }),
  async mounted(){
    console.log(window.location.pathname)
    await this.getApotekaIdForAdminApotekeAction(this.user.id)
    
  },
  computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik",
      apotekaId: "apoteke/getApotekaIdForAdminApoteke",
    }),
  },
  methods:{
      ...mapActions({
        logout: "korisnici/logoutAction",
        resetStore: "resetStore",
        getApotekaIdForAdminApotekeAction: "apoteke/getApotekaIdForAdminApotekeAction",
        
      }),
      apotekaView(){
          this.showApoteka=true;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
          this.showOdsustva = false;
          this.showAkcije = false;
          this.showIzvestaji = false;
      },
      accountView(){
          this.showApoteka=false;
          this.showAccount=true;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
          this.showOdsustva = false;
          this.showAkcije = false;
          this.showIzvestaji = false;
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
          this.showOdsustva = false;
          this.showAkcije = false;
          this.showIzvestaji = false;
      },
      lekoviView(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = true;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
          this.showOdsustva = false;
          this.showAkcije = false;
          this.showIzvestaji = false;
      },
      farmaceutiView(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = true;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
          this.showOdsustva = false;
          this.showAkcije = false;
          this.showIzvestaji = false;
      },
      dermatoloziView(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = true;
          this.showNarucivanje = false;
          this.showOdsustva = false;
          this.showAkcije = false;
          this.showIzvestaji = false;
      },
      narucivanjeView(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = true;
          this.showOdsustva = false;
          this.showAkcije = false;
          this.showIzvestaji = false;
      },
      odsustvaView(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
          this.showOdsustva = true;
          this.showAkcije = false;
          this.showIzvestaji = false;
      },
      akcijeView(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
          this.showOdsustva = false;
          this.showAkcije = true;
          this.showIzvestaji = false;
      },
      izvestajiView(){
          this.showApoteka=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showLekovi = false;
          this.showFarmaceuti = false;
          this.showDermatolozi = false;
          this.showNarucivanje = false;
          this.showOdsustva = false;
          this.showAkcije = false;
          this.showIzvestaji = true;
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
