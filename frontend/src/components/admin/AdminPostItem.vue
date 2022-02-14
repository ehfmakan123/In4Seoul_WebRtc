<template>
    <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="postEdit()">
              <td>{{adminpost.id}}</td>
              <td>{{adminpost.areaName}}</td>
              <td>{{adminpost.deskName}}</td>
              <td>{{adminpost.title}}</td>
              <td v-if="adminpost.content.length<=14">{{adminpost.content}}</td>
              <td v-else>{{adminpost.content.substring(0,10)}}...</td>
              <td>{{adminpost.createdAt}}</td>
              <td>{{adminpost.updatedAt}}</td>
     </tr>
</template>


<script>
// import moment from "moment";

export default {
  name: "AdminPostItem",
  props:{
    adminpost: Object,
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
    postEdit(){
      this.$router.push({ name: 'AdminPostEdit',
        params:
          {
            id: this.adminpost.id,
            title: this.adminpost.title,
            areaName: this.adminpost.areaName,
            deskName: this.adminpost.deskName,
            content: this.adminpost.content,
            createdAt: this.adminpost.createdAt,
            updatedAt: this.adminpost.updatedAt,
          }
          
        
      })
      console.log("length: "+this.adminpost.title.length);
    },

	},
  computed: {
    

  },
  
  
};
</script>

<style></style>
