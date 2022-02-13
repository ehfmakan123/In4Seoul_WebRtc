<template>
    <div class="row flex-nowrap bg-gray-1">
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
              <td><input class="form-control form-25" v-model="deskId"></td>
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
            console.log("put성공!")
            console.log(response)
            this.$router.push({ name: 'AdminDesk' })
            
          })
          .catch((err) => {
            console.log("create실패!")
            console.error(err)
            }
          )   

      },

    },
    created(){
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
