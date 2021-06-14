<template>
<v-card>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >

    <v-text-field
      v-model="newInfo.cena"
      :counter="10"
      :rules="brojRules"
      label="Cena"
      required
    ></v-text-field>

    <v-btn v-if = "this.addMode"
      :disabled="!valid"
      class="mr-4"
      @click="validate"
    >
      Sačuvaj
    </v-btn>
    
    <v-btn v-if = "!this.addMode"
      :disabled="!valid"
      class="mr-4"
      @click="validate"
    >
      Sačuvaj izmene
    </v-btn>

    <v-btn v-if = "!this.addMode"
      color="error"
      class="mr-4"
      @click="reset"
    >
      Resetuj
    </v-btn>
  </v-form>
  <v-dialog
      v-model="dialog"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>Izmene su uspešno sačuvane.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endDialog"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>

  <v-dialog
      v-model="dialogAdd"
      persistent
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">
          Obaveštenje
        </v-card-title>
        <v-card-text>Lek uspešno dodat.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="endDialogAdd"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>
</v-card>
</template>

<script>
import Vue from "vue";

  export default {
      name: "UpdateAddLekApotekeForm",
    data: () => ({
      newInfo: {
          cena: "",
      },
      valid: true,
      
      brojRules: [
        v => !!v || 'Obavezno polje',
        v => (v && v.length <= 10 ) || 'Dužina maksimalno 10 karaktera',
        v => (new RegExp("^(0|([1-9][0-9]*))(\\.[0-9]+)?$").test(v)) || 'Loš format',
      ],
      requiredRules: [
        v => !!v || 'Obavezno polje',
      ],
      
      dialog: false,
      dialogAdd: false,
    }),
    props :{
        lekUMagacinu: {},
        apotekaId: Number,
        lekToAddId:Number,
        addMode: Boolean,
    },
    created(){
        this.reset()
    },
    mounted(){
      if(!this.addMode)
        this.newInfo.cena = this.lekUMagacinu.cena;
    },

    methods: {
      validate () {
        if(this.$refs.form.validate()){
          if (!this.addMode){
              this.lekUMagacinu.cena = this.newInfo.cena;
            Vue.axios.put(`/apoteke/izmeniLek/${this.apotekaId}`,this.lekUMagacinu);
            this.dialog=true;
          }else{
            Vue.axios.put(`/apoteke/dodajLek/${this.lekToAddId}/${this.apotekaId}`,this.newInfo.cena, {headers: {"Content-Type": "text/plain"}});
            this.dialogAdd = true;
          }
           
        }
        
      },
      reset () {
        if (!this.addMode){
            this.newInfo.cena=this.lekUMagacinu.cena;
        }
        
        
      },
      endDialog(){
        this.dialog=false;
        location.reload();
      },
      endDialogAdd(){
        this.dialogAdd=false;
        location.reload();
      },
    },
  }
</script>