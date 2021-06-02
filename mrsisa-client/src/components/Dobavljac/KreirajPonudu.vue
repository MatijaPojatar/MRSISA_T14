<template>
  <div>
    <v-card
    v-if="narudzbenice.length == 0"
    >
    <v-card-title>Nema aktivnih narudžbenica</v-card-title>
    </v-card>


    <v-expansion-panels v-else>
      <v-expansion-panel
        v-for="narudzbenica in narudzbenice"
        :key="narudzbenica.id"
        @click="selekcija(narudzbenica)"
      >
        <v-expansion-panel-header>
          Narudžbenica ID: {{ narudzbenica.id }} Apoteka: {{ narudzbenica.nazivApoteke }}
        </v-expansion-panel-header>

        <v-expansion-panel-content>


          <div v-for="lekNar in narudzbenica.lekovi" :key="lekNar.lekId">
            <div>Lek: {{ lekNar.naziv }}</div>
            <div>Količina: {{ lekNar.kolicina }}</div>
          </div>
          <v-divider />

          <v-text-field dense outlined required v-model="cena" label="Cena">
          </v-text-field>

          <div>Odredite datum roka isporuke:</div>

          <v-date-picker v-model="rokDatum"></v-date-picker>

          <div>Odredite vreme roka isporuke:</div>
          <v-time-picker
            v-model="rokVreme"
            ampm-in-title
            format="ampm"
            landscape
          ></v-time-picker>

          <v-btn right dark color="blue" @click="pokusajPoslati(narudzbenica.id)">
            Pošalji ponudu
          </v-btn>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  data: () => ({
    izmena: false,
    rokDatum: "2021-06-01",
    rokVreme: "10:10",
    cena: "",
    selektovanaNarudzbenica: {},
  }),

  mounted() {
    this.getMoguceNarudzbeniceAction(this.user.id);
  },

  computed: {
    ...mapGetters({
      narudzbenice: "narudzbenice/getMoguceNarudzbenice",
      user: "korisnici/getKorisnik",
      ponudaMoguca: "ponude/getNarudzbenicaMoguca",
    }),
  },

  methods: {
    ...mapActions({
      getMoguceNarudzbeniceAction: "narudzbenice/getMoguceNarudzbeniceAction",
      getMoguceKreiratiPonuduAction: "ponude/getMoguceKreiratiPonuduAction",
      kreirajPonuduAction: "ponude/kreirajPonuduAction",
    }),

    async pokusajPoslati(narId) {
      //provera da li moze
      await this.getMoguceKreiratiPonuduAction({idDob: this.user.id, idNar: narId});
      if(this.ponudaMoguca){
        let dto = {
          cena: this.cena,
          dobavljacId: this.user.id,
          narudzbenicaId: narId,
          rokVreme: this.rokVreme,
          rokDatum: this.rokDatum,
          rokIsporuke: this.rokDatum,  //+ " " + this.rokVreme,
          rokStr: this.rokDatum + " " + this.rokVreme,
          status: "OBRADA",
          // nazivDobavljaca: 
        }

        await this.kreirajPonuduAction(dto);
      }else{
        alert("Dobavljac nema dovoljno lekova na stanju");
      }
    },

    selekcija(narudzbenica) {
      this.selektovanaNarudzbenica = narudzbenica;
    },
  },
};
</script>

<style>
</style>