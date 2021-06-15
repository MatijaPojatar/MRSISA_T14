<template>
  <v-card>
    <v-card-title>Ocenjivanje</v-card-title>
    <v-expansion-panels>
      <v-expansion-panel
        v-for="dermatolog in moguciDermatolozi"
        :key="dermatolog.id"
      >
        <v-expansion-panel-header>
          Dermatolog: {{ dermatolog.ime + " " + dermatolog.prezime }}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-rating
            v-model="ocena"
            length="5"
            size="30"/>
          <v-btn class="mr-4" @click="oceniDermatologa(dermatolog.id)">Sačuvaj</v-btn>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
    <v-expansion-panels>
      <v-expansion-panel
        v-for="farmaceut in moguciFarmaceuti"
        :key="farmaceut.id"
      >
        <v-expansion-panel-header>
          Farmaceut: {{ farmaceut.ime + " " + farmaceut.prezime }}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-rating
            v-model="ocena"
            length="5"
            size="30"/>
          <v-btn class="mr-4" @click="oceniFarmaceuta(farmaceut.id)">Sačuvaj</v-btn>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
    <v-expansion-panels>
      <v-expansion-panel
        v-for="apoteka in moguceApoteke"
        :key="apoteka.id"
      >
        <v-expansion-panel-header>
          Apoteka: {{ apoteka.naziv }}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-rating
            v-model="ocena"
            length="5"
            size="30"/>
          <v-btn class="mr-4" @click="oceniApoteku(apoteka.id)">Sačuvaj</v-btn>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-card>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import Vue from "vuex";

export default {
  name: "Ocenjivanje",

  data: () => ({
    ocena: 0,
  }),

  computed: {
    ...mapGetters({
      korisnik: "korisnici/getKorisnik",
      moguciDermatolozi: "zalbe/getMoguciDermatolozi",
      moguciFarmaceuti : "zalbe/getMoguciFarmaceuti",
      moguceApoteke : "zalbe/getMoguceApoteke"
    }),
  },

  mounted(){
    this.removeDuplicates(this.getMoguceApotekeAction);
  },

  methods: {
    ...mapActions({
      getMoguceDermatologeAction: "zalbe/getMoguceDermatologeAction",
      getMoguceFarmaceuteAction: "zalbe/getMoguceFarmaceuteAction",
      getMoguceApotekeAction: "zalbe/getMoguceApotekeAction"
    }),

    oceniDermatologa(id){
      Vue.axios.post(`/dermatolog/oceniDermatologa/${id}`, );
    },
/*
    oceniFarmaceuta(id){

    },

    oceniApoteku(id){

    }*/
  },
};
</script>

<style>
</style>