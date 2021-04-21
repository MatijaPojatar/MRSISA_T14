<template>
    <div>
    <div>
    <v-btn
        class="mx-2"
        fab
        dark
        small
        color="light-green"
        @click="DodajFarmaceuta"
    >
        <v-icon>
            mdi-plus
        </v-icon>
    </v-btn>
    </div>
    <br/>
    <br/>
    <ListFarmaceuti :apotekaId = "apotekaId" :adminView = "true"/>

    <v-dialog
    v-model="farmaceutAddDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Novi farmaceut
        </v-card-title>
        <AddZaposleniForm :apotekaId="this.apotekaId" :farmaceut="true"/>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endDialog"
          >
            Povratak
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    </div>
    
</template>

<script>
import ListFarmaceuti from "./ListFarmaceuti";
import AddZaposleniForm from "./AddZaposleniForm"

export default{
    name: "FarmaceutiAdminApoteke",
    components: {
        ListFarmaceuti,
        AddZaposleniForm
    },
    data: () => ({
        farmaceutAddDialog: false,
    }),
    props :{
        user: {},
        apotekaId: Number,
    },

    methods:{
         DodajFarmaceuta(){
                this.farmaceutAddDialog = true;
             },
        endDialog(){
                this.farmaceutAddDialog = false;
             },
        
    },
}
</script>

<style>

</style>