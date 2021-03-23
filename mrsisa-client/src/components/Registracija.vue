<template>
    <v-card>
        <v-card-title class="display-1 red--text">Registracija</v-card-title>
        <v-card-text>
            <v-form ref="forma" v-model="valid" lazy-validation>
                <v-text-field
                    v-model="email"
                    :rules="[rules.required, rules.email]"
                    label="Email"
                    required
                />
                <v-text-field
                    v-model="lozinka"
                    :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                    :rules="[rules.required]"
                    :type="show1 ? 'text' : 'password'"
                    label="Lozinka"
                    @click:append="show1 = !show1"
                />
                <v-text-field
                    v-model="potvrdaLozinke"
                    :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                    :rules="[rules.required]"
                    :type="show2 ? 'text' : 'password'"
                    label="Potvrda lozinke"
                    @click:append="show2 = !show2"
                />
                <v-text-field
                    v-model="ime"
                    :rules="[rules.required]"
                    label="Ime"
                    required
                />
                <v-text-field
                    v-model="prezime"
                    :rules="[rules.required]"
                    label="Prezime"
                    required
                />
                <!--
                    pol
                    datum rodjenja
                -->
                <v-text-field
                    v-model="adresa"
                    :rules="[rules.required]"
                    label="Adresa"
                    required
                />
                <v-text-field
                    v-model="grad"
                    :rules="[rules.required]"
                    label="Grad"
                    required
                />
                <v-text-field
                    v-model="drzava"
                    :rules="[rules.required]"
                    label="Država"
                    required
                />
                <v-text-field
                    v-model="drzava"
                    :rules="[rules.required, rules.telefon]"
                    label="Broj telefona"
                    required
                />
            </v-form>
        </v-card-text>
        <v-card-actions>
            <v-btn @click="cancel" class="grey white--text">Odustani</v-btn>
            <v-spacer></v-spacer>
            <v-btn v-on:click="onSubmit" :disabled="!valid" class="red white--text">Registracija</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>

export default {
    name: "Registracija",

    props : {
        value : Boolean,
    },

    data: () => ({
        email: "",
        password: "",
        valid: true,
        show1: false,
        show2: false,
        rules: {
            required: v => !!v || "Obavezno polje",
            email: v => /.+@.+\..+/.test(v) || "Email nije validan",
            telefon: v => /^\d+$/.test(v) || "Broj telefona nije validan"
        }
    }),
    
    computed: {
        show: {
            get() {
                return this.value;
            },
            set(value){
                this.$emit("input", value);
            }
        },
    }
}
</script>

<style>
</style>