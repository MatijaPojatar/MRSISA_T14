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
              <v-img :src="baseUrl + 'men/' + user.id + '.jpg'" v-if="user.pol==='MUSKI'"></v-img>
              <v-img :src="baseUrl + 'women/' + user.id + '.jpg'" v-if="user.pol==='ZENSKI'"></v-img>
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
          <v-list-item link @click="calendarView">
            <v-list-item-icon>
              <v-icon>mdi-calendar</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Kalendar
                </div></v-list-item-title>
          </v-list-item>
          <v-list-item link @click="odsustvoView">
            <v-list-item-icon>
              <v-icon>mdi-coffee</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Zahtev za odsutsvo
                </div></v-list-item-title>
          </v-list-item>
          <v-list-item link @click="allPacijentView">
            <v-list-item-icon>
              <v-icon>mdi-account-group</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Svi pacijenti
                </div></v-list-item-title>
          </v-list-item>
          <v-list-item link @click="pacijentView">
            <v-list-item-icon>
              <v-icon>mdi-account-multiple</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Pregledani pacijenti 
                </div></v-list-item-title>
          </v-list-item>
          <v-list-item link @click="rezervacijaView" v-if="farmaceut">
            <v-list-item-icon>
              <v-icon>mdi-pill</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Preuzimanje leka 
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
      <v-container fluid fill-height v-if="showCalendar">
        <Calendar :farmaceut="farmaceut" :user="user"/>
      </v-container>
      <v-container fluid v-if="showAccount" :style="{width:'70vh'}">
        <AccountView :user="user" :farmaceut="farmaceut" :editable="true"/>
      </v-container>
      <v-container fluid v-if="showPassword" :style="{width:'70vh'}">
        <PasswordSwitch @password-changed="closeAll" :pass="user.password" :farmaceut="farmaceut" :id="user.id"/>
      </v-container>
      <v-container fluid v-if="showOdsustvo" :style="{width:'70vh'}">
        <CreateOdsustvo :farmaceut="farmaceut" :doktorId="user.id"/>
      </v-container>
      <v-container fluid v-if="showPacijenti" :style="{width:'70vh'}">
        <PacijentList :farmaceut="farmaceut" :doktorId="user.id"/>
      </v-container>
      <v-container fluid v-if="showAllPacijenti" :style="{width:'70vh'}">
        <SearchPacijenti/>
      </v-container>
      <v-container fluid v-if="showRezervacija" :style="{width:'70vh'}" grid-list-md>
        <RezervacijaInput :id="user.id"/>
      </v-container>
    </v-main>
      </v-app>
</template>

<script>

import Calendar from "./Calendar";
import AccountView from "./AccountView";
import PasswordSwitch from "./PasswordSwitch";
import CreateOdsustvo from "./CreateOdsustvo";
import PacijentList from "./PacijentList"
import SearchPacijenti from "./SearchPacijenti"
import RezervacijaInput from "./RezervacijaInput";
import {mapActions, mapGetters} from 'vuex'

export default {
    name: "HomepageDF",
    components:{
        Calendar,
        AccountView,
        PasswordSwitch,
        CreateOdsustvo,
        PacijentList,
        SearchPacijenti,
        RezervacijaInput,
    },
    data: () => ({
    showCalendar: false,
    showAccount: false,
    showPassword: false,
    showOdsustvo: false,
    showPacijenti: false,
    showAllPacijenti: false,
    showRezervacija: false,
    farmaceut: true,
    baseUrl: "https://randomuser.me/api/portraits/",
  }),
  computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik",
    }),
  },
  mounted(){
    console.log(window.location.pathname)
    if(window.location.pathname.includes("dermatolog")){
        this.farmaceut=false;
    }
  },
  methods:{
      ...mapActions({
        logout: "korisnici/logoutAction",
        resetStore: "resetStore"
      }),
      closeAll(){
        this.showCalendar=false;
        this.showAccount=false;
        this.showPassword=false;
        this.showOdsustvo=false;
        this.showPacijenti=false;
        this.showAllPacijenti= false;
        this.showRezervacija= false;
      },
      calendarView(){
          this.closeAll();
          this.showCalendar=true;
      },
      accountView(){
          this.closeAll();
          this.showAccount=true;
      },
      odsustvoView(){
          this.closeAll();
          this.showOdsustvo=true;
      },

      onLogout(){
        this.logout();
        this.$router.push('/');
        this.resetStore();
      },

      // logout(){
      //   window.location.href="http://localhost:8081/";
      // },
      changePassword(){
          this.closeAll();
          this.showPassword=true;
      },
      pacijentView(){
        this.closeAll();
        this.showPacijenti=true;
      },
      allPacijentView(){
        this.closeAll();
        this.showAllPacijenti= true;
      },
      rezervacijaView(){
        this.closeAll();
        this.showRezervacija= true;
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
