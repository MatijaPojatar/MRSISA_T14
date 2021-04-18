<template>
    <ListFarmaceuti :apotekaId = "apotekaId" :adminView = "true"/>
</template>

<script>
import ListFarmaceuti from "./ListFarmaceuti";

export default{
    name: "FarmaceutiAdminApoteke",
    components: {
        ListFarmaceuti
    },
    data: () => ({
    
    }),
    props :{
        user: {},
        apotekaId: Number,
    },
}
</script>

<style>

</style>