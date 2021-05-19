<template>
  <v-container>
    <v-row>
      <v-card center width="600">
      <v-card-title> Uspešna aktivacija profila!</v-card-title>

      <h4>Tip korisnika: Pacijent</h4>

      <v-card-actions>
        <v-btn @click="pocetna">Početna strana</v-btn>
      </v-card-actions>
    </v-card>

    </v-row>
  
  </v-container>
 
</template>

<script>
import {mapActions} from 'vuex'

export default {
  methods: {
    ...mapActions({
      aktivacijaProfilaAction: "korisnici/aktivacijaProfilaAction"
    }),

    pocetna() {
      this.$router.push("/");
    }
  },

  async mounted(){
    try{
      this.aktivacijaProfilaAction(this.$route.params.id)
    }catch(error){
      alert("Greska pri aktivaciji")
    }
  }
}
</script>

<style>
 #app {
    background: url("https://images.unsplash.com/photo-1579165466741-7f35e4755660?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
    background-size: cover;
  }
</style>