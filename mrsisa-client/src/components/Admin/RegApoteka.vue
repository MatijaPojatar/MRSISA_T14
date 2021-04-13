<template>
  <v-card>
    <v-card-title>
      Registracija Apoteke
    </v-card-title>

    <v-card-text>
      <v-form ref="forma" v-model="valid" lazy-alidation>
        <v-text-field
        v-model="naziv"
        label="Naziv"
        :rules="[rules.required]"
        required
        />

        <!-- <v-text-field
        v-model="adresa"
        label=
        :rules=""
        required
        />
        <v-text-field
        v-model="grad"
        label=
        :rules=""
        required
        />
        <v-text-field
        v-model="drzava"
        label=
        :rules=""
        required
        />
        <v-text-field
        v-model="opis"
        label=
        :rules=""
        required
        /> -->
      </v-form>
      


    </v-card-text>
  </v-card>
</template>

<script>
export default {

}
</script>

<style>

</style>

naziv i adresu apoteke (dodatno prikaz adrese na mapi), 
● listu dermatologa i farmaceuta koji su zaposleni u njoj, 
● listu lekova koje apoteka ima na stanju, 
● listu svih termina za preglede kod dermatologa koje može da zakaže, 
● prosečnu ocenu apoteke. 

Integer id, String naziv, String adresa, String grad, String drzava, String opis,
			List<Lek> lekovi, Set<Farmaceut> farmaceuti, Set<Dermatolog> dermatolozi, List<Termin> termini,
			Magacin magacin