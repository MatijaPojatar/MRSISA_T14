<template>
<div>
  <v-expansion-panels>
      <v-expansion-panel
      v-for="narudzbenica in narudzbenice"
      :key="narudzbenica.id"
      @click="selekcija(narudzbenica)">

      <v-expansion-panel-header>
        ID: {{narudzbenica.id}}   Apoteka: {{narudzbenica.nazivApoteke}}
      </v-expansion-panel-header>

      <v-expansion-panel-content>
          <div>
            Lek              Količina
          </div>
          <v-divider/>

          <div v-for="lekNar in narudzbenica.lekovi" :key="lekNar.lekId">
            {{lekNar.naziv}}      {{lekNar.kolicina}}
          </div>
          <v-divider/>

          <v-textarea
          outlined
          required
          v-model="cena"
          label="Cena">
          </v-textarea>

          <!-- <v-row justify="center"> -->
            <v-date-picker 
            v-model="rokDatum"
            ></v-date-picker>
          <!-- </v-row> -->

          <v-time-picker
            v-model="rokVreme"
            ampm-in-title
            format="ampm"
            landscape
          ></v-time-picker>

          <v-btn
          dark
          color="green"
          @click="pokusajPoslati(narudzbenica.id)"
          >
            Pošalji ponudu
          </v-btn>

        </v-expansion-panel-content>

      </v-expansion-panel>
    </v-expansion-panels>

</div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex';

export default {

  data() {
    return {
      rokDatum: '2021-06-01',
      rokVreme: '10:10',
      cena: "",
      minimumDatum: new Date(), //?
      selektovanaNarudzbenica : {}

    };
  },

  mounted() {
    this.getMoguceNarudzbeniceAction(this.user.id);
  },

  computed: {
    ...mapGetters({
      narudzbenice: "narudzbenice/getMoguceNarudzbenice",
      user: "korisnici/getKorisnik",
    }),
    maximumDatum() {
      return this.selektovanaNarudzbenica.rok;
    }
  },

  methods: {
    ...mapActions({
      getMoguceNarudzbeniceAction: "narudzbenice/getMoguceNarudzbeniceAction",
    }),

    pokusajPoslati() {
      alert("Ponudu sad kao saljemo");
    },

    selekcija(narudzbenica){
      this.selektovanaNarudzbenica = narudzbenica;
    }
  },
};
</script>

<style>
</style>