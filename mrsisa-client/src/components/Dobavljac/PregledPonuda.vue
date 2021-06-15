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

          <!-- <v-text-field
            v-if="izmena"
            outlined
            v-model="cena"
            label="Cena">
            </v-text-field> 

            v-else-->

            <v-text-field
            type="number"
            :readonly="!izmena"
            outlined
            v-model="ponuda.cena"
            label="Cena">
            </v-text-field>

            <div v-if="izmena">
              <v-date-picker 
              :readonly="!izmena"
              v-model="rokDatum"></v-date-picker>

            
              <v-time-picker
                v-model="rokVreme"
                :readonly="!izmena"
                ampm-in-title
                format="ampm"
                landscape
              ></v-time-picker>
            </div>

            <div v-else>Rok isporuke: {{ponuda.rokIsporuke | moment("dddd, MMMM Do YYYY, h:mm a")}}</div>

            <div> Status ponude:  {{ponuda.status}}</div>
            <br/>
              <v-btn
              class="mx-3"
              dark
              color="green"
              @click="prikaziNar(ponuda.narudzbenicaId)"
              >
                Pogledaj narudžbenicu
              </v-btn>
              
              <v-btn
              class="mx-3"
              v-if="!izmena"
              dark
              color="blue"
              @click="izmenaa(ponuda)"
              >
                Izmeni
              </v-btn>

              <v-btn
              class="mx-3"
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

    <v-dialog
       v-model="prikaziNarDialog"
      transition="dialog-top-transition"
      max-width="600">
        <v-card class="my-2">
        <v-card-title>Narudžbenica: {{currNarudzbenica.id}}</v-card-title>

        <v-divider></v-divider>
        <div v-for="lekNar in currNarudzbenica.lekovi" :key="lekNar.lekId">
            <div>Lek: {{ lekNar.naziv }}</div>
            <div>Količina: {{ lekNar.kolicina }}</div>
            <v-divider></v-divider>
        </div>

     
      </v-card>
      </v-dialog>
</div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import moment from 'moment'

export default {
  data: () => ({
    prihvacena: true,
    odbijena: true,
    cekaOdg: true,

    prikaziNarDialog: false,

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
      currNarudzbenica: "narudzbenice/getCurrNarudzbenica"
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
      getIzmenaMogucaAction: "ponude/getIzmenaMogucaAction",
      getCurrNarudzbenicaAction: "narudzbenice/getCurrNarudzbenicaAction"
    }),

    

    selekcija(ponuda){
      this.selektovanaPonuda = ponuda;
    },

// const format1 = "YYYY-MM-DD HH:mm:ss"
// const format2 = "YYYY-MM-DD"
// var date1 = new Date("2020-06-24 22:57:36");
// var date2 = new Date();

// dateTime1 = moment(date1).format(format1);
    async izmenaa(ponuda){
      await this.getIzmenaMogucaAction(ponuda.id);
      if(this.izmenaMoguca){
        
        const format = "YYYY-MM-DD";
        const formatV = "HH:mm"
        // this.rokDatum = ponuda.rokDatum | moment(format);
        // this.rokVreme = ponuda.rokVreme | moment(formatV);
        this.rokDatum = moment(ponuda.rokDatum).format(format);
        this.rokVreme = moment(ponuda.rokVreme).format(formatV);

        this.izmena = true;
      }else{
        alert("Izmena nemoguca zbog isteka roka")
      }
    },

    async uspeh(){
      // ovde saljes axios
      alert("Uspeh");
    },

    async prikaziNar(id){
      await this.getCurrNarudzbenicaAction(id);
      this.prikaziNarDialog = true;
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