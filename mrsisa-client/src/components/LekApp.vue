<template>
  <div>
    <h1>Lekovi</h1>
    <ListLekovi/>
  </div>
</template>

<script>
import ListLekovi from "./ListLekovi"

export default {
  name: "LekApp",
  components: {
    ListLekovi
  }
}
</script>

<style scoped>

</style>