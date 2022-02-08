<template>
  <div class="row flex-nowrap bg-gray-1">
    <admin-sidebar/>
    <router-view/>
    <div class="w-80 p-5">
       <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
    <b-container class="bv-example-row mt-3 ">

    <br>
    <div class="bg-white shadow">
      <div class="text-start p-3 fw-bold bd-bt">게시판 관리</div>
   
        <table hover responsive class="table-class  p-3 mb-5 bg-body rounded">
          <thead head-variant="dark" class="th-class">
            <tr>
              <th>No.</th>
              <th>지역정보</th>
              <th>데스크</th>
              <th>제목</th>
              <th>내용</th>
              <th>비밀번호</th>
              <th>등록날짜</th>
              <th>수정날짜</th>
            </tr>
          </thead>
          <tbody>
            <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="postEdit()">
              <td>1</td>
              <td>마포구</td>
              <td>홍대데스크</td>
              <td>남산같이 가요</td>
              <td>동행 구합니다!</td>
              <td>*****</td>
              <td>2022-01-01 11:20</td>
              <td>2022-01-01 11:20</td>
            </tr>
            <tr class="tr-class" data-bs-placement="top" data-bs-toggle="tooltip" @click="postEdit()">
              <td>1</td>
              <td>마포구</td>
              <td>홍대데스크</td>
              <td>뮤지컬 프랑켄슈타인</td>
              <td>블루스퀘어에서 하고있어요...</td>
              <td>*****</td>
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
  name: 'AdminPost',
  components: {
    AdminSidebar,
  },
  methods: {
    postEdit(){
      this.$router.push({ name: 'AdminPostEdit' })
    },
  }
  
}
</script>
