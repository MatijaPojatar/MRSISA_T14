<template>
  <div>
    <v-card class="my-2">
      <v-card-title>Bodovanje pregleda i savetovanja</v-card-title>
      <!-- <v-row>
        <v-col> -->
          <v-text-field
          type="number"
          :readonly="!izmenaPS"
          label="Bodovanje pregleda"
          v-model="bodPregled"
          />
        <!-- </v-col>
        <v-col> -->
              
          <v-text-field
          type="number"
          :readonly="!izmenaPS"
          label="Bodovanje savetovanja"
          v-model="bodSavetovanja"
          />
        <!-- </v-col>
        <v-col> -->
          <v-card-actions>
            <v-spacer></v-spacer>
             <v-btn
          color="blue"
          dark
          v-if="izmenaPS"
          @click="posaljiIzmenuPS"
          >Sačuvaj</v-btn>

          <v-btn
          color="blue"
          dark
          v-if="!izmenaPS"
          @click="prebaciNaIzmenuPS"
          >Izmeni</v-btn>
          </v-card-actions>
         
        <!-- </v-col>
      </v-row> -->
      

    </v-card>

    <v-card class="my-2">
      <v-card-title>Kategorije</v-card-title>
      <v-data-table
      :headers="headers"
      :items="kategorije"
      sort-by="brojPoenaStart"
      >
      <template v-slot:item.actions="{ item }">
      <v-btn
      class ="mx-2"
      fab
      small
      dark
      color="indigo">
        <v-icon
        small
        @click="obrisiKat(item)"
      >
        mdi-delete
      </v-icon>
      </v-btn>
      
    </template>
      
      </v-data-table>

    </v-card>

     <v-card>
      <v-card-title>Kreiranje kategorije:</v-card-title>

      <v-text-field
      required
      label="Naziv"
      v-model="naziv"
      ></v-text-field>

      <v-text-field
      required
      type="number"
      label="Potreban broj poena"
      v-model="brPoena"
      ></v-text-field>

      <v-text-field
      required
      label="Procenat popusta"
      v-model="procenat"
      >
      </v-text-field>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
        color="blue"
        dark
        @click="dodajKat"
        >Dodaj</v-btn>
      </v-card-actions>

    </v-card>

  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {

  data: () => ({
    izmenaPS: false,



    brPoena: 0,
    naziv: "",
    procenat: 0.0,
    headers: [
      { text: 'Naziv', value: 'naziv'},
      { text: 'Broj poena', value: 'brojPoenaStart'},
      { text: '% Popusta', value: 'procenat'},
      { text: 'Brisanje', value: 'actions', sortable: false},
    ]

  }),

  computed: {
    ...mapGetters({
      kategorije: "loyalty/getAllKat",
      bodPregled: "loyalty/getBodPregled",
      bodSavetovanja: "loyalty/getBodSavetovanja",
    })
  },

  async mounted(){
    await this.getAllKatAction();
    await this.getBodPregledAction();
    await this.getBodSavetovanjaAction();
  },

  methods: {
    ...mapActions({
      addKatAction: "loyalty/addKatAction",
      getAllKatAction: "loyalty/getAllKatAction",
      delKatAction: "loyalty/delKatAction",
      getBodSavetovanjaAction: "loyalty/getBodSavetovanjaAction",
      getBodPregledAction: "loyalty/getBodPregledAction",
      addStavkaAction: "loyalty/addStavkaAction"
    }),

    prebaciNaIzmenuPS(){
      this.izmenaPS = true;
    },

    async posaljiIzmenuPS(){
      //dva axios poziva
      try{
        let dto1 ={
        brojPoena: this.bodPregled,
        tip: 2
      }
      await this.addStavkaAction(dto1);

      let dto2 ={
        brojPoena: this.bodSavetovanja,
        tip: 1
      }
      await this.addStavkaAction(dto2);

      await this.getBodSavetovanjaAction();
      await this.getBodPregledAction();

      }catch(err){
        console.log(err)
      }
      
      this.izmenaPS = false;
    },


    async obrisiKat(kat){
      await this.delKatAction(kat.id);
      alert("Uspešno brisanje")
      await this.getAllKatAction();
    },

    async dodajKat(){
      let dto = {
        brojPoenaStart: this.brPoena,
        naziv: this.naziv,
        procenat: this.procenat
      }

      try{
        await this.addKatAction(dto);
        await this.getAllKatAction();
        alert("Uspešno dodavanje")
        this.brojPoenaStart = 0;
        this.naziv = "";
        this.procenat = 0;
      }catch(err){
        console.log(err);
        alert("Neuspešno dodavanje")
      }
    }
  }
}
</script>

<style>

</style>