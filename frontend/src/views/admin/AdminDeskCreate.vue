<template>
    <div class="row flex-nowrap bg-gray-1 mw-100">
     <AdminSidebar />
     <router-view/> 
        <div class="w-80 p-5">
            <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
        <br>
    <div class="bg-white shadow">
      <div class="text-start p-3 fw-bold bd-bt ">데스크 관리＞데스크 생성</div>
        <table hover responsive class="p-3 mt-3 bg-body rounded"><tbody>
            <thead class="th-calss">
                <tr class="tr-info"><th><br></th><th><br></th></tr>
            </thead>        
            <tr class="tr-info" data-bs-placement="top" >
              <td>아이디 </td>
              <td class="d-flex flex-row align-items">
                <input class="form-control form-25 item" v-model="deskId">
                <button class="btn btn-outline-primary border-color btn bd-highlight ms-2 rounded-pill bt-pdd item" type="button" v-on:click="isHiddened = true" @click="AdminDeskIdCheck">
                  중복체크
                </button>
                <div v-if="isHiddened" class="item">
                  <span v-if="isIdchecked==2">사용가능한 아이디입니다.</span>
                  <span v-else-if="isIdchecked==0">이미 있는 아이디입니다.</span>
                  <span v-else>데이터를 가져올 수 없습니다.</span>
                  
                </div>
              </td>
            </tr>
            <tr class="tr-info" data-bs-placement="top">
              <td>비밀번호 </td>
              <td><input class="form-control form-25 " v-model="password"></td>
            </tr>
            <tr class="tr-info" data-bs-placement="top" >
              <td>이름 </td>
              <td><input class="form-control form-25 " v-model="korName"></td>
            </tr>
            <tr class="tr-info" data-bs-placement="top">
              <td>영어이름 </td>
              <td><input class="form-control form-25 " v-model="engName"></td>
            </tr>
            <tr class="tr-info" data-bs-placement="top" >
              <td>위도 </td>
              <td><input class="form-control form-25 " v-model="latitude"></td>
            </tr>
            <tr class="tr-info" data-bs-placement="top">
              <td>경도 </td>
              <td><input class="form-control form-25 " v-model="altitude"></td>
            </tr>
            <tr class="tr-info" data-bs-placement="top">
              <td>자역번호 </td>
                  <select class="form-select form-25" aria-label="Default select example" v-model="areaId" >
                    <option v-for="areavalue in areavalues" :key="areavalue" :value="areavalue.id">
                      <span v-if="areavalue.id >= 10">{{areavalue.id}} {{areavalue.korName}}</span>
                      <span v-else>0{{areavalue.id}} {{areavalue.korName}}</span>
                    </option>
                  </select>
            </tr>
          </tbody>
        </table>
        <div class="bd-top">
          <div class="d-flex flex-row-reverse bd-highlight">
            <button class="btn btn-outline-dark bd-highlight m-2 rounded-pill bt-pdd" type="button"  data-bs-toggle="modal" data-bs-target="#admindesk_cancelModal">취소</button>
            <button class="btn btn-outline-primary border-color btn bd-highlight m-2 rounded-pill bt-pdd" @click="deskcreateInsert()">등록</button>
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
              <h5 class="modal-title ms-3 fs-5" id="staff-create-cancel-modal-label">등록을<strong class="t-blue-3">취소</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn btn-outline-primary bd-blue-4 btn-yes-no" data-bs-dismiss="modal" @click="deskcreateCancel()" >네</button>
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
    name: 'AdminDeskCreate',
    components: {
        AdminSidebar
    },
    data(){
      return {
          deskId:"",
          korName:"",
          engName:"",
          password:"",
          latitude:"",
          altitude:"",
          areaId:"",
          areavalues: [],
          isHiddened: false,
          isIdchecked: 0, //0:이미존재 1:권한없음 2:가능아이디
      }
    },
    methods: {
        // deskcreateInsert(){
        //     this.$router.push({ name: 'AdminDesk' })
        // },
        deskcreateCancel(){
            this.$router.push({ name: 'AdminDesk' })
        },

      deskcreateInsert(){
        
        const token = localStorage.getItem('token')
        const config = {
          Authorization: `Bearer ${token}`
        }

        console.log("deleteTN: "+this.deleteYN)
        console.log("areaId: "+this.areaId)
        console.log(this.data);
      
        axios({
          method: 'post',
          url: `${SERVER_HOST}/admin/desks/`,
          headers: config,
          data: {
              deskId:this.deskId,
              korName:this.korName,
              engName:this.engName,
              password:this.password,
              latitude:this.latitude,
              altitude:this.altitude,
              areaId:this.areaId,
          }
        })
          .then((response) => {
            console.log("post성공!")
            console.log(response)
            this.$router.push({ name: 'AdminDesk' })
            
          })
          .catch((err) => {
            console.log("create실패!")
            console.error(err)
            }
          )   

      },


      AdminDeskIdCheck(){
        
        const token = localStorage.getItem('token')
        const config = {
            Authorization: `Bearer ${token}`
        }
        console.log("idcheck axios 전:"+this.deskId)
        axios({
          method: 'post',
          url: `${SERVER_HOST}/admin/desks/idcheck`,
          headers: config,
          data: {
              userId:this.deskId,
            }
          })
          .then((response) => {
            console.log("idcheck성공:"+this.deskId)
            console.log(response)
            const statusCode = response.data.statusCode
            
            if(statusCode == 200){
              this.isIdchecked = 2;
            }else{
              this.isIdchecked = 0;
            }
            //this.isIdchecked = 2;
          })
          .catch((err) => {
            const statusCode = err.response.data.statusCode
            if(statusCode == 403){
              console.log("403err!")
              this.isIdchecked = 1;
            }else{
              console.log("idcheck실패!")
              console.error(err)
            }
            
            }
          )   

      },


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
})
</script>
