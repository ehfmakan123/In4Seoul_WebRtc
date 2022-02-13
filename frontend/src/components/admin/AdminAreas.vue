<template>
<option v-bind:value="adminarea.id">{{adminarea.id}}. {{adminarea.korName}}</option>
    
</template>


<script>


export default {
  name: "AdminAreas",
  props:{
    adminareas: Object,
  },
  methods:{
    
	},
  computed: {
    

  },
  
  
};
</script>

<style></style>
