<template>
    <div>
        <GmapMap
            :center="StartCoordinates"
            :zoom="zoom"
            style="width:320px; height:180px; margin: 32px auto;"
            ref="mapRef"
            @dragend="handleDrag"
        >
            <GmapMarker
                :key = 1
                :position="marker"
                @click="center=m"
            ></GmapMarker>
        </GmapMap>
    </div>
</template>


<script>
    export default {
        data() {
            return {
                map: null,
                StartCoordinates: {
                    lat: 0,
                    lng: 0
                },
                marker: {
                    lat: 45.2396,
                    lng: 19.8227
                },

                zoom: 7
            }
        },
        props: {
            apotekaLat:Number,
            apotekaLng:Number,
        },
        created() {
           
            // does the user have a saved center? use it instead of the default
            // if(localStorage.center) {
            //     this.myCoordinates = JSON.parse(localStorage.center);
            // } else {
            //     // get user's coordinates from browser request
            //     this.$getLocation({})
            //         .then(coordinates => {
            //             this.myCoordinates = coordinates;
            //         })
            //         .catch(error => alert(error));
            // }
            // does the user have a saved zoom? use it instead of the default
            if(localStorage.zoom) {
                this.zoom = parseInt(localStorage.zoom);
            }
            this.StartCoordinates.lat = this.apotekaLat
            this.StartCoordinates.lng = this.apotekaLng
        },
        mounted() {
            // add the map to a data object
            this.$refs.mapRef.$mapPromise.then(map => this.map = map);
            console.log(this.apotekaLat)
            this.marker.lat = this.apotekaLat
            this.marker.lng = this.apotekaLng
            
        },
        methods: {
            handleDrag() {
                // get center and zoom level, store in localstorage
                let center = {
                    lat: this.map.getCenter().lat(),
                    lng: this.map.getCenter().lng()
                };
                let zoom = this.map.getZoom();
                localStorage.center = JSON.stringify(center);
                localStorage.zoom = zoom;
            }
        },
        computed: {
            mapCoordinates() {
                if(!this.map) {
                    return {
                        lat: 0,
                        lng: 0
                    };
                }
                return {
                    lat: this.map.getCenter().lat().toFixed(4),
                    lng: this.map.getCenter().lng().toFixed(4)
                }
            }
        }
    }
</script>