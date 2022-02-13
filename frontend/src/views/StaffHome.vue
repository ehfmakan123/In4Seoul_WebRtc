<template>
  <div class="row flex-nowrap bg-gray-1">
    <staff-sidebar/>
    <div class="w-80 p-5 staff-main">
      <h2 class="text-start mt-3 fw-bold">상담사 이싸피 상담사 페이지</h2>
    <b-container class="bv-example-row mt-3 ">

    <br>
    <div class="bg-white shadow">
      <div class="text-start p-3 fw-bold bd-bt">스태프 관리</div>
   
        <table hover responsive class="table-class  p-3 mb-5 bg-body rounded">
          <thead head-variant="dark" class="th-class">
            <tr>
              <th>No.</th>
              <th>고유번호</th>
              <th>안내데스크</th>
              <th>상담시간</th>
              <th>상담종료시간</th>
              <th>상담내용</th>
            </tr>
          </thead>
          <tbody>
           <StaffHometableItem v-for="(tableItem, index)
            in articles" :key="index" 
             :staffItem="staffItem"/>
          </tbody>
        </table>    
    </div>

    <nav aria-label="..." class="d-flex justify-content-center">
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
    </nav>
    </b-container>



  </div>
  </div>
  
  
</template>

<script>
// @ is an alias to /src
//import { ref } from 'vue'
import StaffHometableItem from '@/components/staff/StaffHomeTableItem.vue'
import StaffSidebar from '@/components/staff/StaffSidebar.vue'
import axios from 'axios'

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
      }
    },
    created () {
 
    const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }
      axios.get(`${SERVER_HOST}/staff/meeting-logs`, {headers: config})
      .then(response => {
        console.log(response.data);
        this.articles = response.data.data;
      })
      .catch(err => {
        console.log(err);
      });
  },
}
</script>
