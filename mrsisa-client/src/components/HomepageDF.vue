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
          <v-list-item link @click="calendarView">
            <v-list-item-icon>
              <v-icon>mdi-calendar</v-icon>
            </v-list-item-icon>
            <v-list-item-title><div class="wh">
                    Kalendar
                </div></v-list-item-title>
          </v-list-item>
        </v-list>

        <template v-slot:append>
        <div class="pa-2">
          <v-btn block @click="logout">
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
        <AccountView :user="user" :farmaceut="farmaceut"/>
      </v-container>
      <v-container fluid v-if="showPassword" :style="{width:'70vh'}">
        <PasswordSwitch :pass="user.password" :farmaceut="farmaceut" :id="user.id"/>
      </v-container>
    </v-main>
      </v-app>
</template>

<script>

import Calendar from "./Calendar";
import AccountView from "./AccountView";
import PasswordSwitch from "./PasswordSwitch";
import axios from "axios";

export default {
    name: "HomepageDF",
    components:{
        Calendar,
        AccountView,
        PasswordSwitch
    },
    data: () => ({
    showCalendar: false,
    showAccount: false,
    showPassword: false,
    farmaceut: true,
    user: {},
  }),
  mounted(){
    console.log(window.location.pathname)
    if(window.location.pathname.includes("dermatolog")){
        this.farmaceut=false;
    }
    if(this.farmaceut){
      axios.get("http://localhost:8080/farmaceut/1").then(response => {
          console.log(response.data)
          this.user=response.data;
        });
    }else{
      axios.get("http://localhost:8080/dermatolog/3").then(response => {
          console.log(response.data)
          this.user=response.data;
      });
    }
  },
  methods:{
      calendarView(){
          this.showCalendar=true;
          this.showAccount=false;
          this.showPassword=false;
      },
      accountView(){
          this.showCalendar=false;
          this.showAccount=true;
          this.showPassword=false;
      },
      logout(){
        window.location.href="http://localhost:8081/";
      },
      changePassword(){
          this.showCalendar=false;
          this.showAccount=false;
          this.showPassword=true;
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
