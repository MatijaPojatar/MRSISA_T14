<template>
  <v-row>
    <v-expansion-panels>
      <v-expansion-panel
      v-for="ponuda in ponude"
      :key="ponuda.id"
      @click="selekcija(ponuda)">

      <v-expansion-panel-header>
        Ponuda za narudžbenicu: {{ponuda.narudzbenicaId}}
      </v-expansion-panel-header>

      <v-expansion-panel-content>

        <v-text-field
          v-if="izmena"
          outlined
          v-model="cena"
          label="Cena">
          </v-text-field>

           <v-text-field
           v-else
          readonly
          outlined
          v-model="ponuda.cena"
          label="Cena">
          </v-text-field>

          <div 
          v-if="izmena">
            <v-date-picker v-model="rokDatum"></v-date-picker>

          
            <v-time-picker
              v-model="rokVreme"
              ampm-in-title
              format="ampm"
              landscape
            ></v-time-picker>
          </div>

          <v-text-field
          v-else
          readonly
          outlined
          v-model="ponuda.rokIsporuke"
          label="Rok isporuke">
          </v-text-field>

          <br/>
            <v-btn
            v-if="!izmena"
            dark
            color="blue"
            @click="pokusajIzmenu(ponuda)"
            >
              Izmeni
            </v-btn>

            <v-btn
            v-if="izmena"
            dark
            color="blue"
            @click="posaljiIzmenu(ponuda)"
            >
              Sačuvaj izmene
            </v-btn>

      </v-expansion-panel-content>


      </v-expansion-panel>
    </v-expansion-panels>
  </v-row>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  data: () => ({
    izmena: false,
    selektovanaPonuda: {},
    cena: 0.0,
    rokDatum: "2021-06-01",
    rokVreme: "10:10",
  //ponude: [{id : 1, narudzbenicaId: 500}, {id: 2, narudzbenicaId: 505}]
  }),
  mounted () {
    this.getPonudeDobavljacaAction(this.user.id);
  },

  computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik",
      ponude: "ponude/getPonudeDobavljaca",
      izmenaMoguca: "ponude/getIzmenaMoguca",
    }),
  },

  mehods: {
    ...mapActions({
      getPonudeDobavljacaAction: "ponude/getPonudeDobavljacaAction",
      izmeniPonuduAction: "ponude/izmeniPonuduAction",
      getIzmenaMogucaAction: "ponuda/getIzmenaMoguca"
    }),

    selekcija(ponuda){
      this.selektovanaPonuda = ponuda;
    },

    async pokusajIzmenu(ponuda){
      await this.getIzmenaMogucaAction(ponuda.id);
      if(this.izmenaMoguca){
        this.izmena = true;
      }else{
        alert("Izmena nemoguca zbog isteka roka")
      }
    },

    async posaljiIzmenu(ponuda){
      let dto = {
        id: ponuda.id,
        dobavljacId: ponuda.dobavljacId,
        narudzbenicaId: ponuda.narudzbenicaId,
        status: ponuda.status,
        nazivDobavljaca: ponuda.nazivDobavljaca,

        cena: this.cena,
        rokIsporuke: this.rokDatum + " " + this.rokVreme, //UKOMBINOVATI DATUM I VREME
        rokStr: this.rokDatum,  //dd.MM.yyyy.
      }

      try {
        await this.izmeniPonuduAction({id: ponuda.id, dto});
        this.izmena = false;
      }catch(error){
        console.log(error);
      }
    
      
    }


  }


}
</script>

<style>

</style>