<template>
    <v-card>
        <v-card-title>Dodavanje alergije: </v-card-title>
        <v-card-text>
            <v-form ref="forma" v-model="valid" lazy-validation v-for="(lek, index) in lekovi" :key="index">
                <v-row>
                    <v-col>
                        <v-select
                            v-model="lek.naziv"
                            :items="items"
                            label="Lek"
                        />
                    </v-col>
                    <v-col>
                        <v-btn @click="dodaj">+</v-btn>
                        <v-btn @click="ukloni">-</v-btn>
                    </v-col>
                </v-row>
            </v-form>
        </v-card-text>
        <v-card-actions>
            <v-btn>Odustani</v-btn>
            <v-btn class="blue white--text">Potvrdi</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
export default {
    name: "DodajAlergiju",

    props : {
    
    },

    data: () => ({
        lekovi: [
        {
            naziv: ""
        }],
        items: ["Aspirin", "Bromazepam", "Ibuprofen", "Kafetin"],
    }),

    methods: {
        dodaj() {
            this.lekovi.push({
                naziv: ""
            })
        },

        ukloni(index) {
            this.lekovi.splice(index, 1);
        },
    },
}
</script>

<style>
</style>