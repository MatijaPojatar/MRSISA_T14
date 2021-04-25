<template>
    <v-card>
        <v-card-title>Prijava</v-card-title>
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
                    :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                    :rules="[rules.required]"
                    :type="show ? 'text' : 'password'"
                    label="Lozinka"
                    @click:append="show = !show"
                />
            </v-form>
        </v-card-text>
        <v-card-actions>
            <v-btn @click="cancel" class>Odustani</v-btn>
            <v-spacer></v-spacer>
            <v-btn v-on:click="onSubmit" :disabled="!valid" class="blue white--text">Prijava</v-btn>
        </v-card-actions>
        <v-card-actions>
            <v-btn @click="loginAsPacijent">Pacijent</v-btn>
            <v-btn @click="loginAsFarmaceut">Farmaceut</v-btn>
            <v-btn @click="loginAsDermatolog">Dermatolog</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {
    name: "Prijava",

    props : {
        value : Boolean,
    },

    data: () => ({
        email: "",
        lozinka: "",
        valid: true,
        show: false,
        rules: {
            required: v => !!v || "Obavezno polje",
            email: v => /.+@.+\..+/.test(v) || "Email nije validan"
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
        ...mapGetters({
            currentMail: "korisnici/getMail"
        })
    },
    methods: {
        ...mapActions({
            logInAction: "korisnici/logInAction"
        }),

        onSubmit(){
            alert("Funkcija nije u funkcija");
        },

        async loginAsPacijent(){
            try{
                const credentials = {
                    mail : this.email,
                    password : this.lozinka
                }
                await this.logInAction(credentials);
                alert("USPEH");
                //this.$router.push('/pacijent');
            }catch(error){
                alert("Greska pri prijavi")
            }
            
        },

        loginAsFarmaceut(){
            this.$router.push('/farmaceut');
        },

        loginAsDermatolog(){
            this.$router.push('/dermatolog');
        },
    },
}
</script>

<style>
</style>