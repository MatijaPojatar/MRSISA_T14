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
        Održani pregledi - ovaj mesec
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
          padding="24"
          stroke-linecap="round"
          smooth
        >
          
        </v-sparkline>
      </v-sheet>
    </v-card-text>

    <v-card-text>
      <div class="display-1 font-weight-thin">
        Održani pregledi - kvartal
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
          padding="24"
          stroke-linecap="round"
          smooth
        >
          
        </v-sparkline>
      </v-sheet>
    </v-card-text>

    <v-card-text>
      <div class="display-1 font-weight-thin">
        Održani pregledi - godina
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
      labelsKvartal: [
        "pretprošli mesec",
        "prošli mesec",
        "trenutni mesec",
      ],
      labelsGodina:[
        "JAN",
        "FEB",
        "MAR",
        "APR",
        "MAJ",
        "JUN",
        "JUL",
        "AVG",
        "SEP",
        "OKT",
        "NOV",
        "DEC",
      ]
    }),
    props :{
        apotekaId: Number,
    },
    mounted(){
        Vue.axios.get(`http://localhost:8080/izvestaj/pregledi/mesec/${this.apotekaId}`).then(response => {
            this.mesecIzvestaj = response.data;
        });
        Vue.axios.get(`http://localhost:8080/izvestaj/pregledi/kvartal/${this.apotekaId}`).then(response => {
            this.kvartalIzvestaj = response.data;
        });
        Vue.axios.get(`http://localhost:8080/izvestaj/pregledi/godina/${this.apotekaId}`).then(response => {
            this.godinaIzvestaj = response.data;
        });
    },
    methods:{
        
    }
  }
</script>