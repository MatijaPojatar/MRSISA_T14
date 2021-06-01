<template>
<div>
  <v-card
    class="mx-auto text-center"
    color="light-blue"
    dark
    max-width="600"
  >
    <v-card-text>
      <v-sheet color="rgba(0, 0, 0, .12)">
        <v-sparkline
          :value="mesecIzvestaj"
          color="rgba(255, 255, 255, .7)"
          height="100"
          padding="24"
          stroke-linecap="round"
          smooth
        >
          <template v-slot:label="item">
            {{ item.value }}
          </template>
        </v-sparkline>
      </v-sheet>
    </v-card-text>

    
    <v-card-text>
      <div class="display-1 font-weight-thin">
        Potrošnja lekova - ovaj mesec
      </div>
    </v-card-text>

    <v-divider></v-divider>

  </v-card>
  <br/>

   <v-card
    class="mx-auto text-center"
    color="light-blue"
    dark
    max-width="600"
  >
    <v-card-text>
      <v-sheet color="rgba(0, 0, 0, .12)">
        <v-sparkline
          :value="kvartalIzvestaj"
          color="rgba(255, 255, 255, .7)"
          height="100"
          padding="5"
          stroke-linecap="round"
          smooth
        >
          
        </v-sparkline>
      </v-sheet>
    </v-card-text>

    <v-card-text>
      <div class="mx-2" justify="space-around" align="center">
        <v-row justify="space-around" align="center">
        <label>Pretprošli mesec</label>
        <label>Prošli mesec</label>
        <label>Trenutni mesec</label>
        </v-row>
      </div>
    </v-card-text>

    <v-card-text>
      <div class="display-1 font-weight-thin">
        Potrošnja lekova - kvartal
      </div>
    </v-card-text>

    <v-divider></v-divider>

  </v-card>

  <br/>

   <v-card
    class="mx-auto text-center"
    color="light-blue"
    dark
    max-width="600"
  >
    <v-card-text>
      <v-sheet color="rgba(0, 0, 0, .12)">
        <v-sparkline
          :value="godinaIzvestaj"
          color="rgba(255, 255, 255, .7)"
          height="100"
          padding="5"
          stroke-linecap="round"
          smooth
        >
          
        </v-sparkline>
      </v-sheet>
    </v-card-text>

    <v-card-text>
      <div class="mx-2" justify="space-around" align="center">
        <v-row justify="space-around" align="center">
        <label>JAN</label>
        <label>FEB</label>
        <label>MAR</label>
        <label>APR</label>
        <label>MAJ</label>
        <label>JUN</label>
        <label>JUL</label>
        <label>AVG</label>
        <label>SEP</label>
        <label>OKT</label>
        <label>NOV</label>
        <label>DEC</label>
        
        </v-row>
      </div>
    </v-card-text>

    <v-card-text>
      <div class="display-1 font-weight-thin">
        Potrošnja lekova - godina
      </div>
    </v-card-text>

    <v-divider></v-divider>

  </v-card>

</div>
</template>

<script>
import Vue from "vue";
  export default {
    data: () => ({
      mesecIzvestaj: [],
      kvartalIzvestaj: [],
      godinaIzvestaj: [],
      
    }),
    props :{
        apotekaId: Number,
    },
    mounted(){
        Vue.axios.get(`http://localhost:8080/izvestaj/prihodi/mesec/${this.apotekaId}`).then(response => {
            this.mesecIzvestaj = response.data;
        });
        Vue.axios.get(`http://localhost:8080/izvestaj/prihodi/kvartal/${this.apotekaId}`).then(response => {
            this.kvartalIzvestaj = response.data;
        });
        Vue.axios.get(`http://localhost:8080/izvestaj/prihodi/godina/${this.apotekaId}`).then(response => {
            this.godinaIzvestaj = response.data;
        });
    },
    methods:{
        
    }
  }
</script>