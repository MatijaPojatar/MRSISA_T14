

<template>
    <div>
    <div>
    <v-btn
        class="mx-2"
        fab
        dark
        small
        color="light-green"
        @click="DodajLek"
    >
        <v-icon>
            mdi-plus
        </v-icon>
    </v-btn>
    </div>
    <br/>
    <br/>
    <ListLekoviV2 :apotekaId = "apotekaId" :adminView = "true"/>

    <v-dialog
    v-model="lekAddDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Novi lek
        </v-card-title>
        <ListLekoviToAdd :apotekaId="this.apotekaId"/>
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
import ListLekoviV2 from "./ListLekoviV2";
import ListLekoviToAdd from "./ListLekoviToAdd";

export default{
    name: "LekoviAdminApoteke",
    components: {
        ListLekoviV2,
        ListLekoviToAdd
    },
    data: () => ({
        lekAddDialog: false,
    }),
    props :{
        user: {},
        apotekaId: Number,
    },

    methods:{
         DodajLek(){
                this.lekAddDialog = true;
             },
        endDialog(){
                this.lekAddDialog = false;
             },
        
    },
}
</script>

<style>

</style>