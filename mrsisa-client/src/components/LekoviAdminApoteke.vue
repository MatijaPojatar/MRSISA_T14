<template>
    <ListLekoviV2 :apotekaId = "apotekaId" :adminView = "true"/>
</template>

<script>
import ListLekoviV2 from "./ListLekoviV2";

export default{
    name: "LekoviAdminApoteke",
    components: {
        ListLekoviV2
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