<template>
  <div>
    <router-view/>
  </div>
</template>

<script>
import TestApp from './components/TestApp'
import LekApp from './components/LekApp'

export default {
  name: 'App',

  components: {
    //TestApp,
    //LekApp,

  },

  data: () => ({
    //
  }),
};
</script>
