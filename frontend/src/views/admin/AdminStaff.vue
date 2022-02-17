<template>
  <div class="row flex-nowrap bg-gray-1">
     <admin-sidebar />
    <div class="w-80 p-5">
       <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
    <div class="bv-example-row mt-3 ">

    <br>
    <div class="bg-white shadow">
      <div v-if="articles.length" class="text-start p-3 fw-bold bd-bt">스태프 관리</div>
   
        <table hover responsive class="table-class  p-3 mb-5 bg-body rounded">
          <thead head-variant="dark" class="th-class">
            <tr>
              <th>No.</th>
              <th>UserID</th>
              <th>이름</th>
              <th>email</th>
              <th>휴대폰번호</th>
              <th>지역코드</th>
              <th>지역명</th>
              <th>승인</th>
              <th>생성날짜</th>
              <th>수정날짜</th>
            </tr>
          </thead>
          <tbody>
            <admin-staff-item
              v-for="(adminstaff, index) in articles"
              :key="index"
              :adminstaff="adminstaff"
            />
          </tbody>
        </table>
 
    
    
    </div>

    <nav aria-label="..." class="d-flex justify-content-center mb-4 fixed-bottom">
      <ul class="pagination d-flex justify-content-between">
        <li v-if="start" class="page-item">
          <a class="page-link" href="#" @click="gotostartpage">«</a>
        </li>
        <li v-else class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true">«</a>
        </li>

        <li v-if="pre" li class="page-item">
          <a class="page-link" href="#" @click="gotoprepage">‹</a>
        </li>
        <li v-else li class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true">‹</a>
        </li>

        <li
         v-for="pageitem in pageNumbers"
          v-bind:id="'p'+pageitem"
          v-bind:class="{' active': pageitem == nowPage}"
          :key="pageitem"
         class="page-item"><a class="page-link" href="#" @click="thispage(pageitem)">{{pageitem}}</a></li>
        
        
        <li v-if="next" class="page-item">
          <a class="page-link" href="#" @click="gotonextpage">›</a>
        </li>
        <li v-else li class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true">›</a>
        </li>

        <li v-if="end" class="page-item">
          <a class="page-link" href="#" @click="gotoendpage">»</a>
        </li>
        <li v-else li class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true">»</a>
        </li>
      </ul>
    </nav>







  <!-- <nav aria-label="..." class="d-flex justify-content-center">
  <ul class="pagination d-flex justify-content-between">
    <li class="page-item disabled">
      <a class="page-link" href="#" tabindex="-1" aria-disabled="true">«</a>
    </li>
    <li class="page-item disabled">
      <a class="page-link" href="#" tabindex="-1" aria-disabled="true">‹</a>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item active" aria-current="page" @click="thispage">
      <a class="page-link" href="#">2</a>
    </li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item"><a class="page-link" href="#">4</a></li>
    <li class="page-item"><a class="page-link" href="#">5</a></li>
    <li class="page-item">
      <a class="page-link" href="#">›</a>
    </li>   
    <li class="page-item">
      <a class="page-link" href="#">»</a>
    </li>
  </ul>
  </nav> -->

  


  </div>
    </div>


  </div>
  
</template>

<script>
import AdminSidebar from '@/components/admin/AdminSidebar.vue'
import AdminStaffItem from '@/components/admin/AdminStaffItem.vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

// @ is an alias to /src
const SERVER_HOST = process.env.VUE_APP_SERVER_HOST


export default {
  name: 'AdminStaff',
  components: {
    AdminSidebar,
    AdminStaffItem,
  },
  // data() {
  //   return {
  //     articles: [],
  //   }
  // },
  data () {
    return {
      articles: [],
      pageNumbers: [],
      totalPage: undefined,
      totalCount: undefined,
      nowPage: undefined,
      startPage: undefined,
      endPage: undefined,
      pre: false,
      next: true,
      start: false,
      end: true,     
    }
  },
  props: {

  },
  computed: {

  },
  setup(){
    // const AdminStaffData = () => {
    //   console.log("adminstafdata")

    //   const token = localStorage.getItem('token')
    //   const config = {
    //     Authorization: `Bearer ${token}`
    //   }

    //   axios({
    //     method: 'get',
    //     url: `${SERVER_HOST}/admin/staff`,
    //     headers: config
    //   })      
    //     .then(res => {
    //       console.log("axios 성공")
    //       console.log(res)
    //     this.pageArray = res.data.contacts;
          
    //     })
    //     .catch(err => {
    //       console.log("axios 실패")
    //       console.log(err);
    //       console.log(err.response.data)
    //     })

    // }
    // return{
    //  AdminStaffData,

    // }
  },

  created () {
     const router = useRouter()

    if(!localStorage.getItem('adminData')){
      router.push({ name: 'AdminAuth' })
    }
    const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }
    axios.get(`${SERVER_HOST}/admin/staff`, {headers: config})
    .then(response => {
      console.log(response.data);
      this.articles = response.data.data;
      this.totalPage = response.data.totalPage;
      this.totalCount = response.data.totalCount;
      this.nowPage = response.data.nowPage;
      this.startPage = response.data.startPage;
      this.endPage = response.data.endPage;
      this.pre = response.data.pre;
      this.next = response.data.next;
      this.start = response.data.start;
      this.end = response.data.end;
      this.pageNumbers = []
      for(var i =this.startPage; i<=this.endPage;i++){
        this.pageNumbers[i-this.startPage] = i
      }
    })
    .catch(err => {
      console.log(err);
    });


  },
  methods: {
    thispage(np){
      this.nowPage= np
      const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }
      axios.get(`${SERVER_HOST}/admin/staff?page=`+this.nowPage, {headers: config})
      .then(response => {
        console.log(response.data);
        this.articles = response.data.data;
          this.totalPage = response.data.totalPage;
          this.totalCount = response.data.totalCount;
          this.nowPage = response.data.nowPage;
          this.startPage = response.data.startPage;
          this.endPage = response.data.endPage;
          this.pre = response.data.pre;
          this.next = response.data.next;
          this.start = response.data.start;
          this.end = response.data.end;
          this.pageNumbers = []
          for(var i =this.startPage; i<=this.endPage;i++){
            this.pageNumbers[i-this.startPage] = i
          }
      })
      .catch(err => {
        console.log(err);
      });
    },
    gotoprepage(){
      if(this.nowPage-5 < 1 ){
        this.thispage(1)
      }else{
        this.thispage(this.nowPage-5)
      }
      
    },
    gotonextpage(){
      if(this.nowPage+5 > this.totalPage){
        this.thispage(this.totalPage)
      }else{
        this.thispage(this.nowPage+5)
      }
    },
    gotostartpage(){
      this.thispage(1)
    },
    gotoendpage(){
      this.thispage(this.totalPage)
    }
   
  },
  
}
</script>
