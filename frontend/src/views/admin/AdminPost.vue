<template>
  <div class="row flex-nowrap bg-gray-1">
    <admin-sidebar/>
    <router-view/>
    <div class="w-80 p-5">
       <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
    <div class="bv-example-row mt-3 ">

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
              <th>등록날짜</th>
              <th>수정날짜</th>
            </tr>
          </thead>
          <tbody>
            <admin-post-item
                v-for="(adminpost, index) in articles"
                :key="index"
                :adminpost="adminpost"
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


  </div>
    </div>
  </div>
</template>

<script>
import AdminSidebar from '@/components/admin/AdminSidebar.vue'
import AdminPostItem from '@/components/admin/AdminPostItem.vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

// @ is an alias to /src
const SERVER_HOST = process.env.VUE_APP_SERVER_HOST

export default {
  name: 'AdminPost',
  components: {
    AdminSidebar,
    AdminPostItem,
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
    }
  },
  props: {

  },
  computed: {



  },
  setup(){

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
    axios.get(`${SERVER_HOST}/admin/board/posts`, {headers: config})
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
      axios.get(`${SERVER_HOST}/admin/board/posts?page=`+this.nowPage, {headers: config})
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
  }
  
}
</script>
