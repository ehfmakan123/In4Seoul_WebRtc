<template>
  <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip">
      <!-- <td>{{ index }}</td> -->
      <td>{{ staffItem.id }}</td>
      <td>{{ staffItem.deskName }}</td>
      <td>{{ staffItem.startedAt }}</td>
      <td>{{ staffItem.endedAt }}</td>
      <!-- <td>{{ staffItem.content }}</td> -->
  </tr>  
</template>

<script>
export default {
  name: 'StaffHomestaffItem',
  props: {
    // index: { type: String },
    // staffItem: { type: Object },
    staffItem: Object,
  },
  methods:{
    // staffEdit(){
    //   this.$router.push({ name: 'AdminStaffEdit',
    //     params:
    //       {
    //         id: this.staffItem.id,
    //         deskName: this.staffItem.deskName,
    //         startTime: this.staffItem.startTime,
    //         endTime: this.staffItem.endTime,
    //         content: this.staffItem.content,
    //       }
          
        
    //   })
    //   console.log(this.staffItem.userId);
    // },
	},
  // setup(props) {
  //   console.log(`${props.index}번째 StaffHomeTableItem `,props.key,props.staffItem) // key가 안나옴

  // }
}
</script>

<style>

</style>