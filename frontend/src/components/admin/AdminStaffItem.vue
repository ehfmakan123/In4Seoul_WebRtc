<template>
    <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="staffEdit()">
        <!-- <router-link
        :to="{ name: 'BoardView', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      > -->
              <td>{{articleno}}</td>
              <td>{{userid}}</td>
              <td>{{username}}</td>
              <td>{{email}}</td>
              <td>{{phone}}</td>
              <td>{{areaid}}</td>
              <td>{{areaname}}</td>
              <td class="table-yes">{{approveYN}}</td>
              <td>{{createdAt}}</td>
              <td>{{updatedAt}}</td>
            </tr>
</template>


<script>
// import moment from "moment";

export default {
  name: "AdminStaffItem",
  props: {
    articleno: Number,
    userid: String,
    username: String, //name
    email: String,
    phone: String,
    areaid: Number,
    areaname: String,
    approveYN: String,
    createdAt: String,
    updatedAt: String,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
};
</script>

<style></style>
