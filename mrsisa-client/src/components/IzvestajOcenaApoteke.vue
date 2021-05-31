<template>
    <div justify="space-around" align="center">
    <br/>
    <br/>
    <v-card class="mx-2">
    <br/>
    <v-rating
        v-model = "apoteka.ocena"
        half-increments
        hover
        readonly
        length="5"
    ></v-rating>
    Ocena: {{apoteka.ocena}} <br/>
    
    <br/>
    </v-card>
      
   

    <v-dialog
      v-model="obavestenjeDialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>{{message}}</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endObavestenjeDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>

  
    </div>
    
</template>

<script>
import Vue from "vue";


export default{
    components: {
    },
    data: () => ({
        
        obavestenjeDialog: false,
        message:"",
        apoteka: {
          naziv: "",
          opis: "",
          adresa: "",
          grad: "",
          drzava: "",
          ocena: "",
      },
    }),
    props :{
        apotekaId: Number,
    },
    mounted(){
        Vue.axios.get(`http://localhost:8080/apoteke/getOne/${this.apotekaId}`).then(response => {
            this.apoteka = response.data
        });
    },

    methods:{
        endObavestenjeDialog(){
            this.obavestenjeDialog = false;
        },
        

        

        
        
        
    },
}
</script>

<style>

</style>