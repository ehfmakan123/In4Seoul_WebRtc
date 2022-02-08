<template>
    <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="staffEdit()">
        <!-- <router-link
        :to="{ name: 'BoardView', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      > -->
              <td>{{adminstaff.articleno}}</td>
              <td>{{adminstaff.userid}}</td>
              <td>{{adminstaff.username}}</td>
              <td>{{adminstaff.email}}</td>
              <td>{{adminstaff.phone}}</td>
              <td>{{adminstaff.areaid}}</td>
              <td>{{adminstaff.areaname}}</td>
              <td class="table-yes">{{adminstaff.approveYN}}</td>
              <td>{{adminstaff.createdAt}}</td>
              <td>{{adminstaff.updatedAt}}</td>
            </tr>
</template>


<script>
// import moment from "moment";

export default {
  name: "AdminStaffItem",
  data () {
    return {
      pageNum: 0
    }
  },
  props: {
    adminstaff:{
      type: Array,
      requeired: true
      // articleno: Number,
      // userid: String,
      // username: String, //name
      // email: String,
      // phone: String,
      // areaid: Number,
      // areaname: String,
      // approveYN: String,
      // createdAt: String,
      // updatedAt: String,
    },
    pageSize: {
      type: Number,
      required: false,
      default: 10
    },
  },
  methods:{
		nextPage () {
      this.pageNum +=1;
    },
    prevPage () {
      this.pageNum -=1;
    }
	},
  computed: {
    pageCount () {
      let listLeng = this.listArray.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      
      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
      return this.listArray.slice(start, end);
    }
  },
  
  
};
</script>

<style></style>
