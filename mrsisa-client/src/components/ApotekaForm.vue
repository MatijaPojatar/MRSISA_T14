<template>
<v-card>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
    <v-text-field
      v-model="newInfo.naziv"
      :counter="50"
      :rules="nameRules"
      label="Naziv"
      :readonly="!editable"
      required
    ></v-text-field>


    <v-text-field
      v-model="newInfo.adresa"
      :rules="requiredRules"
      :readonly="!editable"
      label="Adresa"
      required
    ></v-text-field>

    <v-text-field
      v-model="newInfo.grad"
      :rules="requiredRules"
      :readonly="!editable"
      label="Grad"
      required
    ></v-text-field>

    <v-text-field
      v-model="newInfo.drzava"
      :rules="requiredRules"
      :readonly="!editable"
      label="Država"
      required
    ></v-text-field>

    <v-textarea
      v-model="newInfo.opis"
      :counter="500"
      :rules="opisRules"
      label="Opis"
      :readonly="!editable"
      required
    ></v-textarea>

    <v-checkbox
      v-model="checkbox"
      v-if="editable"
      :rules="[v => !!v || 'Prihvatite da biste nastavili.']"
      label="Da li ste sigurni?"
      required
    ></v-checkbox>

    <v-btn
      :disabled="!valid"
      v-if="editable"
      class="mr-4"
      @click="validate"
    >
      Sačuvaj izmene
    </v-btn>

    <v-btn
      color="error"
      class="mr-4"
      v-if="editable"
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

  
</v-card>
</template>

<script>
import Vue from "vue";

  export default {

    data: () => ({
      newInfo: {
          naziv: "",
          opis: "",
          adresa: "",
          grad: "",
          drzava: "",
      },
      valid: true,
      nameRules: [
        v => !!v || 'Obavezno polje',
        v => (v && v.length <= 50) || 'Dužina maksimalno 50 karaktera',
      ],
      opisRules: [
        v => !!v || 'Obavezno polje',
        v => (v && v.length <= 50) || 'Dužina maksimalno 500 karaktera',
      ],
      requiredRules: [
        v => !!v || 'Obavezno polje',
      ],
      checkbox: false,
      dialog: false,
    }),
    props :{
        editable: Boolean,
        apotekaId: Number,
    },
    created(){
        this.reset()
    },
    mounted(){
      this.reset()
     
    },

    methods: {
      validate () {
        if(this.$refs.form.validate()){
            Vue.axios.put(`http://localhost:8080/apoteke/save/${this.apotekaId}`, this.newInfo)
            this.dialog = true;
        }
        
      },
      reset () {
        console.log(this.user);
        Vue.axios.get(`http://localhost:8080/apoteke/getOne/${this.apotekaId}`).then(response => {
            this.newInfo = response.data
            console.log(this.newInfo)
        });
      },
      endDialog(){
        this.dialog=false;
        //location.reload();
      },
      destroy(){
         this.$destroy();
      }
    },
  }
</script>