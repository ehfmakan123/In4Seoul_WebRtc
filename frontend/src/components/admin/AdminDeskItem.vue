<template>
    <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="deskEdit()">
              <td>{{admindesk.id}}</td>
              <td>{{admindesk.deskId}}</td>
              <td>{{admindesk.korName}}</td>
              <td>{{admindesk.engName}}</td>
              <!-- <td v-if="admindesk.password.length<=10">{{admindesk.password}}</td>
              <td v-else>{{admindesk.password.substring(0,9)}}...</td> -->
              <td>{{admindesk.latitude}}</td>
              <td>{{admindesk.altitude}}</td>
              <td>{{admindesk.areaId}}</td>
              <td>{{admindesk.areaName}}</td>
              <td>{{admindesk.createdAt}}</td>
              <td>{{admindesk.updatedAt}}</td>
     </tr>
</template>


<script>
// import moment from "moment";

export default {
  name: "AdminDeskItem",
  props:{
    admindesk: Object,
  },
  methods:{
    deskEdit(){
      this.$router.push({ name: 'AdminDeskEdit',
        params:
          {
            id: this.admindesk.id,
            deskId: this.admindesk.deskId,
            korName: this.admindesk.korName,
            engName: this.admindesk.engName,
            password: this.admindesk.password,
            latitude: this.admindesk.latitude,
            altitude: this.admindesk.altitude,
            areaId: this.admindesk.areaId,
            areaName: this.admindesk.areaName,
            createdAt: this.admindesk.createdAt,
            updatedAt: this.admindesk.updatedAt,
          }
          
        
      })
      console.log("test")
      console.log(this.admindesk.id)
    },
	},
  computed: {
    

  },
  
  
};
</script>

<style></style>
