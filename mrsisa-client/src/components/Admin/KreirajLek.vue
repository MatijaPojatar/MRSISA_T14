<template>
  <v-card>
    <v-card-title>Unos leka: </v-card-title>
    <v-card-text>
      <v-form ref= "forma" v-model="valid" lazy-validation>
        <v-row>

          <v-col>
            <v-text-field
            v-model="naziv"
            :rules="[rules.required]"
            label="Naziv"
            required
            />

            <v-text-field
            v-model="proizvodjac"
            :rules="[rules.required]"
            label="Proizvodjac"
            required
            />

          <v-autocomplete
            v-model="rezimIzdavanja"
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
            v-model="vrsta"
              :items="allVrste"
              label="Vrsta leka"
              reqired
            ></v-autocomplete>

            <v-autocomplete
              v-model="oblik"
              :items="allOblici"
              label="Oblik leka"
              required
            ></v-autocomplete>

            <v-text-field
            v-model="sastav"
            :rules="[rules.required]"
            label="Sastav"
            required
            />

            <v-textarea
            height="70"
            outlined
            v-model="napomena"
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
              :items="zamenskiLekovi"
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
      <v-btn @click="onSubmit" :disabled="!valid">Kreiraj</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import {mapActions, mapGetters } from "vuex"

export default {
  name: "KreirajLek",

  props : {
    
  },

  data: () => ({
    sifra: "",
    naziv: "",
    proizvodjac: "",

    rezimIzdavanja: "",

    vrsta: "",
    oblik: "",
    sastav: "",

    zamenskiLekovi: [],
    napomena: "",

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
      currentLek: "lekovi/getCurrentLek"
    }),
  },

  methods: {
    ...mapActions({
      getObliciAction: "lekovi/getObliciAction",
      getVrsteAction: "lekovi/getVrsteAction",
      addLekAction: "lekovi/addLekAction",
      getRezimiIzdavanjaAction: "lekovi/getRezimiIzdavanjaAction",
      getLekoviAction: "lekovi/getLekoviAction",
      removeLekAction: "lekovi/removeLekAction",
      addZamenskeLekoveAction: "lekovi/addZamenskeLekoveAction"
    }),

    async onSubmit() {
      if(this.$refs.forma.validate()){
        const lekInfo = {
          naziv : this.naziv,
          sastav : this.sastav,
          proizvodjac : this.proizvodjac,
          napomena : this.napomena,
          rezimIzdavanja : this.rezimIzdavanja,
          oblikLeka : this.oblik,
          vrstaLeka : this.vrsta,
        }

        let zamenskiIds = [];
        let index;
        for( index in this.zamenskiLekovi){
          zamenskiIds.push(this.zamenskiLekovi[index].id);
        }
        
        let kreiraniLek;
        try{
          await this.addLekAction(lekInfo);
          kreiraniLek = this.currentLek;
          this.addZamenskeLekoveAction({ id: kreiraniLek.id, zamenski: zamenskiIds} );
       
        }catch(e){
          alert("Greska pri kreiranju leka ilidodavanjem zamenskih");
          this.removeLekAction(kreiraniLek.id);  //istestirati brisanje
          return;
        }

        alert("Lek uspesno dodat")
      }
      this.cancel();
    },

    dodajZamenski(lek) {
      if (this.zamenskiLekovi.includes(lek)){
        return;
      }

      this.zamenskiLekovi.push(lek);
    },

    ukloniZamenski(lek){
      const index = this.zamenskiLekovi.findIndex((l) => l.id === lek.id);
      this.zamenskiLekovi.splice(index,1);
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