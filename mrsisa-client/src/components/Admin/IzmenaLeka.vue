<template>
<div>
  <v-card v-if="nastavi">
    <v-card-title>Izmena leka: </v-card-title>
    <v-card-text>
      <v-form ref= "forma" v-model="valid" lazy-validation>
        <v-row>

          <v-col>
            <v-text-field
            v-model="lek.naziv"
            :rules="[rules.required]"
            label="Naziv"
            required
            />

            <v-text-field
            v-model="lek.proizvodjac"
            :rules="[rules.required]"
            label="Proizvodjac"
            required
            />

          <v-autocomplete
            v-model="lek.rezimIzdavanja"
            :items="allRezimiIzdavanja"
            label="Rezim izdavanja"
            required>
          </v-autocomplete>
          
          <v-container>

            <v-row>
              <v-list-item>
                  <v-chip  dark  color="grey">Ime leka</v-chip>
                  <v-chip class="ml-2" dark  color="grey">Proizvodjac</v-chip>
                  <v-spacer></v-spacer>

                  <v-chip class="ml-3 mr-3" dark color="grey">Dodaj kao zamenski</v-chip>
              </v-list-item>
            </v-row>
            <v-row>
              <v-virtual-scroll
              :items="allLekovi"
              :item-height="50"
              height="300"
              >
                <template v-slot:default="{ item }">
                  <v-list-item>

                    <v-list-item-content>
                      <v-list-item-title class="text-left">{{item.naziv}} - {{item.proizvodjac}}</v-list-item-title>
                    </v-list-item-content>

                    <v-spacer/>

                    <v-list-item-action>

                      <v-btn red v-on:click="dodajZamenski(item)">
                        <v-icon>mdi-plus-thick</v-icon>
                      </v-btn>
                    </v-list-item-action>

                  </v-list-item>
                </template>

              </v-virtual-scroll>

            </v-row>
          </v-container>
          
          </v-col>

          <v-col>
            <v-autocomplete
              v-model="lek.vrstaLeka"
              :items="allVrste"
              label="Vrsta leka"
              reqired
            ></v-autocomplete>

            <v-autocomplete
              v-model="lek.oblikLeka"
              :items="allOblici"
              label="Oblik leka"
              required
            ></v-autocomplete>

            <v-text-field
            v-model="lek.sastav"
            :rules="[rules.required]"
            label="Sastav"
            required
            />

            <v-textarea
            height="70"
            outlined
            v-model="lek.napomena"
            label="Napomena"
          ></v-textarea>

          <v-container>
            <v-row>
              <h4>Odabrani zamenski lekovi</h4>
            </v-row>
            <v-row>
              <v-list-item>
                  <v-chip  dark  color="grey">Ime leka</v-chip>
                  <v-chip class="ml-2" dark  color="grey">Proizvodjac</v-chip>
                  <v-spacer></v-spacer>

                  <v-chip class="ml-3 mr-3" dark color="grey">Ukloni</v-chip>
              </v-list-item>
            </v-row>
            <v-row>
              <v-virtual-scroll
              :items="currZamenski"
              :item-height="50"
              height="200"
              >
                <template v-slot:default="{ item }">
                  <v-list-item>

                    <v-list-item-content>
                      <v-list-item-title class="text-left">{{item.naziv}} - {{item.proizvodjac}}</v-list-item-title>
                    </v-list-item-content>

                    <v-spacer/>

                    <v-list-item-action>

                      <v-btn red v-on:click="ukloniZamenski(item)">
                        <v-icon>mdi-delete</v-icon>
                      </v-btn>
                    </v-list-item-action>

                  </v-list-item>
                </template>

              </v-virtual-scroll>

            </v-row>
          </v-container>
          

          </v-col>
        </v-row>

      </v-form>
    </v-card-text>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn @click="cancel">Odustani</v-btn>
      <v-btn @click="onSubmit" :disabled="!valid">Izmeni</v-btn>
    </v-card-actions>


  </v-card>
  <v-dialog
      v-if="nastavi"
      v-model="odabir"
      persistent
      max-width="400">
    <v-card>
       <v-container>

            <v-row>
              <v-list-item>
                  <v-chip  dark  color="grey">Ime leka</v-chip>
                  <v-spacer></v-spacer>

                  <v-chip class="ml-3 mr-3" dark color="grey">Odaberi</v-chip>
              </v-list-item>
            </v-row>
            <v-row>
              <v-virtual-scroll
              :items="allLekovi"
              :item-height="50"
              height="300"
              >
                <template v-slot:default="{ item }">
                  <v-list-item>

                    <v-list-item-content>
                      <v-list-item-title class="text-left">{{item.naziv}}</v-list-item-title>
                    </v-list-item-content>

                    <v-spacer/>

                    <v-list-item-action>

                      <v-btn red v-on:click="odaberiLek(item)">
                        <v-icon>mdi-plus-thick</v-icon>
                      </v-btn>
                    </v-list-item-action>

                  </v-list-item>
                </template>

              </v-virtual-scroll>

            </v-row>
          </v-container>
        <v-card-actions>
          <v-btn
          @click="odustani"
          >Cancel</v-btn>
        </v-card-actions>
    </v-card>
  </v-dialog>
  </div>
</template>

<script>
import {mapActions, mapGetters } from "vuex"

export default {
  name: "IzmenaLeka",

  props : {
    
  },

  data: () => ({
    nastavi: true,
    // sifra: "",
    // naziv: "",
    // proizvodjac: "",

    // rezimIzdavanja: "",

    // vrsta: "",
    // oblik: "",
    // sastav: "",

    // zamenskiLekovi: [],
    // napomena: "",
    lek: {},

    odabir: true,
    valid: true,

    rules: {
            required: v => !!v || "Obavezno polje",
        }
  }),

  computed: {
    ...mapGetters({
      allOblici: "lekovi/getOblici",
      allVrste: "lekovi/getVrste",
      allRezimiIzdavanja: "lekovi/getRezimiIzdavanja",
      allLekovi: "lekovi/getLekovi",
      currentLek: "lekovi/getCurrentLek",
      currZamenski: "lekovi/getCurrZamenski"
    }),
  },

  methods: {
    ...mapActions({
      getObliciAction: "lekovi/getObliciAction",
      getVrsteAction: "lekovi/getVrsteAction",
      addLekAction: "lekovi/addLekAction",
      izmeniLekAction: "lekovi/izmeniLekAction",
      getRezimiIzdavanjaAction: "lekovi/getRezimiIzdavanjaAction",
      getLekoviAction: "lekovi/getLekoviAction",
      removeLekAction: "lekovi/removeLekAction",
      addZamenskeLekoveAction: "lekovi/addZamenskeLekoveAction",
      getZamenskeAction: "lekovi/getZamenskeAction"
    }),

    odustani(){
      this.nastavi = false;
    },  

    async onSubmit() {
      if(this.$refs.forma.validate()){
        const lekInfo = {
          id: this.lek.id,
          naziv : this.lek.naziv,
          sastav : this.lek.sastav,
          proizvodjac : this.lek.proizvodjac,
          napomena : this.lek.napomena,
          rezimIzdavanja : this.lek.rezimIzdavanja,
          oblikLeka : this.lek.oblikLeka,
          vrstaLeka : this.lek.vrstaLeka,
        }

        let zamenskiIds = [];
        let index;
        for( index in this.currZamenski){
          zamenskiIds.push(this.currZamenski[index].id);
        }
        
        let izmenjeniLek;
        try{
          await this.izmeniLekAction(lekInfo);

          izmenjeniLek = this.currentLek;
          this.addZamenskeLekoveAction({ id: izmenjeniLek.id, zamenski: zamenskiIds} );
       
        }catch(e){
          alert("Greska pri kreiranju leka ili dodavanjem zamenskih");
          this.removeLekAction(izmenjeniLek.id);  //istestirati brisanje
          return;
        }

        alert("Lek uspesno izmenjen")
      }
      this.cancel();
    },

    async odaberiLek(lek){
      this.lek = lek;
      this.odabir = false;
      await this.getZamenskeAction(this.lek.id);
    },

    dodajZamenski(lek) {
      if (this.currZamenski.includes(lek)){
        //ovde dodati obavestenje da ne moze isti, ili samo ibaciti pre listanja
        return;
      }

      this.zamenskiLekovi.push(lek);
    },

    ukloniZamenski(lek){
      const index = this.currZamenski.findIndex((l) => l.id === lek.id);
      this.currZamenski.splice(index,1);
    },

    cancel(){
      this.reset();
      this.resetValidation();
    },

    reset(){
      this.$refs.forma.reset();
    },

    resetValidation(){
      this.$refs.forma.resetValidation();
    }
  },

  async beforeMount() {
    this.getObliciAction();
    this.getVrsteAction();
    this.getRezimiIzdavanjaAction();
    this.getLekoviAction();
  }
}
</script>

<style>

</style>