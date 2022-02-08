<template>
    <div class="row flex-nowrap bg-gray-1">
     <admin-sidebar />
    <router-view/>   
    <div class="w-80 p-5">
       <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
    <b-container class="bv-example-row mt-3 ">

    <br>
    <div class="bg-white shadow">
      <div class="text-start p-3 fw-bold bd-bt">
        데스크 관리
        <button class="btn btn-outline-primary border-color btn bd-highlight rounded-pill btn-sm float-end" @click="deskCreate()">데스크등록</button>
          </div>
   
        <table hover responsive class="table-class  p-3 mb-5 bg-body rounded">
          <thead head-variant="dark" class="th-class">
            <tr>
              <th>No.</th>
              <th>아이디</th>
              <th>이름</th>
              <th>위도</th>
              <th>경도</th>
              <th>지역코드</th>
              <th>지역명</th>
              <th>생성날짜</th>
              <th>수정날짜</th>
            </tr>
          </thead>
          <tbody>
            <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="deskEdit()">
              <td>1</td>
              <td>hongdong</td>
              <td>홍대1</td>
              <td>37.55486722863767</td>
              <td>126.9226391549618</td>
              <td>01</td>
              <td>강남구</td>
              <td>2022-01-01 11:20</td>
              <td>2022-01-01 11:20</td>
            </tr>
            <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="deskEdit()">
              <td>1</td>
              <td>hongdong</td>
              <td>홍대1</td>
              <td>37.55486722863767</td>
              <td>126.9226391549618</td>
              <td>01</td>
              <td>강남구</td>
              <td>2022-01-01 11:20</td>
              <td>2022-01-01 11:20</td>
            </tr>
            <!-- <admin-staff-item
              v-for="(article, index) in articles"
              :key="index"
              v-bind="article"
            /> -->
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
// @ is an alias to /src

export default {
  name: 'AdminDesk',
  components: {
    AdminSidebar
  },
  methods: {
    deskCreate(){
      this.$router.push({ name: 'AdminDeskCreate' })
    },
    deskEdit(){
      this.$router.push({ name: 'AdminDeskEdit' })
    },
  }
}
</script>
<style>
  table {
    width: 100%;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
  }
</style>