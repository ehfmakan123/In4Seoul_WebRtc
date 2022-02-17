<template>
    <div class="row flex-nowrap bg-gray-1 mw-100">
     <AdminSidebar />
     <router-view/> 
      <div class="w-80 p-5">
              <h2 class="text-start mt-3 fw-bold">Admin 관리자 페이지</h2>
          <br>
      <div class="bg-white shadow">
        <div class="text-start p-3 fw-bold bd-bt ">스태프 관리＞수정페이지</div>
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
                <td>아이디</td>
                <td>{{userId}}</td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>이름</td>
                <td><input class="form-control form-25 " v-model="name"></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>이메일</td>
                <td><input type="email" class="form-control form-25" v-model="email"></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>휴대폰번호</td>
                <td><input class="form-control form-25" v-model="phone" ></td>
              </tr>
              <tr class="tr-info" data-bs-placement="top">
                <td>지역코드</td>
                <td>
                  <select class="form-select form-25" aria-label="Default select example" v-model="areaId" >
                    <option v-for="areavalue in areavalues" :key="areavalue" :value="areavalue.id">
                      <span v-if="areavalue.id >= 10">{{areavalue.id}} {{areavalue.korName}}</span>
                      <span v-else>0{{areavalue.id}} {{areavalue.korName}}</span>
                    </option>
                  </select>
                </td>
              </tr>
              <!-- <tr class="tr-info" data-bs-placement="top">
                <td>지역명 </td>
                <td><select class="form-select form-10" aria-label="Default select example" v-model="areaName" ></td>
              </tr> -->
              <tr class="tr-info" data-bs-placement="top">
                <td>승인 </td>
                <td><select class="form-select form-10" aria-label="Default select example" name="assign" id="assign"  v-model="approveYN">
                      <option value="Y">Y</option>
                      <option value="N">N</option>
                    </select></td>
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
              <button class="btn btn-outline-danger bd-red-2 bd-highlight m-2 rounded-pill bt-pdd" type="button"  data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
              <button class="btn btn-outline-dark bd-highlight m-2 rounded-pill bt-pdd" type="button"  data-bs-toggle="modal" data-bs-target="#cancelModal" @click="edit()">취소</button>
              <button class="btn btn-outline-primary border-color btn bd-highlight m-2 rounded-pill bt-pdd" @click="staffUpdate()">수정</button>
            </div>
            
          </div>
          
          </div>
      </div>
        
    </div>
    <!-- delete modal -->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-red-2 px-4 pt-3 pb-4">
            <div id="staff-login-modal-header" class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5" >계정을 <strong class="t-red-2">삭제</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn btn-outline-danger bd-red-2 btn-yes-no " data-bs-dismiss="modal" @click="staffDelete()">네</button>
                <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal">아니오</button>
              </div>          
            </div>
          </div>
        </div>
      </div>
      <!-- cancel modal -->
      <div class="modal fade" id="cancelModal" tabindex="-1" aria-labelledby="cancelModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
          <div class="modal-content modal-rounded bd-blue-4 px-4 pt-3 pb-4">
            <div class="d-flex justify-content-center align-items-center mt-3">
              <h5 class="modal-title ms-3 fs-5" id="staff-login-modal-label">수정을<strong class="t-blue-3">취소</strong>하시겠습니까?</h5>
            </div>
            <div class="modal-body">
              <div class="d-flex justify-content-center mt-4">
                <button type="button" class="btn btn-outline-primary bd-blue-4 btn-yes-no" data-bs-dismiss="modal" @click="staffCancel()" >네</button>
              <button type="button" class="btn btn-outline-dark ms-5 btn-yes-no" data-bs-dismiss="modal">아니오</button>
              </div>          
            </div>
          </div>
        </div>
      </div>

</template>

<script>

import AdminSidebar from '@/components/admin/AdminSidebar.vue'
//import AdminAreas from '@/components/admin/AdminAreas.vue'
import axios from 'axios'
import { useRouter } from 'vue-router'


const SERVER_HOST = process.env.VUE_APP_SERVER_HOST
//const id1 = this.id

export default({
    name: 'AdminStaffEdit',
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
          userId: this.$route.params.userId,
          name: this.$route.params.name,
          phone: this.$route.params.phone,
          email: this.$route.params.email,
          deleteYN: this.$route.params.deleteYN,
          approveYN: this.$route.params.approveYN,
          areaId: this.$route.params.areaId,
          createdAt: this.$route.params.createdAt,
          updatedAt: this.$route.params.updatedAt,
          areavalues: [],
      }
    },
    components: {
        AdminSidebar,
        //AdminAreas
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
      
    //   const AdminStaffEidtUpdate = () => {
        
    //   // console.log("adminstafdata")
    //   // console.log(id1)

    //   const token = localStorage.getItem('token')
    //   const config = {
    //     Authorization: `Bearer ${token}`
    //   }

    //   axios.put(`${SERVER_HOST}/admin/staff/`+ this.id, {headers: config}, {
    //       name:1,
    //       phone:1,
    //       email:1,
    //       deleteYN:1,
    //       approveYN:1,
    //       areaId:2,
          
    //     })
    //     .then(function (response) {
    //       console.log("put성공!");
    //       console.log(response);
    //     })
    //     .catch(function (error) {
    //       console.log("put실패!");
    //       console.log(error);
    //     });
    // }
    // return{
    //  AdminStaffEidtUpdate,
    // }
    },
    methods: {
   
        staffUpdate(){
            this.AdminStaffEidtUpdate();
            //this.$router.push({ name: 'AdminStaff' })
        },
        staffCancel(){
            this.$router.push({ name: 'AdminStaff' })
        },
        staffDelete(){
            //삭제 구현.
            this.AdminStaffEditDelete();
            //this.$router.push({ name: 'AdminStaff' })
        },
      AdminStaffEidtUpdate(){
        
        const token = localStorage.getItem('token')
        const config = {
          Authorization: `Bearer ${token}`
        }

        // axios.put(`${SERVER_HOST}/admin/staff/`+ this.id, 
        //     {headers: config}, 
        //     {
        //       // name: this.name,
        //       // phone: this.phone,
        //       // email: this.email,
        //       deleteYN: this.deleteYN,
        //       approveYN: this.approveYN,
        //       areaId: this.areaId,
                        
        //   })
        //   .then(function (response) {
        //     console.log("put성공!");
        //     console.log(response);
        //   })
        //   .catch(function (error) {
        //     console.log("put실패!");
        //     console.log(error);
        // });

        console.log("deleteTN: "+this.deleteYN)
        
      
        axios({
          method: 'put',
          url: `${SERVER_HOST}/admin/staff/`+ this.id,
          headers: config,
          data: {
              name: this.name,
              phone: this.phone,
              email: this.email,
              deleteYN: "N",
              approveYN: this.approveYN,
              areaId: this.areaId,
          }
        })
          .then((response) => {
            console.log("put성공!")
            console.log(response)
            this.$router.push({ name: 'AdminStaff' })
            
          })
          .catch((err) => {
            console.log("put실패!")
            console.error(err)
            }
          )   

      },

      AdminStaffEditDelete(){
        
        const token = localStorage.getItem('token')
        const config = {
          Authorization: `Bearer ${token}`
        }

        axios({
          method: 'put',
          url: `${SERVER_HOST}/admin/staff/`+ this.id,
          headers: config,
          data: {
              name: this.name,
              phone: this.phone,
              email: this.email,
              deleteYN: "Y",
              approveYN: this.approveYN,
              areaId: this.areaId,
          }
        })
          .then((response) => {
            console.log("put성공!")
            console.log(response)
            this.$router.push({ name: 'AdminStaff' })
            
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