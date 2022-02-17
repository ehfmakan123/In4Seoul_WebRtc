<template>
    <div class="row flex-nowrap bg-gray-1 mw-100">
     <AdminSidebar />
     <router-view/> 
      <div class="w-80 p-5">
            <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
        <br>
        <div class="bg-white shadow">
          <div class="text-start p-3 fw-bold bd-bt ">데스크 관리＞데스크 수정</div>
            <table hover responsive class="p-3 mt-3 bg-body rounded">
              <tbody>
                <thead class="th-calss">
                    <tr class="tr-info"><th><br></th><th><br></th></tr>
                </thead> 
                <tr class="tr-info" data-bs-placement="top" >
                    <td>No. </td>
                    <td>{{id}}</td>
                  </tr>           
                <tr class="tr-info" data-bs-placement="top" >
                  <td>아이디: </td>
                  <td><input class="form-control form-25" v-model="deskId"></td>
                </tr>
                <tr class="tr-info" data-bs-placement="top" >
                  <td>패스워드: </td>
                  <td v-if="!isHidden" class="d-flex flex-row align-items">
                    <input  class="form-control form-25 item" disabled value="수정하시려면 버튼을 클릭">
                    <button class="btn btn-outline-primary border-color btn bd-highlight ms-2 rounded-pill bt-pdd item" type="button"  v-on:click="isHidden = !isHidden">새 비밀번호 입력</button></td>
                  <td v-else class="d-flex flex-row align-items" >
                    <input class="form-control form-25 item" v-model="password" placeholder="새 비밀번호를 입력해주세요">
                    <button class="btn btn-outline-primary border-color btn bd-highlight ms-2 rounded-pill bt-pdd item" type="button"  v-on:click="isHidden = !isHidden">비밀번호 안 바꾸기 </button>
                  </td>
                    
                </tr>
                <tr class="tr-info" data-bs-placement="top">
                  <td>이름: </td>
                  <td><input class="form-control form-25 " v-model="korName"></td>
                </tr>
                <tr class="tr-info" data-bs-placement="top">
                  <td>영어이름: </td>
                  <td><input class="form-control form-25 " v-model="engName"></td>
                </tr>
                <tr class="tr-info" data-bs-placement="top">
                  <td>위도: </td>
                  <td><input class="form-control form-25 " v-model="latitude"></td>
                </tr>
                <tr class="tr-info" data-bs-placement="top">
                  <td>경도: </td>
                  <td><input class="form-control form-25 " v-model="altitude"></td>
                </tr>
                <tr class="tr-info" data-bs-placement="top">
                  <td>지역코드: </td>
                  <td>
                    <select class="form-select form-25" aria-label="Default select example" v-model="areaId" >
                      <option v-for="areavalue in areavalues" :key="areavalue" :value="areavalue.id">
                        <span v-if="areavalue.id >= 10">{{areavalue.id}} {{areavalue.korName}}</span>
                        <span v-else>0{{areavalue.id}} {{areavalue.korName}}</span>
                      </option>
                    </select>
                  </td>
                </tr>
                <tr class="tr-info" data-bs-placement="top">
                  <td>지역명: </td>
                  <td>{{areaName}}</td>
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
                <button class="btn btn-outline-danger bd-red-2 bd-highlight m-2 rounded-pill bt-pdd" type="button"  data-bs-toggle="modal" data-bs-target="#admindesk_deleteModal">삭제</button>
                <button class="btn btn-outline-dark bd-highlight m-2 rounded-pill bt-pdd" type="button"  data-bs-toggle="modal" data-bs-target="#admindesk_cancelModal">취소</button>
                <button class="btn btn-outline-primary border-color btn bd-highlight m-2 rounded-pill bt-pdd" @click="deskUpdate()">수정</button>
              </div>
              
            </div>
            
        </div>
      </div>
        
    </div>
    <!-- delete modal -->
    <div class="modal fade" id="admindesk_deleteModal" tabindex="-1" aria-labelledby="admindesk_deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-red-2 px-4 pt-3 pb-4">
            <div id="desk-login-modal-header" class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5" >계정을 <strong class="t-red-2">삭제</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn btn-outline-danger bd-red-2 btn-yes-no " data-bs-dismiss="modal" @click="deskDelete()">네</button>
                <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal">아니오</button>
              </div>          
            </div>
          </div>
        </div>
      </div>
    <!-- cancel modal -->
      <div class="modal fade" id="admindesk_cancelModal" tabindex="-1" aria-labelledby="admindesk_cancelModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-blue-4 px-4 pt-3 pb-4">
            <div class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5" id="dsek-login-modal-label">수정을<strong class="t-blue-3">취소</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn btn-outline-primary bd-blue-4 btn-yes-no" data-bs-dismiss="modal" @click="deskCancel()" >네</button>
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

export default({
    name: 'AdminDeskEdit',
    props:{
      // userId: Number,
      //       name: String,
      //       email: String,
      //       phone: String,
      //       areaId: Number,
      //       areaName: String,
      //       approveYN: String,
      //       createdAt: String,
      //       updatedAt: String,
    },
    data(){
      return {
          id: this.$route.params.id,
          deskId: this.$route.params.deskId,
          korName: this.$route.params.korName,
          engName: this.$route.params.engName,
          password: this.$route.params.password,
          latitude: this.$route.params.latitude,
          altitude: this.$route.params.altitude,
          areaId: this.$route.params.areaId,
          areaName: this.$route.params.areaName,
          createdAt: this.$route.params.createdAt,
          updatedAt: this.$route.params.updatedAt,
          deleteYN: this.$route.params.deleteYN,
          areavalues: [],
          isHidden: false,
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
      const token = localStorage.getItem('token')
      const config = {
        Authorization: `Bearer ${token}`
      }
      axios.get(`${SERVER_HOST}/admin/areas`, {headers: config})
      .then(response => {
        console.log(response.data);
        this.areavalues = response.data.data;
        //this.pageArray = response.data.data.contacts;
        // console.log("areavalues: ")
        // console.log(this.areavalues)
        // console.log("areavalues.data: ")
        // console.log(this.areavalues.data)
      })
      .catch(err => {
        console.log(err);
      });
    },
    setup(){

    },
    methods: {
        deskUpdate(){
            this.AdminDeskEidtUpdate();
            //.$router.push({ name: 'AdminDesk' })
        },
        deskCancel(){
            this.$router.push({ name: 'AdminDesk' })
        },
        deskDelete(){
            this.AdminDeskEditDelete();
            //this.$router.push({ name: 'AdminDesk' })
        },


        AdminDeskEidtUpdate(){
        
          const token = localStorage.getItem('token')
          const config = {
            Authorization: `Bearer ${token}`
          }

        console.log(this.data);
      
        axios({
          method: 'put',
          url: `${SERVER_HOST}/admin/desks/`+ this.id,
          headers: config,
          data: {
              id: this.id,
              deskId: this.deskId,
              korName: this.korName,
              engName: this.engName,
              password: this.password,
              latitude: this.latitude,
              altitude: this.altitude,
              areaId: this.areaId,
              areaName: this.areaName,
              createdAt: this.createdAt,
              updatedAt: this.updatedAt,
              deleteYN: "N"
          }
        })
          .then((response) => {
            console.log("put성공!")
            console.log(response)
            this.$router.push({ name: 'AdminDesk' })
            
          })
          .catch((err) => {
            console.log("put실패!")
            console.error(err)
            }
          )   

      },

      AdminDeskEditDelete(){
        
        const token = localStorage.getItem('token')
        const config = {
          Authorization: `Bearer ${token}`
        }

        axios({
          method: 'put',
          url: `${SERVER_HOST}/admin/desks/`+ this.id,
          headers: config,
          data: {
              id: this.id,
              deskId: this.deskId,
              korName: this.korName,
              engName: this.engName,
              password: this.password,
              latitude: this.latitude,
              altitude: this.altitude,
              areaId: this.areaId,
              areaName: this.areaName,
              createdAt: this.createdAt,
              updatedAt: this.updatedAt,
              deleteYN: "Y",
          }
        })
          .then((response) => {
            console.log("put성공!")
            console.log(response)
            this.$router.push({ name: 'AdminDesk' })
            
          })
          .catch((err) => {
            console.log("put실패!")
            console.error(err)
            }
          )   

      }




    }
})
</script>