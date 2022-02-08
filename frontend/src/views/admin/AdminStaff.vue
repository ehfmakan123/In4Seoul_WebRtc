<template>
  <div class="row flex-nowrap bg-gray-1">
     <admin-sidebar />
    <router-view/>   
    <div class="w-80 p-5">
       <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
    <b-container class="bv-example-row mt-3 ">

    <br>
    <div class="bg-white shadow">
      <div class="text-start p-3 fw-bold bd-bt">스태프 관리</div>
   
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
            <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="staffEdit()">
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
            </tr>
            <!-- <admin-staff-item
              v-for="(article, index) in articles"
              :key="index"
              v-bind="article"
            /> -->
            <admin-staff-item
              v-for="(adminstaff, index) in pageArray"
              :key="index"
              v-bind="article"
            />
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


import AdminSidebar from '@/components/admin/AdminSidebar.vue'
import AdminStaffItem from '@/components/admin/AdminStaffItem.vue'
import axios from 'axios'
// @ is an alias to /src

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
      pageArray: [],
      adminpage: 'test'
    }
  },
  created () {
    axios.get('http://127.0.0.1:8080/admin/staff', {headers: {
                  //  Authorization: `Bearer ${token}`
                    }})
    .then(response => {
      console.log(response);
      this.pageArray = response.data.contacts;
    })
    .catch(err => {
      console.log(err);
    });
  },
  methods: {
    staffEdit(){
      this.$router.push({ name: 'AdminStaffEdit' })
    },
  },
  
}
</script>
