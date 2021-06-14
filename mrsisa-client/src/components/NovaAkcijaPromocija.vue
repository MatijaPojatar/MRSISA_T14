<template>
  <v-container justify="center" max-width="100">

<div justify="space-around" align="center" >
    <v-card max-width="70vh">
    <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        transition="scale-transition"
        offset-y
        min-width="auto"
    >
        <template v-slot:activator="{ on, attrs }">
        
        <v-text-field
            v-model="definisanRok"
            label="Rok važenja:"
            prepend-icon="mdi-calendar"
            readonly
            required
            v-bind="attrs"
            v-on="on"
        ></v-text-field>
        
        </template>
        <v-date-picker
        ref="picker"
        v-model="definisanRok"
        
        :min="new Date().toISOString().substr(0, 10)"
        @change="$refs.menu.save(definisanRok)"
        ></v-date-picker>
        
    </v-menu>
    <br/>
    <v-textarea
            v-model="opis"
            label="Opis"
            required
        ></v-textarea>
    
  <br/>
    <v-btn
                class="mx-2"
                @click="kreirajAkciju"
            >
               Kreiraj
            </v-btn>
    
  </v-card>
  </div>
  <v-divider/>
    <br/>


  <v-dialog
      v-model="obavestenje"
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
            @click="endObavestenje"
          >
            Ok
          </v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>
      
  </v-container >
</template>

<script>
 import Vue from "vue";

    export default{
        data: () => ({
           
            
            definisanRok: null,
            opis: "",
            menu: false,
            obavestenje:false,
            message:"",

            valid: true,
            requiredRules: [
                v => !!v || 'Obavezno polje',
            ],
            
            dialog: false,
            
         }),
        props:{
            apotekaId: Number,
            user:{},
            
        },
        components:{
        },
        async mounted(){
        },
        methods:{
                kreirajAkciju(){
                    if(!this.definisanRok){
                        this.message= "Rok važenja nije definisan."
                        this.obavestenje = true;
                    
                    }else{
                        Vue.axios.post(`/akcijaPromocija/dodaj/${this.apotekaId}`, {opis:this.opis, kraj:this.definisanRok});
                        this.message= "Uspešno kreiranje."
                        this.reset();
                        this.obavestenje = true;
                    }
                },
                reset(){
                    this.definisanRok=null;
                    this.opis="";
                },
                endObavestenje(){
                    this.obavestenje=false;
                }
                

             

         }
    }

</script>