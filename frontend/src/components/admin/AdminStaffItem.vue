<template>
    <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="staffEdit()">
              <td>{{adminstaff.id}}</td>
              <td>{{adminstaff.userId}}</td>
              <td>{{adminstaff.name}}</td>
              <td>{{adminstaff.email}}</td>
              <td>{{adminstaff.phone}}</td>
              <td>{{adminstaff.areaId}}</td>
              <td>{{adminstaff.areaName}}</td>
              <td v-if="adminstaff.approveYN == 'Y'" class="table-yes">Y</td>
              <td v-else class="t-red-2">N</td>
              <td>{{adminstaff.createdAt}}</td>
              <td>{{adminstaff.updatedAt}}</td>
     </tr>
</template>


<script>
// import moment from "moment";

export default {
  name: "AdminStaffItem",
  props:{
    adminstaff: Object,
  },
  // props: {
  //   articleno: Number,
  //     userid: String,
  //     username: String, //name
  //     email: String,
  //     phone: String,
  //     areaid: Number,
  //     areaname: String,
  //     approveYN: String,
  //     createdAt: String,
  //     updatedAt: String,
  // },
  methods:{
    staffEdit(){
      this.$router.push({ name: 'AdminStaffEdit',
        params:
          {
            id: this.adminstaff.id,
            userId: this.adminstaff.userId,
            name: this.adminstaff.name,
            email: this.adminstaff.email,
            phone: this.adminstaff.phone,
            areaId: this.adminstaff.areaId,
            areaName: this.adminstaff.areaName,
            approveYN: this.adminstaff.approveYN,
            createdAt: this.adminstaff.createdAt,
            updatedAt: this.adminstaff.updatedAt,
          }
          
        
      })
      console.log(this.adminstaff.userId);
    },
	},
  computed: {
    

  },
  
  
};
</script>

<style></style>
