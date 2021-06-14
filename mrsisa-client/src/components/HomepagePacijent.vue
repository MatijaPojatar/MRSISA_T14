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
                        <v-list-item-subtitle>
                            {{user.mail}}
                        </v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
            <v-divider></v-divider>
            <v-list
            nav
            dense
            >
                <v-list-item link @click="accountView">
                    <v-list-item-icon>
                        <v-icon>mdi-account-box</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Nalog
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link  @click="changePassword">
                    <v-list-item-icon>
                        <v-icon>mdi-lock-reset</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Promena šifre
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link  @click="apotekeView">
                    <v-list-item-icon>
                        <v-icon>mdi-gamepad-round-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Apoteke
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="allergyView">
                    <v-list-item-icon>
                        <v-icon>mdi-virus-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Alergije
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="rezLekoviView">
                    <v-list-item-icon>
                        <v-icon>mdi-medical-bag</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Rezervisani lekovi
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="rezervacijaLekaView">
                    <v-list-item-icon>
                        <v-icon>mdi-pill</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Rezervacija leka
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="calendarView">
                    <v-list-item-icon>
                        <v-icon>mdi-calendar</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Zakazane posete
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="noviPregledView">
                    <v-list-item-icon>
                        <v-icon>mdi-stethoscope</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Zakazivanje pregleda
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="novoSavetovanjeView">
                    <v-list-item-icon>
                        <v-icon>mdi-test-tube</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Zakazivanje savetovanja
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="istorijaPregledaView">
                    <v-list-item-icon>
                        <v-icon>mdi-calendar-check</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Istorija pregleda
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="istorijaSavetovanjaView">
                    <v-list-item-icon>
                        <v-icon>mdi-calendar-check</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Istorija savetovanja
                        </div>
                    </v-list-item-title>
                </v-list-item>
                 <v-list-item link @click="kupovinaView">
                    <v-list-item-icon>
                        <v-icon>mdi-cart-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Kupovina lekova
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="ereceptiView">
                    <v-list-item-icon>
                        <v-icon>mdi-note-text-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Moji E-Recepti
                        </div>
                    </v-list-item-title>
                </v-list-item>
                
                <v-list-item link @click="zalbaApotekaView">
                    <v-list-item-icon>
                        <v-icon>mdi-alpha-a-circle-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Žalba na apoteku
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="zalbaDermatologView">
                    <v-list-item-icon>
                        <v-icon>mdi-alpha-d-circle-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Žalba na dermatologa
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="zalbaFarmaceutView">
                    <v-list-item-icon>
                        <v-icon>mdi-alpha-f-circle-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Žalba na farmaceuta
                        </div>
                    </v-list-item-title>
                </v-list-item>
                 <v-list-item link @click="zalbePacijentaView">
                    <v-list-item-icon>
                        <v-icon>mdi-file-document-multiple-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Moje žalbe
                        </div>
                    </v-list-item-title>
                </v-list-item>
                <v-list-item link @click="akcijeView">
                    <v-list-item-icon>
                        <v-icon>mdi-sale</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                        <div class="wh">
                            Akcije i promocije
                        </div>
                    </v-list-item-title>
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
        <v-main>
            <v-container fluid fill-height v-if="showCalendar">
                <Calendar :pacijent="pacijent" :user="user"/>
            </v-container>
            <v-container fluid v-if="showAccount" :style="{width:'70vh'}">
                <AccountView :user="user" :pacijent="pacijent" :editable="true"/>
            </v-container>
            <v-container fluid v-if="showPassword" :style="{width:'70vh'}">
                <PasswordSwitch :pass="user.password" :pacijent="pacijent" :id="user.id"/>
            </v-container>
            <v-container fluid v-if="showAllergy" :style="{width:'70vh'}">
                <AddAllergy :pacijentId="user.id"/>
            </v-container>
            <v-container fluid v-if="showRezLekovi" :style="{width:'70vh'}">
                <RezervisaniLekovi/>
            </v-container>
            <v-container fluid v-if="showRezervacijaLeka" :style="{width:'70vh'}">
                <RezervacijaLeka/>
            </v-container>
            <v-container fluid v-if="showNoviPregled" :style="{width:'70vh'}">
                <NoviPregled/>
            </v-container>
            <v-container fluid v-if="showNovoSavetovanje" :style="{width:'70vh'}">
                <NovoSavetovanje/>
            </v-container>
            <v-container fluid v-if="showIstorijaPregleda" :style="{width:'70vh'}">
                <IstorijaPregleda/>
            </v-container>
            <v-container fluid v-if="showIstorijaSavetovanja" :style="{width:'70vh'}">
                <IstorijaSavetovanja/>
            </v-container>
             <v-container fluid v-if="showZalbaApoteka" :style="{width:'70vh'}">
                <ZalbaNaApoteku />
            </v-container>
             <v-container fluid v-if="showZalbaFarmaceut" :style="{width:'70vh'}">
                <ZalbaNaFarmaceuta />
            </v-container>
             <v-container fluid v-if="showZalbaDermatolog" :style="{width:'70vh'}">
                <ZalbaNaDermatologa :id="user.id"/> <!--mislim da ne treba prop-->
            </v-container>
            <v-container fluid v-if="showZalbePacijenta" :style="{width:'70vh'}">
                <ZalbePacijenta/>
            </v-container>
            <v-container fluid v-if="showAkcije" :style="{width:'70vh'}">
                <AkcijePromocije/>
            </v-container>
            <v-container fluid v-if="showApoteke" :style="{width:'180vh'}">
                <ProfilApoteke :apotekaId=2 :registrovan="true" :user="user"/>
            </v-container>
            <v-container fluid v-if="showErecepti" :style="{width:'70vh'}">
                <PregledERecepata/>
            </v-container>
            <v-container fluid v-if="showKupovina" :style="{width: '70vh'}">
                <KupovinaLekova/>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>

import Calendar from "./CalendarPacijent";
import AccountView from "./AccountView";
import PasswordSwitch from "./PasswordSwitch";
import AddAllergy from "./AddAllergy";
import RezervisaniLekovi from "./RezervisaniLekovi"
import RezervacijaLeka from "./RezervacijaLeka";
import NoviPregled from "./NoviPregled";
import NovoSavetovanje from "./NovoSavetovanje";
import IstorijaPregleda from "./IstorijaPregleda";
import IstorijaSavetovanja from "./IstorijaSavetovanja";
import ZalbaNaApoteku from "./Pacijent/ZalbaNaApoteku";
import ZalbaNaDermatologa from "./Pacijent/ZalbaNaDermatologa";
import ZalbaNaFarmaceuta from "./Pacijent/ZalbaNaFarmaceuta";
import ZalbePacijenta from "./Pacijent/ZalbePacijenta";
import AkcijePromocije from "./AkcijePromocije";
import ProfilApoteke from "./ProfilApoteke";
import PregledERecepata from "./Pacijent/PregledERecepata";
import KupovinaLekova from "./Pacijent/KupovinaLekova"
import {mapActions, mapGetters} from 'vuex';

export default {
    name: "HomepageDF",
    components:{
        Calendar,
        AccountView,
        PasswordSwitch,
        AddAllergy,
        RezervisaniLekovi,
        RezervacijaLeka,
        NoviPregled,
        NovoSavetovanje,
        IstorijaPregleda,
        IstorijaSavetovanja,
        ZalbaNaApoteku,
        ZalbaNaDermatologa,
        ZalbaNaFarmaceuta,
        ZalbePacijenta,
        AkcijePromocije,
        ProfilApoteke,
        PregledERecepata,
        KupovinaLekova
    },
    data: () => ({
    showCalendar: false,
    showAccount: false,
    showPassword: false,
    showAllergy: false,
    showRezLekovi: false,
    showRezervacijaLeka: false,
    showNoviPregled: false,
    showNovoSavetovanje: false,
    showIstorijaPregleda: false,
    showIstorijaSavetovanja: false,
    showZalbaApoteka : false,
    showZalbaFarmaceut : false,
    showZalbaDermatolog : false,
    showZalbePacijenta: false,
    showAkcije: false,
    showApoteke: false,
    showErecepti: false,
    showKupovina: false,
  }),
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

      calendarView(){
          this.showCalendar = true;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije = false;
          this.showApoteke = false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      accountView(){
          this.showCalendar = false;
          this.showAccount = true;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije = false;
          this.showApoteke = false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      changePassword(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = true;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije = false;
          this.showApoteke = false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      allergyView(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = true;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije = false;
          this.showApoteke = false;
      },
      rezLekoviView(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = true;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije = false;
          this.showApoteke = false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      rezervacijaLekaView(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = true;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije = false;
          this.showApoteke = false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      noviPregledView(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = true;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije=false;
          this.showApoteke=false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      novoSavetovanjeView(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = true;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije=false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      istorijaPregledaView(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = true;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije=false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      istorijaSavetovanjaView(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = true;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije=false;
          this.showApoteke=false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      zalbaApotekaView(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = true;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije=false;
          this.showApoteke=false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      zalbaDermatologView(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = true;
          this.showZalbePacijenta = false;
          this.showAkcije=false;
          this.showApoteke=false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      zalbaFarmaceutView() {
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = true;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije=false;
          this.showApoteke=false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      zalbePacijentaView() {
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = true;
          this.showAkcije=false;
          this.showApoteke=false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      akcijeView(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showIstorijaPregleda = false;
          this.showIstorijaSavetovanja = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije=true;
          this.showApoteke=false;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      apotekeView(){
          this.showCalendar = false;
          this.showAccount = false;
          this.showPassword = false;
          this.showAllergy = false;
          this.showRezLekovi = false;
          this.showRezervacijaLeka = false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije=false;
          this.showApoteke=true;
          this.showErecepti = false;
          this.showKupovina = false;
      },
      ereceptiView(){
          this.showCalendar=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showAllergy=false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije=false;
          this.showApoteke=false;
          this.showErecepti = true;
          this.showKupovina = false;
      },
      kupovinaView(){
          this.showCalendar=false;
          this.showAccount=false;
          this.showPassword=false;
          this.showAllergy=false;
          this.showNoviPregled = false;
          this.showNovoSavetovanje = false;
          this.showZalbaApoteka = false;
          this.showZalbaFarmaceut = false;
          this.showZalbaDermatolog = false;
          this.showZalbePacijenta = false;
          this.showAkcije=false;
          this.showApoteke=false;
          this.showErecepti = false;
          this.showKupovina = true;
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
