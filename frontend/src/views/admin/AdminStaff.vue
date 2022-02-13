<template>
  <div class="row flex-nowrap bg-gray-1">
     <admin-sidebar />
    <div class="w-80 p-5">
       <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
    <b-container class="bv-example-row mt-3 ">

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
    <!-- <admin-staff-item
              v-for="(article, index) in articles"
              :key="index"
              v-bind="article"
            /> -->
            <!-- <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="staffEdit()">
              <td>1</td>
              <td>hongdong</td>
              <td>홍길동</td>
              <td>h2@naver.com</td>
              <td>010-1234-1234</td>
              <td>01</td>
              <td>강남구</td>
              <td class="table-yes">Y</td>
              <td>2022-01-01 11:20</td>
              <td>2022-01-01 11:20</td>
            </tr>
            <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="staffEdit()">
              <td>1</td>
              <td>hongdong</td>
              <td>홍길동</td>
              <td>h2@naver.com</td>
              <td>010-1234-1234</td>
              <td>01</td>
              <td>강남구</td>
              <td class="t-red-2">N</td>
              <td>2022-01-01 11:20</td>
              <td>2022-01-01 11:20</td>
            </tr> -->
    
    
    </div>







    <!-- <nav aria-label="..." class="d-flex justify-content-center">
      <div class="pagination d-flex justify-content-between">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-item page-btn page-link">
          ＜
        </button>
        
        <span class="page-link page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
        <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-item page-btn page-link">
          ＞
        </button>
      </div>
    </nav> -->

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


<!-- <nav aria-label="..." class="d-flex justify-content-center">
      <ul class="pagination d-flex justify-content-between">
    <li class="page-item disabled">
      <a class="page-link" href="#" tabindex="-1" aria-disabled="true">＜</a>
    </li>
    
      <div class="btn-cover">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
          이전
        </button>
        <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
        <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
          다음
        </button>
      </div>
    </nav> -->
  </b-container>
    </div>


  </div>
  
</template>

<script>
import AdminSidebar from '@/components/admin/AdminSidebar.vue'
import AdminStaffItem from '@/components/admin/AdminStaffItem.vue'
import axios from 'axios'

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
 
    const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }
    axios.get(`${SERVER_HOST}/admin/staff`, {headers: config})
    .then(response => {
      console.log(response.data);
      this.articles = response.data.data;
      this.pageArray = response.data.data.contacts;
      // console.log("articles: ")
      // console.log(this.articles)
      // console.log("articles.data: ")
      // console.log(this.articles.data)
    })
    .catch(err => {
      console.log(err);
    });
  },
  methods: {
    
   
  },
  
}
</script>
