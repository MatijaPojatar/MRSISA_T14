<template>
  <v-card v-if="nastavi">
    <v-card-title class="display-1">Registracija Zaposlenog</v-card-title>

    <v-card-text>
      <v-form ref="forma" v-model="valid" lazy-validation>
        <v-row>
        <v-col>

          <!--FORMA ZA SVE -->

          <v-text-field
            v-model="email"
            :rules="[rules.required, rules.email]"
            label="Email"
            required
          />

          <v-text-field
            v-model="ime"
            :rules="[rules.required]"
            label="Ime"
            required
          />

          <v-text-field
            v-model="prezime"
            :rules="[rules.required]"
            label="Prezime"
            required
          />

          <v-text-field
            v-model="brojTelefona"
            :rules="[rules.required, rules.telefon]"
            label="Broj telefona"
            required
          />

          <v-select
          v-model="pol"
          :items="polovi"
          label="Pol"
          required
          ></v-select>

          <v-text-field
          v-model="adresa"
          :rules="[rules.required]"
          label="Adresa"
          required
          />

          <v-text-field
          v-model="grad"
          :rules="[rules.required]"
          label="Grad"
          required
          />

          <v-text-field
          v-model="drzava"
          :rules="[rules.required]"
          label="Država"
          required
          />

        </v-col>
        <v-col>
          <v-text-field
            value="Datum Rođenja"
            readonly
          />
          <v-date-picker v-model="datum"></v-date-picker>
           
          
        </v-col>
        <v-col>

          <!-- GOTOV-->

          <!-- ADMIN SISTEMA -->
          <v-text-field
          v-if="uloga === uloge[0]"
          v-model="potpis"
          label="Potpis"
          />
          <!-- GOTOV -->


          <!-- ADMIN APOTEKE-->
          <v-autocomplete
          v-if="uloga === uloge[1]"
          v-model="izabranaApotekaId"
          :items="allApoteke"
          label="Apoteka"
          item-text="naziv"
          item-value="id"
          required>

        
          <template slot="selection" slot-scope="data">
            <!-- HTML that describe how select should render selected items -->
            {{ data.item.naziv }} 
          </template>

          <template slot="item" slot-scope="data">
            <!-- HTML that describe how select should render items when the select is open -->
            {{ data.item.naziv }}
          </template>
        </v-autocomplete>
          <!-- GOTOV -->



          <!-- DERMATOLOG-->
            <v-time-picker
            v-if="uloga === uloge[2]"
            v-model="pocetakRadnogVremena"
            class="mt-4"
            format="ampm"
            scrollable
          ></v-time-picker>

           <v-time-picker
           v-if="uloga === uloge[2]"
            v-model="krajRadnogVremena"
            class="mt-4"
            format="ampm"
            scrollable
          ></v-time-picker>
          <!-- GOTOV -->

          <!-- DOBTAVLJAC-->
          <v-text-field
          v-if="uloga === uloge[3]"
            class="blue--text"
            v-model="nazivPreduzeca"
            label="Naziv preduzeća"
          />
          <!-- GOTOV -->

          

        </v-col>
        </v-row>
        </v-form>
    </v-card-text>
    <v-card-actions>
        <v-btn @click="cancel" class="grey white--text">Odustani</v-btn>
        <v-spacer></v-spacer>

        <v-btn v-if="uloga === uloge[0]"
        v-on:click="onSubmitAS" 
        :disabled="!valid" 
        class="blue white--text"> 
        Registracija Administratora sistema</v-btn>

        <v-btn v-if="uloga === uloge[1]"
        v-on:click="onSubmitAAP" 
        :disabled="!valid" 
        class="blue white--text"> 
        Registracija Administratora apoteke</v-btn>

        <v-btn v-if="uloga === uloge[2]"
        v-on:click="onSubmitDerm" 
        :disabled="!valid" 
        class="blue white--text"> 
        Registracija dermatologa</v-btn>

        <v-btn 
        v-if="uloga === uloge[3]"
        v-on:click="onSubmitDob" 
        :disabled="!valid" 
        class="blue white--text"> 
        Registracija Dobavljača</v-btn>

    </v-card-actions>


    <v-dialog
      v-model="odabir"
      persistent
      max-width="500">
    <v-card>
       <v-container>

            <v-row>
              <v-list-item>
                  <v-chip  dark  color="grey">Uloga zaposlenog</v-chip>
                  <v-spacer></v-spacer>

                  <v-chip class="ml-3 mr-3" dark color="grey">Odaberi</v-chip>
              </v-list-item>
            </v-row>
            <v-row>
              <v-virtual-scroll
              :items="uloge"
              :item-height="50"
              height="300"
              >
                <template v-slot:default="{ item }">
                  <v-list-item>

                    <v-list-item-content>
                      <v-list-item-title class="text-left">{{item.naziv}}</v-list-item-title>
                    </v-list-item-content>

                    <v-spacer/>

                    <v-list-item-action>

                      <v-btn red v-on:click="odaberiUlogu(item)">
                        <v-icon>mdi-plus-thick</v-icon>
                      </v-btn>
                    </v-list-item-action>

                  </v-list-item>
                </template>

              </v-virtual-scroll>

            </v-row>
          </v-container>
      <v-card-actions>
        <v-btn
        v-on:click="goBack" 
        class="blue white--text">Cancel</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>




  </v-card>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';


export default {

  name: "RegistracijaZaposlenog",
  data: () => ({
    izabranaApotekaId: -1,
    odabir: true,
    uloga: {},
    nastavi: true,

    uloge:[{ id: 1, naziv: "Administrator sistema"},
          { id: 2, naziv: "Administrator apoteke"},
          { id: 3, naziv: "Dermatolog"},
          { id: 4, naziv: "Dobavljač"}],


    polovi: ["MUSKI", "ZENSKI"],

    email: "",
    lozinka: "",
    potvrdaLozinke: "",

    ime: "",
    prezime: "",
    brojTelefona: "",
    datum: "",
    pol: "",

    adresa: "",
    grad: "",
    drzava: "",

    pocetakRadnogVremena: "",
    krajRadnogVremena: "",
    nazivPreduzeca: "",
    potpis: "",

    apotekeGdeJeZaposlenString: "",
    apotekeGdeJeZaposlen: [],
    valid: false,
    show1: false,
    show2: false,
    rules: {
      required: v => !!v || "Obavezno polje",
      email: v => /.+@.+\..+/.test(v) || "Email nije validan",
      telefon: v => /^\d+$/.test(v) || "Broj telefona nije validan",
      counter: v => v.length == 10 || 'Datum nije dobar',
      counterVreme: v => v.length == 5 || "Vreme nije dobro",
    }
  }),

  computed: {
    ...mapGetters({
      allApoteke: "apoteke/getAllApotekeMinimal"
    }),
      show: {
          get() {
              return this.value;
          },
          set(value){
              this.$emit("input", value);
          }
      },
  },

  async mounted(){
    await this.getAllApotekeMinimalAction();
  },

  methods: {
    ...mapActions({
      getAllApotekeMinimalAction: "apoteke/getAllApotekeMinimalAction",
      dermSignUpAction: "korisnici/dermSignUpAction",
      dobSignUpAction: "korisnici/dobSignUpAction",
      aApSignUpAction: "korisnici/aApSignUpAction",
      asSignUpAction: "korisnici/asSignUpAction"

    }),

    goBack() {
      this.nastavi = false
    },

    async onSubmitAAP(){
      alert("Submit admina apoteke klik");
      if(this.izabranaApotekaId === -1){
        alert("Potrebno je odabrati apoteku gde je admin zaposlen!");
        return;
      }
      const aapDTO = {
        ime: this.ime,
        prezime: this.prezime,
        mail: this.mail,
        adresa: this.adresa,
        grad: this.grad,
        drzava: this.drzava,
        brojTelefona: this.brojTelefona,
        pol: this.pol,
        datumRodjenja: this.datum,
        apotekaId: this.izabranaApotekaId,
      }
      try{
        ///////////////////////
        await this.aApSignUpAction(aapDTO);
        alert("Uspesna registracija "+ this.ime + this.prezime);
        this.cancel();
        console.log(aapDTO);
      }catch(error){
        alert("Greska pri registraciji admina apoteke")
      }

    },

    async onSubmitAS(){
      alert("Submit Admin sistema klik");
      if(this.potpis === ""){
        alert("Potrebno uneti potpis administratora");
        return;
      }

      const asDTO ={
        ime: this.ime,
        prezime: this.prezime,
        mail: this.mail,
        adresa: this.adresa,
        grad: this.grad,
        drzava: this.drzava,
        brojTelefona: this.brojTelefona,
        pol: this.pol,
        datumRodjenja: this.datum,
        potpis: this.potpis
      }

       try{
        ///////////////////////
        await this.asSignUpAction(asDTO);
        alert("Uspesna registracija "+ this.ime + this.prezime);
        this.cancel();
        console.log(asDTO);
      }catch(error){
        alert("Greska pri registraciji admina sistema")
      }

    },

    odaberiUlogu(uloga){
      this.odabir = false;
      this.uloga = uloga;
    },

    async onSubmitDerm(){
     alert("Submit derm klik");
     if(this.pocetakRadnogVremena === "" || this.krajRadnogVremena === ""){
       alert("Potreban unos radnog vremena");
       return;
     }

      const dermDTO = {
        ime: this.ime,
        prezime: this.prezime,
        mail: this.mail,
        adresa: this.adresa,
        grad: this.grad,
        drzava: this.drzava,
        brojTelefona: this.brojTelefona,
        pol: this.pol,
        datumRodjenja: this.datum,
        pocetakRadnogVremena: this.pocetakRadnogVremena,
        krajRadnogVremena: this.krajRadnogVremena
      }
      try{
        ///////////////////////
        await this.dermSignUpAction(dermDTO);
        alert("Uspesna registracija "+ this.ime + this.prezime);
        this.cancel();
        console.log(dermDTO);
      }catch(error){
        alert("Greska pri registraciji dermatologa")
      }
    },

    async onSubmitDob(){
      alert("Sumbit Dobavljac klik");
      if(this.nazivPreduzeca === ""){
        alert("Potreban unos naziva preduzeca!");
        return;
      }
      const dobDTO = {
        ime: this.ime,
        prezime: this.prezime,
        mail: this.mail,
        adresa: this.adresa,
        grad: this.grad,
        drzava: this.drzava,
        brojTelefona: this.brojTelefona,
        pol: this.pol,
        datumRodjenja: this.datum,
        nazivPreduzeca: this.nazivPreduzeca,
      }
      try{
        ///////////////////////
        await this.dobSignUpAction(dobDTO);
        alert("Uspesna registracija "+ this.ime + this.prezime);
        this.cancel();
        console.log(dobDTO);
      }catch(error){
        alert("Greska pri registraciji dobavljaca")
      }
      
    },

    cancel(){
      this.$refs.forma.reset();
      this.$refs.forma.resetValidation();
    }
  }

}
</script>

<style>

</style>