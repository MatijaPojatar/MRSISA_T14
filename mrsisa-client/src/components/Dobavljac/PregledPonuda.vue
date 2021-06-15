<template>
<div>
  <v-row>
    <v-card width="600">
      <v-row align="center" justify="space-around">
        <v-col align="center"> Status ponude: </v-col>
        <v-col>
          <v-checkbox v-model="prihvacena" label="Prihvaćena" />
        </v-col>
        <v-col>
          <v-checkbox v-model="odbijena" label="Odbijena" />
        </v-col>
        <v-col>
          <v-checkbox v-model="cekaOdg" label="Čeka na odgovor" />
        </v-col>
      </v-row>
      </v-card>


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

            <div v-else>Rok isporuke: {{ponuda.rokIsporuke | moment("dddd, MMMM Do YYYY, h:mm a")}}</div>

            <div> Status ponude:  {{ponuda.status}}</div>
            <br/>
              <v-btn
              color="blue"
              @click="prikaziNar(ponuda.narudzbenicaId)"
              >
                Pogledaj narudžbenicu
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn
              v-if="!izmena"
              dark
              color="blue"
              @click="izmenaa()"
              >
                Izmeni
              </v-btn>

              <v-btn
              v-if="izmena"
              dark
              color="blue"
              @click="uspeh()"
              >
                Sačuvaj izmene
              </v-btn>

        </v-expansion-panel-content>


        </v-expansion-panel>
      </v-expansion-panels>
      
    </v-row>
</div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  data: () => ({
    prihvacena: true,
    odbijena: true,
    cekaOdg: true,

    izmena: false,
    selektovanaPonuda: {},
    cena: 0.0,
    rokDatum: "2021-06-01",
    rokVreme: "10:10",
  }),
  mounted () {
    this.getPonudeDobavljacaAction(this.user.id);
  },

  computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik",
      ponudeRaw: "ponude/getPonudeDobavljaca",
      izmenaMoguca: "ponude/getIzmenaMoguca",
    }),

    ponude(){
      var filter = [];

      if(this.prihvacena){
        filter = filter.concat(this.ponudeRaw.filter(ponuda => ponuda.status == "PRIHVACENA"));
      }
      if(this.odbijena){
        filter = filter.concat(this.ponudeRaw.filter(ponuda => ponuda.status==="ODBIJENA"));
      }
      if(this.cekaOdg){
        filter = filter.concat(this.ponudeRaw.filter(ponuda => ponuda.status == "OBRADA"));
      }

      return filter;
    },
  },

  methods: {
    ...mapActions({
      getPonudeDobavljacaAction: "ponude/getPonudeDobavljacaAction",
      izmeniPonuduAction: "ponude/izmeniPonuduAction",
      getIzmenaMogucaAction: "ponuda/getIzmenaMoguca"
    }),

    

    selekcija(ponuda){
      this.selektovanaPonuda = ponuda;
    },

    izmenaa(){
      this.izmena = true;
    },

    async uspeh(){
      alert("Uspeh");
    },

    async prikaziNar(id){
      alert("Pozoviii"+id)
    },

    async pokusajIzmenu(ponuda){
      this.izmena= true;
      console.log(ponuda);
      // await this.getIzmenaMogucaAction(ponuda.id);
      // if(this.izmenaMoguca){
      //   this.izmena = true;
      // }else{
      //   alert("Izmena nemoguca zbog isteka roka")
      // }
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