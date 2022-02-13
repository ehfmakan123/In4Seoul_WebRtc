<template>
  <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip">
      <td>{{ index }}</td>
      <td>{{ tableItem.id }}</td>
      <td>{{ tableItem.deskName }}</td>
      <td>{{ tableItem.startTime }}</td>
      <td>{{ tableItem.endTime }}</td>
      <td>{{ tableItem.content }}</td>
  </tr>  
</template>

<script>
export default {
  name: 'StaffHometableItem',
  props: {
    // index: { type: String },
    // tableItem: { type: Object },
    tableItem: Object,
  },
  methods:{
    staffEdit(){
      this.$router.push({ name: 'AdminStaffEdit',
        params:
          {
            id: this.tableItem.id,
            deskName: this.tableItem.deskName,
            startTime: this.tableItem.startTime,
            endTime: this.tableItem.endTime,
            content: this.tableItem.content,
          }
          
        
      })
      console.log(this.tableItem.userId);
    },
	},
  // setup(props) {
  //   console.log(`${props.index}번째 StaffHomeTableItem `,props.key,props.tableItem) // key가 안나옴

  // }
}
</script>

<style>

</style>