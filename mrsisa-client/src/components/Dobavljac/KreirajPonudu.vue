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

          <v-text-field 
          type="number"
          dense outlined required v-model="cena" label="Cena">
          </v-text-field>

          <div>Odredite datum roka isporuke:</div>

          <v-date-picker 
          v-model="rokDatum"
          :min="new Date().toISOString().substr(0, 10)"
          ></v-date-picker>

          <div>Odredite vreme roka isporuke:</div>
          <v-time-picker
            v-model="rokVreme"
            ampm-in-title
            format="ampm"
            landscape
          ></v-time-picker>
          <v-row>
            <v-spacer></v-spacer>
          <v-btn right dark color="blue" @click="pokusajPoslati(narudzbenica.id)">
            Pošalji ponudu
          </v-btn>
          </v-row>
          
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>

     <v-dialog
      v-model="potvrda"
      persistent
      max-width="300"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>Uspešno ste poslali ponudu.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endPotvrda"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  data: () => ({
    potvrda: false,
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
    endPotvrda(){
      this.potvrda = false;
    },

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
          rokStr: this.rokDatum + " " + this.rokVreme,
          status: "OBRADA",
         
        }

        await this.kreirajPonuduAction(dto);

        this.potvrda = true;
        this.getMoguceNarudzbeniceAction(this.user.id);
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