<template>
  <div class="justify-center" widt>
  <v-card class="mt-16 mx-auto" >
    <v-row align="center" justify="space-around">
      <v-col align="center"> Ocena: </v-col>
      <v-col>
        <v-checkbox v-model="ocena2" label="< 2" />
      </v-col>
      <v-col>
        <v-checkbox v-model="ocena24" label="2 - 4" />
      </v-col>
      <v-col>
        <v-checkbox v-model="ocena4" label="> 4" />
      </v-col>
    </v-row>
    <v-divider />
    <v-row align="center" justify="space-around">
      <v-col align="center"> Udaljenost: </v-col>
      <v-col>
        <v-checkbox v-model="udaljenost5" label="< 500" />
      </v-col>
      <v-col>
        <v-checkbox v-model="udaljenost510" label="500 - 1000" />
      </v-col>
      <v-col>
        <v-checkbox v-model="udaljenost10" label="> 1000" />
      </v-col>
    </v-row>
    <v-divider />
    <v-data-table :headers="headeri" :items="filtriraneApoteke">
      <template v-slot:item.profil="{ item }">
        <v-btn class="mx-2" dark color="blue" @click="profilApoteke(item)">
          Otvori
        </v-btn>
      </template>
    </v-data-table>
    <v-divider />
    <v-row no-gutters justify="space-around">
      <v-col md="4">
        <v-text-field
          v-model="pretragaParams.naziv"
          label="Naziv"
        ></v-text-field>
      </v-col>
      <v-col md="4">
        <v-text-field v-model="pretragaParams.grad" label="Grad">
        </v-text-field>
      </v-col>
    </v-row>
    <v-card-actions class="justify-center">
      <v-btn @click="pretrazi"> Pretraži </v-btn>
    </v-card-actions>
  </v-card>

<v-dialog
    v-model="profilApotekeDialog"
    persistent
    scrollable
      max-width="180vh">
      <v-card>
        <v-card-title class="headline">
          
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="gray"
            text
            @click="endProfilApotekeDialog"
          >
            Povratak
          </v-btn>
        </v-card-actions>
        <ProfilApoteke :apotekaId="this.apotekaId" :registrovan="false" :key="this.componentKey"/>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="white"
            text
            @click="endProfilApotekeDialog"
          >
            Povratak
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</div>
</template>
<script>
import axios from "axios";
import ProfilApoteke from "./ProfilApoteke";

export default {
  name: "PretragaApoteka",

  components: {
     ProfilApoteke,
  },

  data: () => ({
    profilApotekeDialog:false,
    apotekaId: "",
    selektovanaApoteka: null,
    componentKey: 0,
    headeri: [
      { text: "Naziv", value: "naziv", sortable: false },
      { text: "Adresa", value: "adresa", sortable: false },
      { text: "Grad", value: "grad", sortable: false },
      { text: "Država", value: "drzava", sortable: false },
      { text: "Ocena", value: "ocena", sortable: false },
      { text: "Udaljenost", value: "udaljenost", sortable: false },
      { text: "Profil apoteke", value: "profil", sortable: false },
    ],
    apoteke: [],
    pretragaParams: {
      naziv: "",
      grad: "",
    },
    ocena2: true,
    ocena24: true,
    ocena4: true,
    udaljenost5: true,
    udaljenost510: true,
    udaljenost10: true,
    
  }),

  async mounted() {
    this.loadApoteke();
  },

  computed: {
    filtriraneApoteke() {
      var filter1 = [];
      var filter2 = [];

      if (this.ocena2)
        filter1 = filter1.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.ocena) < 2));
      if (this.ocena24)
        filter1 = filter1.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.ocena) >= 2 && parseFloat(apoteka.ocena) < 4));
      if (this.ocena4)
        filter1 = filter1.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.ocena) >= 4));
      if (this.udaljenost5)
        filter2 = filter2.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.udaljenost) < 500));
      if (this.udaljenost510)
        filter2 = filter2.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.udaljenost) >= 500 && parseFloat(apoteka.udaljenost) < 1000));
      if (this.udaljenost10)
        filter2 = filter2.concat(this.apoteke.filter(apoteka => parseFloat(apoteka.udaljenost) >= 1000));

      return filter1.filter(value => filter2.includes(value));
    }
  },

  methods: {
    async loadApoteke() {
      const apoteke = [];
      await axios.get(`/apoteke/sve/`).then((response) => {
        response.data.forEach((element) => {
          apoteke.push({
            id: element.id,
            naziv: element.naziv,
            adresa: element.adresa,
            grad: element.grad,
            drzava: element.drzava,
            ocena: element.id,
            udaljenost: 500,
          });
          this.apoteke = apoteke;
        });
      });
    },

    pretrazi() {
      axios
        .post(`/apoteke/pretraga`, this.pretragaParams)
        .then((response) => {
          this.apoteke = response.data;
          this.apoteke.forEach(element => {
            element.ocena = element.id;
            element.udaljenost = 500;
          });
        });
    },

    profilApoteke(apoteka){
      console.log(apoteka);
      this.componentKey += 1;
      this.selektovanaApoteka=Object.assign({}, apoteka);
      this.apotekaId = this.selektovanaApoteka.id;

      this.profilApotekeDialog = true;
    },
    endProfilApotekeDialog(){
      this.profilApotekeDialog = false;
    },
  },
};
</script>

<style>
</style>