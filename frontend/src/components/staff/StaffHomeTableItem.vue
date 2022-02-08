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
    index: { type: String },
    tableItem: { type: Object },
  },
  setup(props) {
    console.log(`${props.index}번째 StaffHomeTableItem `,props.key,props.tableItem) // key가 안나옴

  }
}
</script>

<style>

</style>