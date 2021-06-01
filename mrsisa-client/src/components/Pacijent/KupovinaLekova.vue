<template>
<div>
    <div v-if="upload">
      <v-card>
        <v-card-title>Izaberite QR kod sa E-receptom:</v-card-title>

        <v-card-text>
          <v-file-input
            v-model="file"
            accept="image/*"
            label="File input"
            
          ></v-file-input>
        </v-card-text>
        
      <v-card-actions>
        <v-btn @click="submitFile()">Pošalji</v-btn>
      </v-card-actions>
      </v-card>
    
    
  </div>
  <div v-if="!upload">
    <v-card v-if="odbijen">
      <v-card-text>
        ERecept je odbijen!
        Mogući razlozi:
        -Pacijent je alergičan na neki od lekova
        -Ne postoje lekovi na ereceptu
      </v-card-text>
    </v-card>

    <v-card class="my-2">
      <v-card-title>Lekovi iz recepta:</v-card-title>
      
      <v-data-table
        :headers="headersLekovi"
        :items="this.erecept.lekoviErecepta"
        >
      </v-data-table>
    </v-card>
    

    <v-card
    class="my-2">
      <v-card-title>Snabdevene apoteke:</v-card-title>
      <v-card-text
      v-if="!postojeApoteke"
      >Ne postoje apoteke koje nude lekove sa recepta</v-card-text>
      <v-data-table
        v-if="postojeApoteke"
        :headers="headersApoteke"
        :items="this.snabdeveneApoteke">
      <template v-slot:item.actions="{ item }">
        <v-btn
          @click="odaberiApoteku(item.idApoteke)"
          class ="mx-2"
          fab
          small
          dark
          color="indigo">
          Kupi
        </v-btn>
      </template>
    </v-data-table>
    </v-card>
    
  </div>
</div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex';

export default {
  

   computed: {
    ...mapGetters({
      user: "korisnici/getKorisnik",
      erecept: "erecepti/getCurrentErecept",
      snabdeveneApoteke: "erecepti/getSnabdeveneApoteke"
    }),
    odbijen() {
      return this.erecept.status == "ODBIJEN";
    },
    postojeApoteke() {
      return this.snabdeveneApoteke.length != 0;
    }
  },

  data: () => ({
    file: null,
    upload: true,
    headersLekovi: [
      { text: 'Naziv leka', value: 'nazivLeka'},
      { text: 'Količina', value: 'kolicina'},
      ],
    headersApoteke: [
      { text: 'Apoteka', value: 'nazivApoteke'},
      { text: 'Cena', value: 'cenaLekova'},
      { text: 'Odaberi', value: 'actions', sortable: false},
    ]
  }),

  methods: {
    ...mapActions({
    sendFileAction: "erecepti/sendFileAction",
    getSnabdeveneApotekeAction: "erecepti/getSnabdeveneApotekeAction"
  }),

  async submitFile(){
    let formData = new FormData();
    formData.append('file', this.file);
    try{
      await this.sendFileAction({formData: formData, id: this.user.id} );
      this.upload = false;
    }catch(error){
      console.log(error)
      alert("Greska pri upload-u fajla")
    }
    try{
      await this.getSnabdeveneApotekeAction(this.erecept);
    }catch(error){
      console.log(error);
      alert("Greska pri dobavljanju snabdevenih apoteka")
    }
    
  },

  async odaberiApoteku(idApoteke){
    //posalji axios da kupiss
    console.log(idApoteke);
    alert("Sad treba zvati kupovinu")
  }

  }
}
</script>

<style>

</style>