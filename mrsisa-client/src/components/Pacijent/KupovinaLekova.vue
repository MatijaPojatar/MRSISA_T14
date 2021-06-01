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
    <v-data-table
    :headers="headersLekovi"
    :items="erecept.lekoviErecepta"
    >
    </v-data-table>
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
    })
  },

  data: () => ({
    file: null,
    upload: true,
    headersLekovi: [
      { text: 'Naziv leka', value: 'nazivLeka'},
      { text: 'Količina', value: 'kolicina'},
      ]
  }),

  methods: {
    ...mapActions({
    sendFileAction: "erecepti/sendFileAction",
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
      
    }
  }
}
</script>

<style>

</style>