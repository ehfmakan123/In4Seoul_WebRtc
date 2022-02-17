<template>
  <div class="row flex-nowrap bg-gray-1">
    <staff-sidebar/>
    <div class="w-80 p-5 staff-main">
      <h2 class="text-start mt-3 fw-bold">상담사 <span class="t-blue-4 fs-4">{{ staffData.name }}</span> 페이지</h2>
    <div class="bv-example-row mt-3 ">

    <br>
    <div class="bg-white shadow">
      <div class="text-start p-3 fw-bold bd-bt">스태프 관리</div>
   
        <table hover responsive class="text-center text-center p-3 mb-5 bg-body rounded">
          <thead head-variant="dark" class="th-class">
            <tr>
              <!-- <th>고유번호</th> -->
              <th>No.</th>
              <th>안내데스크</th>
              <th>상담시간</th>
              <th>상담종료시간</th>
              <!-- <th>상담내용</th> -->
            </tr>
          </thead>
          <tbody>
           <StaffHometableItem 
           v-for="(staffItem, index) in articles"
             :key="index" 
             :staffItem="staffItem"/>
          </tbody>
        </table>    
    </div>

    <!-- <nav aria-label="..." class="d-flex justify-content-center">
      <ul class="pagination d-flex justify-content-between">
        <li class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true">＜</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item active" aria-current="page">
          <a class="page-link" href="#">2</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
          <a class="page-link" href="#">＞</a>
        </li>
      </ul>
    </nav> active class 추가, aria-current="page", -->

    <nav aria-label="..." class="d-flex justify-content-center fixed-bottom mb-4">
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


</div>



  </div>
  </div>
  
  
</template>

<script>
// @ is an alias to /src
//import { ref } from 'vue'
import StaffHometableItem from '@/components/staff/StaffHomeTableItem.vue'
import StaffSidebar from '@/components/staff/StaffSidebar.vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

// @ is an alias to /src
const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'StaffHome',
  components: {
    StaffHometableItem,
    StaffSidebar
  },
  setup() {
    // let testArray = ref([
    //   {
    //     "id": "1",
    //     "deskName": "홍대1번",
    //     "startTime": "2022-01-14 14:00",
    //     "endTime": "2022-01-14 14:20",
    //     "content": "홍대 명소 문의"
    //   },
    //   {
    //     "id": "4",
    //     "deskName": "홍대2번",
    //     "startTime": "2022-01-15 14:00",
    //     "endTime": "2022-01-15 14:20",
    //     "content": "홍대 지하철역 위치 문의"
    //   }       
    // ])



    // return {
    //   testArray,
    // }
    },
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
        staffData: undefined,
      }
    },
    created () {
      const router = useRouter()

      this.staffData = JSON.parse(localStorage.getItem('staffData'))

      if(!localStorage.getItem('staffData')){
        router.push({ name: 'Auth' })
      }   
      
      const token = localStorage.getItem('token')
        const config = {
          Authorization: `Bearer ${token}`
        }
        axios.get(`${SERVER_HOST}/staff/meeting-logs`, {headers: config})
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
      console.log("thispage"+this.nowPage)
      const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }
      axios.get(`${SERVER_HOST}/staff/meeting-logs?page=`+this.nowPage, {headers: config})
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
        //console.log("this.pageNumbers: "+this.pageNumbers)
        //this.pageNumbers.length = 0
        this.pageNumbers = []
        //this.pageNumbers.splice(0)
        //console.log("this.pageNumbers: "+this.pageNumbers)
        for(var i =this.startPage; i<=this.endPage;i++){
          this.pageNumbers[i-this.startPage] = i;
          //i-this.startPage로 해야 0부터 시작.
          //console.log("")
        }
        //console.log("this.pageNumbers: "+this.pageNumbers)

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
