<template>
    <div class="row flex-nowrap bg-gray-1 mw-100">
     <AdminSidebar />
     <router-view/> 
        <div class="w-80 p-5">
            <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
        <br>
    <div class="bg-white shadow">
      <div class="text-start p-3 fw-bold bd-bt ">게시판 관리＞게시글 상세정보</div>
        <table hover responsive class="p-3 mt-3 bg-body rounded"><tbody>
            <thead class="th-calss">
                <tr class="tr-info"><th><br></th><th><br></th></tr>
            </thead>        
            <tr class="tr-info" data-bs-placement="top" >
              <td>지역정보: </td>
              <td>{{areaName}}</td>
            </tr>
            <tr class="tr-info" data-bs-placement="top">
              <td>데스크이름: </td>
              <td>{{deskName}}</td>
            </tr>
            <tr class="tr-info" data-bs-placement="top">
              <td>제목: </td>
              <td>{{title}}</td>
            </tr>
            <tr class="tr-info" data-bs-placement="top">
              <td>내용 </td>
              <td> <textarea class="form-control form-25" id="exampleFormControlTextarea1" rows="3" disabled v-model="content" ></textarea></td>
            </tr>
            <tr class="tr-info" data-bs-placement="top">
              <td>생성일 </td>
              <td>{{createdAt}}</td>
            </tr>
            <tr class="tr-info" data-bs-placement="top">
              <td>수정일 </td>
              <td>{{updatedAt}}</td>
            </tr>
          </tbody>
        </table>
        <div class="bd-top">
          <div class="d-flex flex-row-reverse bd-highlight">
            <button class="btn btn-outline-danger bd-red-2 bd-highlight m-2 rounded-pill bt-pdd" type="button"  data-bs-toggle="modal" data-bs-target="#adminpost_deleteModal">삭제</button>
            <button class="btn btn-outline-dark bd-highlight m-2 rounded-pill bt-pdd" type="button"  data-bs-toggle="modal" data-bs-target="#adminpost_cancelModal">취소</button>
          </div>
          
        </div>
        
        </div>
    </div>
        
    </div>
    <!-- delete modal -->
    <div class="modal fade" id="adminpost_deleteModal" tabindex="-1" aria-labelledby="adminpost_deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-red-2 px-4 pt-3 pb-4">
            <div id="psot-login-modal-header" class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5" >게시글을 <strong class="t-red-2">삭제</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn btn-outline-danger bd-red-2 btn-yes-no " data-bs-dismiss="modal" @click="AdminPostEditDelete()">네</button>
                <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal">아니오</button>
              </div>          
            </div>
          </div>
        </div>
      </div>
    <!-- cancel modal -->
      <div class="modal fade" id="adminpost_cancelModal" tabindex="-1" aria-labelledby="adminpost_cancelModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-blue-4 px-4 pt-3 pb-4">
            <div class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5" id="post-login-modal-label">수정을<strong class="t-blue-3">취소</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn btn-outline-primary bd-blue-4 btn-yes-no" data-bs-dismiss="modal" @click="postCancel()" >네</button>
              <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal">아니오</button>
              </div>          
            </div>
          </div>
        </div>
      </div>
</template>

<script>
import AdminSidebar from '@/components/admin/AdminSidebar.vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST
//const id1 = this.id

export default({
    name: 'AdminPostEdit',
    data(){
      return {
          id: this.$route.params.id,
          title: this.$route.params.title,
          content: this.$route.params.content,
          areaName: this.$route.params.areaName,
          deskName: this.$route.params.deskName,
          createdAt: this.$route.params.createdAt,
          updatedAt: this.$route.params.updatedAt,
      }
    },
    components: {
        AdminSidebar,
    },
    
    created(){
       const router = useRouter()

       if(!localStorage.getItem('adminData')){
          router.push({ name: 'AdminAuth' })
       }
       console.log(this.$route.params.id)
       //const test123 = this.$route.params.id
    },
    methods: {
        // postDelete(){
        //   //AdminPostEditDelete();
        //    // this.$router.push({ name: 'AdminPost' })
        // },
        postCancel(){
            this.$router.push({ name: 'AdminPost' })
        },
        AdminPostEditDelete(){
        
          const token = localStorage.getItem('token')
          const config = {
            Authorization: `Bearer ${token}`
          }

          axios.delete(`${SERVER_HOST}/admin/board/posts/`+this.id, {headers: config})
            .then(response => {
              console.log(response.data);
              this.$router.push({ name: 'AdminPost' })
              // console.log("articles: ")
              // console.log(this.articles)
              // console.log("articles.data: ")
              // console.log(this.articles.data)
            })
            .catch(err => {
              console.log(err);
            }); 

      }
    }
})
</script>