<template>
  <v-app>
    <v-navigation-drawer
      clipped
      fixed
      app
      expand-on-hover
      src="https://images.unsplash.com/photo-1599652749620-e45b7f38988e?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2089&q=80"
    >
      <v-list>
        <v-list-item class="px-2">
          <v-list-item-avatar>
            <v-img
              src="https://randomuser.me/api/portraits/men/85.jpg"
              v-if="user.pol === 'MUSKI'"
            ></v-img>
            <v-img
              src="https://randomuser.me/api/portraits/women/85.jpg"
              v-if="user.pol === 'ZENSKI'"
            ></v-img>
          </v-list-item-avatar>
        </v-list-item>

        <v-list-item link>
          <v-list-item-content>
            <v-list-item-title class="title">
              <div class="wh">{{ user.ime }} {{ user.prezime }}</div>
            </v-list-item-title>
            <v-list-item-subtitle>{{ user.mail }}</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-divider></v-divider>

      <v-list nav dense>
        <v-list-item link>
          <v-list-item-icon>
            <v-icon>mdi-view-dashboard</v-icon>
          </v-list-item-icon>
          <v-list-item-title><div class="wh">Dashboard</div></v-list-item-title>
        </v-list-item>
        <v-list-item link @click="accountView">
          <v-list-item-icon>
            <v-icon>mdi-account-box</v-icon>
          </v-list-item-icon>
          <v-list-item-title><div class="wh">Nalog</div></v-list-item-title>
        </v-list-item>
        <v-list-item link @click="changePassword">
          <v-list-item-icon>
            <v-icon>mdi-lock-reset</v-icon>
          </v-list-item-icon>
          <v-list-item-title
            ><div class="wh">Promeni šifru</div></v-list-item-title
          >
        </v-list-item>

        <v-list-item link @click="kreirajPonudu">
          <v-list-item-icon>
            <v-icon>mdi-note-plus</v-icon>
          </v-list-item-icon>
          <v-list-item-title
            ><div class="wh">Kreiraj Ponudu</div></v-list-item-title
          >
        </v-list-item>

        <v-list-item link @click="pregledPonuda">
          <v-list-item-icon>
            <v-icon>mdi-note-multiple</v-icon>
          </v-list-item-icon>
          <v-list-item-title
            ><div class="wh">Pregled ponuda</div></v-list-item-title
          >
        </v-list-item>

        <!-- <v-list-item link @click="lekoviNaStanju">
          <v-list-item-icon>
            <v-icon>mdi-pill</v-icon>
          </v-list-item-icon>
          <v-list-item-title
            ><div class="wh">Magacin lekova</div></v-list-item-title
          >
        </v-list-item>-->
      </v-list> 

      <template v-slot:append>
        <div class="pa-2">
          <v-switch
            v-model="$vuetify.theme.dark"
            label="Dark mode"
            color="black"
          ></v-switch>
          <v-btn block @click="onLogout"> Logout </v-btn>
        </div>
      </template>
    </v-navigation-drawer>

    <v-main>
      <v-container fluid v-if="showAccount" :style="{ width: '70vh' }">
        <AccountView :user="user" :editable="true" :dobavljac="true" />
      </v-container>
      <v-container fluid v-if="showPassword" :style="{ width: '70vh' }">
        <PasswordSwitch :pass="user.password" :id="user.id" />
      </v-container>

      <v-container fluid v-if="showKreirajPonudu" :style="{ width: '70vh' }">
        <KreirajPonudu />
      </v-container>

      <v-container fluid v-if="showPregledPonuda" :style="{ width: '70vh' }">
        <PregledPonuda />
      </v-container>

      <!-- <v-container fluid v-if="showLekoviNaStanju" :style="{ width: '70vh' }">
        <LekoviNaStanju />
      </v-container> -->
    </v-main>
  </v-app>
</template>

<script>
import AccountView from "../AccountView";
import PasswordSwitch from "../PasswordSwitch";
import KreirajPonudu from "./KreirajPonudu";
// import LekoviNaStanju from "./LekoviNaStanju";
import PregledPonuda from "./PregledPonuda";

import { mapActions, mapGetters } from "vuex";

export default {
  name: "HomepageDobavljac",
  components: {
    AccountView,
    PasswordSwitch,
    KreirajPonudu,
    // LekoviNaStanju,
    PregledPonuda,
  },
  data: () => ({
    showAccount: false,
    showPassword: false,
    showKreirajPonudu: false,
    showPregledPonuda: false,
    showLekoviNaStanju: false,
  }),
  mounted() {},
  computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik",
    }),
  },
  methods: {
    ...mapActions({
      logout: "korisnici/logoutAction",
      resetStore: "resetStore",
    }),

    accountView() {
      this.showAccount = true;
      this.showPassword = false;
      this.showKreirajPonudu = false;
      this.showPregledPonuda = false;
      this.showLekoviNaStanju = false;
    },

    changePassword() {
      this.showAccount = false;
      this.showPassword = true;
      this.showKreirajPonudu = false;
      this.showPregledPonuda = false;
      this.showLekoviNaStanju = false;
    },

    kreirajPonudu() {
      this.showAccount = false;
      this.showPassword = false;
      this.showKreirajPonudu = true;
      this.showPregledPonuda = false;
      this.showLekoviNaStanju = false;
    },

    pregledPonuda() {
      this.showAccount = false;
      this.showPassword = false;
      this.showKreirajPonudu = false;
      this.showPregledPonuda = true;
      this.showLekoviNaStanju = false;
    },

    lekoviNaStanju() {
      this.showAccount = false;
      this.showPassword = false;
      this.showKreirajPonudu = false;
      this.showPregledPonuda = false;
      this.showLekoviNaStanju = true;
    },

    onLogout() {
      this.logout();
      this.$router.push("/");
      this.resetStore();
    },
  },
};
</script>

<style>
.wh {
  color: white;
}

#app {
  background: url("https://images.unsplash.com/photo-1579165466741-7f35e4755660?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
  background-size: cover;
}
</style>
