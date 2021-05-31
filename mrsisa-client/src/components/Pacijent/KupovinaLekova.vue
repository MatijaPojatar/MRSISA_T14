<template>
  <!-- <div
  v-if="upload">
    <v-file-input
      accept="image/*"
      label="File input"
    ></v-file-input>
  </div> -->

  <div class="container">
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
      </label>
        <button v-on:click="submitFile()">Submit</button> 
        <!-- dodati atribut DA SAMO SLIKE PRIMA? -->
    </div>
  </div>
</template>

<script>
import {mapActions} from 'vuex';

export default {
  ...mapActions({
    sendFileAction: "erecepti/sendFileAction",
  }),
  data: () => ({
    file: '',
  }),

  methods: {
    handleFileUpload(){
      this.file = this.$refs.file.files[0]; 
    },

    async submitFile(){
      let formData = new FormData();
      formData.append('file', this.file);
      try{
        await this.sendFileAction(formData);
      }catch(error){
        alert("Greska pri upload-u fajla")
      }
      
    }
  }
}
</script>

<style>

</style>