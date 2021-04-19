<template>
  <v-app>
      <router-view/>
      <v-footer app fixed height=50>
      <span>&copy; MRSISA TIM14 2021</span>
      <v-spacer/>
      <v-switch
            v-model="$vuetify.theme.dark"
            label="Dark mode"
            color="black"
            dense
      ></v-switch>
    </v-footer>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  components: {

  },

  data: () => ({
  }),
};
</script>

