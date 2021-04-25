<template>
    <div>
    <div>
    <v-btn
        class="mx-2"
        fab
        dark
        small
        color="light-green"
        @click="DodajDermatologa"
    >
        <v-icon>
            mdi-plus
        </v-icon>
    </v-btn>
    </div>
    <br/>
    <br/>
    <ListDermatolozi :apotekaId = "apotekaId" :adminView = "true"/>

    <v-dialog
    v-model="dermatologAddDialog"
    persistent
    scrollable
      max-width="760">
      <v-card>
        <v-card-title class="headline">
          Novi dermatolog
        </v-card-title>
        <AddZaposleniForm :apotekaId="this.apotekaId" :farmaceut="false"/>
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
import ListDermatolozi from "./ListDermatolozi";
import AddZaposleniForm from "./AddZaposleniForm"

export default{
    name: "DermatoloziAdminApoteke",
    components: {
        ListDermatolozi,
        AddZaposleniForm
    },
    data: () => ({
        dermatologAddDialog: false,
    }),
    props :{
        user: {},
        apotekaId: Number,
    },

    methods:{
         DodajDermatologa(){
                this.dermatologAddDialog = true;
             },
        endDialog(){
                this.dermatologAddDialog = false;
             },
        
    },
}
</script>

<style>

</style>