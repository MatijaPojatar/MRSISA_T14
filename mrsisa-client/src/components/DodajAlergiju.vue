<template>
    <v-card>
        <v-card-title>Dodavanje alergije: </v-card-title>
        <v-card-text>
            <v-form ref="forma" v-model="valid" lazy-validation>
                <v-select
                    v-model="lek"
                    :rules="[rules.required, rules.email]"
                    label="Lek"
                    required
                />
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
        lek: "",

        rules: {
            required: v => !!v || "Obavezno polje",
        }
    })
}
</script>

<style>
</style>