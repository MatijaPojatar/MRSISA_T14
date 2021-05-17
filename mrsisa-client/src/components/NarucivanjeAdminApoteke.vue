<template>
    <div justify="space-around" align="center">
    <br/>
    <div>
    <v-btn
        class="mx-2"
        
        @click="viewUpiti"
    >
        Upiti
    </v-btn>

     <v-btn
        class="mx-2"
        @click="viewNarudzbenice"
    >
        Narudžbenice
    </v-btn>

     <v-btn
        class="mx-2"
        color="light-green"
        @click="viewDodajNarudzbenicu"
    > 
        <v-icon>
            mdi-plus
        </v-icon>
        Nova narudžbenica 
    </v-btn>
    </div>
    <br/>
    <br/>

    
    
    <v-container fluid v-if="showUpiti">
        <TableUpiti :apotekaId="apotekaId"/>
      </v-container>

    <v-container fluid v-if="showNarudzbenice">
        <TableNarudzbenice :apotekaId="apotekaId"/>
      </v-container>

   

    <v-dialog
      v-model="obavestenjeDialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>Nije pronađen farmaceut sa zadatim parametrima.</v-card-text>
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
import TableUpiti from "./TableUpiti";
import TableNarudzbenice from "./TableNarudzbenice";


export default{
    name: "NarucivanjeAdminApoteke",
    components: {
        TableUpiti,
        TableNarudzbenice,
    },
    data: () => ({
        
        obavestenjeDialog: false,
        showNarudzbenice:false,
        showUpiti:true,
        showNovaNarudzbenica:false,
    }),
    props :{
        user: {},
        apotekaId: Number,
    },

    methods:{
        endObavestenjeDialog(){
            this.obavestenjeDialog = false;
        },
        viewUpiti(){
            this.showUpiti = true;
            this.showNarudzbenice = false;
            this.showNovaNarudzbenica = false;
        },
        viewNarudzbenice(){
            this.showUpiti = false;
            this.showNarudzbenice = true;
            this.showNovaNarudzbenica = false;
        },
        viewDodajNarudzbenicu(){
            this.showUpiti = false;
            this.showNarudzbenice = false;
            this.showNovaNarudzbenica = true;
        }
        
        
    },
}
</script>

<style>

</style>